package com.frank.mytest.codetest.leetcode.string;

/**
 * Given two binary strings a and b, return their sum as a binary string.
 * Example 1:
 * Input: a = "11", b = "1"
 * Output: "100"
 * <p>
 * Example 2:
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 */
public class AddBinary {
    public static void main(String[] args) {

        System.out.println(addBinary("11", "1"));
        System.out.println(addBinary("1", "111"));
        System.out.println(addBinary("1010", "1011"));
        System.out.println(addBinaryV2("11", "1"));
        System.out.println(addBinaryV2("1", "111"));
        System.out.println(addBinaryV2("1010", "1011"));
    }

    /**
     * 進位數為 1 或 0
     * 00, 11 -> 得到結果與進位數的值 ”相同“
     * 01, 10 -> 得到結果與進位數的值 ”相反“
     */
    public static String addBinary(String a, String b) {
        if (a.isEmpty()) return b;
        if (b.isEmpty()) return a;

        // 當 a, b 不一樣長時，補上 0 到高位數
        boolean carry = false; // 初始進位數為 0
        int i = a.length() - 1;
        int j = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            // 取得 s1, s2 兩者的值，若位數不同補上 0
            char fromS1 = i >= 0 ? a.charAt(i--) : '0';
            char fromS2 = j >= 0 ? b.charAt(j--) : '0';
            String addString;
            if (fromS1 == fromS2) { // 00, 11
                addString = carry ? "1" : "0"; // 前一位的進位值
                carry = fromS1 == '1'; // 判斷是否下次需要進位
            } else { // 01, 10
                addString = carry ? "0" : "1"; // 與進位值相反
            }
            sb.append(addString);
        }

        // 確認最後進位數
        return carry ? ("1" + sb.reverse()) : sb.reverse().toString();
    }

    // 使用內建方法解析成 integer 相加後再轉回二進位字串
    public static String addBinaryV2(String a, String b) {
        int intA = Integer.parseInt(a, 2);
        int intB = Integer.parseInt(b, 2);
        return Integer.toBinaryString(intA + intB);
    }
}
