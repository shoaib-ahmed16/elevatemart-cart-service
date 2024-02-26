package com.elevatemartcartservice.service.impl;

import com.elevatemartcartservice.domain.Cart;
import com.elevatemartcartservice.domain.CartProduct;
import com.elevatemartcartservice.dto.Product;
import com.elevatemartcartservice.dto.enums.CartStatus;
import com.elevatemartcartservice.dto.enums.Constants;
import com.elevatemartcartservice.exception.CartAddUnknownServerError;
import com.elevatemartcartservice.exception.CartNotFoundException;
import com.elevatemartcartservice.exception.ProductServiceException;
import com.elevatemartcartservice.exception.ProductServiceSkuProductNotFound;
import com.elevatemartcartservice.repository.CartRepository;
import com.elevatemartcartservice.service.CartService;
import com.elevatemartcartservice.service.MicroServicesCallRouter;
import com.elevatemartcartservice.utils.Calculator;
import com.elevatemartcartservice.utils.ProductFetch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private MicroServicesCallRouter callRouter;
    @Autowired
    private CartRepository cartRepo;

    private double taxAmount;

    private double totalAmount;
    @Autowired
    private Calculator cal;
    private final List<CartStatus> status= List.of(CartStatus.INITIALIZED,CartStatus.PENDING);
    @Override
    public String addToCart(CartProduct cartProduct) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Product> futureProduct= service.submit(new ProductFetch(cartProduct.getProductSku(),callRouter));
        Optional<Cart> cart =cartRepo.findByUsernameAndStatusIn(cartProduct.getUsername(), status);
        try {
            Product product=futureProduct.get();
            if(Objects.isNull(product)){
                throw new ProductServiceSkuProductNotFound("No Product found for the Product sku: {}"+cartProduct.getProductSku());
            }
            taxAmount = cal.doubleToIntMultiply(cal.taxCalculation(product) , cartProduct.getQuantity());
            totalAmount = cal.doubleToIntMultiply(product.getPrice() , cartProduct.getQuantity());
            Cart updateCart;
            if (cart.isPresent()) {
                updateCart = cart.get();
                updateCart.getProducts().add(cartProduct);
                updateCart.setTaxAmount(cal.add(updateCart.getTaxAmount(),taxAmount));
                updateCart.setSubTotal(cal.add(updateCart.getSubTotal() , totalAmount));
                updateCart.setTotal(cal.add(updateCart.getTotal() , cal.add(totalAmount , taxAmount)));
                updateCart.setStatus(CartStatus.PENDING);
                updateCart.setCountryCode(Constants.COUNTRY.getStr());
                updateCart.setTotalQuantity(cal.addInts(updateCart.getTotalQuantity() , cartProduct.getQuantity()));
            } else {
                updateCart = new Cart();
                updateCart.addProduct(cartProduct);
                updateCart.setUsername(cartProduct.getUsername());
                updateCart.setTaxAmount(cal.add(updateCart.getTaxAmount(),taxAmount));
                updateCart.setSubTotal(cal.add(updateCart.getSubTotal() , totalAmount));
                updateCart.setTotal(cal.add(updateCart.getTotal() , cal.add(totalAmount , taxAmount)));
                updateCart.setStatus(CartStatus.PENDING);
                updateCart.setCountryCode(Constants.COUNTRY.getStr());
                updateCart.setTotalQuantity(cartProduct.getQuantity());
            }
            cartRepo.save(updateCart);
            return "Product is Add successfully.";
        }catch (ProductServiceException psExc) {
            throw  new ProductServiceException(psExc.getMessage());
        }catch (ProductServiceSkuProductNotFound psSkuExc) {
            throw  new ProductServiceSkuProductNotFound(psSkuExc.getMessage());
        }catch (Exception exc){
            throw new CartAddUnknownServerError(exc.getMessage());
        }finally {
            service.shutdown();
        }
    }

    @Override
    public String removeFromCart(CartProduct cartProduct) {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Product> futureProduct= service.submit(new ProductFetch(cartProduct.getProductSku(),callRouter));
        Optional<Cart> cart =cartRepo.findByUsernameAndStatusIn(cartProduct.getUsername(), status);
        try {
            Cart updateCart;
            if(cart.isPresent()){
                updateCart=cart.get();
            }else{
                throw new CartNotFoundException("No Cart found for user : "+cartProduct.getUsername());
            }
            Product product=futureProduct.get();
            if(Objects.isNull(product)){
                throw new ProductServiceSkuProductNotFound("No Product found for the Product sku: {}"+cartProduct.getProductSku());
            }
            taxAmount = cal.doubleToIntMultiply(cal.taxCalculation(product) , cartProduct.getQuantity());
            totalAmount = cal.doubleToIntMultiply(product.getPrice() , cartProduct.getQuantity());
            if(updateCart.getProducts().size()==Constants.MIN_SIZE_CART_PRODUCT_LIST.getValue()){
                cartRepo.delete(cart.get());
            }else{
                updateCart.getProducts().add(cartProduct);
                updateCart.setTaxAmount(cal.subtract(updateCart.getTaxAmount() ,taxAmount));
                updateCart.setSubTotal(cal.subtract(updateCart.getSubTotal() , totalAmount));
                updateCart.setTotal(cal.subtract(updateCart.getTotal() , cal.subtract(totalAmount , taxAmount)));
                updateCart.setStatus(CartStatus.PENDING);
                updateCart.setCountryCode(Constants.COUNTRY.getStr());
                updateCart.setTotalQuantity(cal.subtractInts(updateCart.getTotalQuantity() , cartProduct.getQuantity()));
                cartRepo.save(updateCart);
            }
            return "Product is Removed successfully.";
        }catch (CartNotFoundException cnfe){
            throw new CartNotFoundException(cnfe.getMessage());
        }catch (ProductServiceException psExc) {
            throw  new ProductServiceException(psExc.getMessage());
        }catch (ProductServiceSkuProductNotFound psSkuExc) {
            throw  new ProductServiceSkuProductNotFound(psSkuExc.getMessage());
        }catch (Exception exc){
            throw new CartAddUnknownServerError(exc.getMessage());
        }finally {
            service.shutdown();
        }
    }

    @Override
    public String updateToCart(CartProduct cartProduct) {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Product> futureProduct= service.submit(new ProductFetch(cartProduct.getProductSku(),callRouter));
        Optional<Cart> cart =cartRepo.findByUsernameAndStatusIn(cartProduct.getUsername(), status);
        try {
            Cart updateCart;
            if(cart.isPresent()){
                updateCart=cart.get();
            }else{
                throw new CartNotFoundException("No Cart found for user : "+cartProduct.getUsername());
            }
            Product product=futureProduct.get();
            if(Objects.isNull(product)){
                throw new ProductServiceSkuProductNotFound("No Product found for the Product sku: {}"+cartProduct.getProductSku());
            }
            CartProduct cartProductFetch = updateCart.getProducts()
                    .stream()
                    .filter(prod -> prod.getProductSku().equals(cartProduct.getProductSku()))
                    .findFirst()
                    .orElse(null);
            int quantity=cal.subtractInts(cartProduct.getQuantity(),cartProductFetch.getQuantity());
            taxAmount = cal.taxCalculation(product) ;
            totalAmount = product.getPrice();
            updateCart.getProducts().remove(cartProductFetch);
            if(cartProductFetch.getQuantity()==cartProduct.getQuantity()){
                updateCart.getProducts().remove(cartProductFetch);
                updateCart.setTaxAmount(cal.subtract(updateCart.getTaxAmount() ,taxAmount));
                updateCart.setSubTotal(cal.subtract(updateCart.getSubTotal() ,totalAmount));
                updateCart.setTotal(cal.subtract(updateCart.getTotal() ,cal.add(totalAmount,taxAmount)));
            }else {
                cartProduct.setCart(updateCart);
                updateCart.getProducts().add(cartProduct);
                updateCart.setTaxAmount(cal.add(updateCart.getTaxAmount() , cal.doubleToIntMultiply(taxAmount ,quantity)));
                updateCart.setSubTotal(cal.add(updateCart.getSubTotal() , cal.doubleToIntMultiply(totalAmount, quantity)));
                updateCart.setTotal(cal.add(updateCart.getTotal() , cal.add(cal.doubleToIntMultiply(totalAmount , quantity) , cal.doubleToIntMultiply(taxAmount , quantity))));
            }
            if(updateCart.getProducts().size()<Constants.MIN_SIZE_CART_PRODUCT_LIST.getValue()){
                cartRepo.delete(updateCart);
                return "Product is updated successfully.";
            }
            updateCart.setStatus(CartStatus.PENDING);
            updateCart.setCountryCode(Constants.COUNTRY.getStr());
            updateCart.setTotalQuantity(cartProduct.getQuantity());
            cartRepo.save(updateCart);
            return "Product is updated successfully.";
        }catch (CartNotFoundException cnfe){
            throw new CartNotFoundException(cnfe.getMessage());
        }catch (ProductServiceException psExc) {
            throw  new ProductServiceException(psExc.getMessage());
        }catch (ProductServiceSkuProductNotFound psSkuExc) {
            throw  new ProductServiceSkuProductNotFound(psSkuExc.getMessage());
        }catch (Exception exc){
            throw new CartAddUnknownServerError(exc.getMessage());
        }finally {
            service.shutdown();
        }
    }
}
