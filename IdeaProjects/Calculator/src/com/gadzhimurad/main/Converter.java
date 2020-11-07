package com.gadzhimurad.main;

import java.util.TreeMap;

class Converter {
    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();
    private static final int[] arab = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static final String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    public static String arabToRoman(int number) {
        int l = map.floorKey(number);
        if (number == l) {
            return map.get(number);
        }
        return map.get(l) + arabToRoman(number - l);
    }

    public static int romanToArab(String number) {
        for (int i = 0; i < roman.length; i++) {
            if (roman[i].equals(number)) {
                return arab[i];
            }
        }
        return 0;
    }
}
