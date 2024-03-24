package com.elevatemartcartservice.utils;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println(splitIntoFibonacci("1101111"));
    }
    public static List<Integer> splitIntoFibonacci(String num) {
        List<Integer> res = new ArrayList<>();
        backtrack(num.toCharArray(), 0, res, 0, 0);
        return res;
    }

    private static boolean backtrack(char[] chars, int start, List<Integer> res, int prev, int sum) {
        if (start == chars.length) {
            return res.size() >= 3;
        }
        long curLong = 0;
        for (int i = start; i < chars.length; i++) {
            if (i > start && chars[start] == '0') {
                break;
            }
            curLong = curLong * 10 + chars[i] - '0';
            if (curLong > Integer.MAX_VALUE) {
                break;
            }
            int cur = (int) curLong;
            if (res.size() >= 2) {
                if (cur > sum) {
                    break;
                } else if (cur < sum) {
                    continue;
                }
            }
            res.add(cur);
            if (backtrack(chars, i + 1, res, cur, prev + cur)) {
                return true;
            }
            res.remove(res.size() - 1);
        }
        return false;
    }
}
