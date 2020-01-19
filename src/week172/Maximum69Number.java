package week172;

import java.util.Arrays;

/**
 * 6和9组成的最大数字
 * 简单题
 * @author: 胖虎
 * @date: 2020/1/19 10:44
 **/
public class Maximum69Number {
    public int maximum69Number (int num) {
        String s = String.valueOf(num);
        char[] chars = s.toCharArray();
        int i = 0;
        for (int j = 0; j < chars.length; j++) {
            if (chars[j] == '6'){
                chars[j] = '9';
                break;
            }
        }
        for (char c : chars) {
            i *= 10;
            i += (c - '0');
        }
        return i;
    }

    public static void main(String[] args) {
        Maximum69Number m = new Maximum69Number();
        int i = m.maximum69Number(9669);
        System.out.println(i);
    }
}
