package week172;

import java.util.ArrayList;
import java.util.List;

/**
 * 竖直打印单词
 * 小模拟题 稍稍繁琐
 * @author: 胖虎
 * @date: 2020/1/19 11:00
 **/
public class PrintVertically {

    public List<String> printVertically(String s) {
        String[] strings = s.split(" ");
        ArrayList<String> arrayList = new ArrayList<>();
        int len = strings.length;
        int maxLength = -1;
        for (String string : strings) {
            if (string.length() > maxLength) {
                maxLength = string.length();
            }
        }
        for (int i = 0; i < maxLength; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len; j++) {
                if (i >= strings[j].length()){
                    sb.append(' ');
                }
                else {
                    sb.append(strings[j].charAt(i));
                }
            }
            char[] chars = sb.toString().toCharArray();
            int last = -1;
            for (int j = chars.length - 1; j >= 0; j--) {
                if (chars[j] != ' '){
                    last = j;
                    break;
                }
            }
            String r;
            if (last != -1){
                r = sb.toString().substring(0, last + 1);
            }
            else{
                r = null;
            }
            arrayList.add(r);
        }
        return arrayList;
    }

    public static void main(String[] args) {
        PrintVertically p = new PrintVertically();
        p.printVertically("HOW ARE YOU");
    }
}
