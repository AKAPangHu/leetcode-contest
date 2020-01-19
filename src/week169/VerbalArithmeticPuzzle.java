package week169;

/**
 * 口算难题
 *
 * @author: 胖虎
 * @date: 2020/1/4 19:22
 **/
public class VerbalArithmeticPuzzle {
    public class Solution {
        //记录不能成为0的字母
        int[] notZeroSet;
        //记录字母对应的数值
        int[] charToNum;
        //记录数值对应字母
        int[] numToChar;

        public boolean isSolvable(String[] words, String result) {
            notZeroSet = new int[26];
            int maxLength = 0;
            //出现在首位的字母不能赋值为0
            notZeroSet[result.charAt(0) - 'A'] = 1;
            for (String word : words) {
                notZeroSet[word.charAt(0) - 'A'] = 1;
                maxLength = word.length();
            }
            //如果words中的最长字符串的长度比结果字符串长，那么肯定无解。也可以加上 如果结果字符串比words中最长字符串长两位也肯定无解，因为words中最多有5个字符串
            if (maxLength > result.length()) return false;
            // charToNum , numToChar 数组的初始值赋为-1
            charToNum = new int[26];
            for (int i = 0; i < 26; i++) {
                charToNum[i] = -1;
            }
            numToChar = new int[10];
            for (int i = 0; i < 10; i++) {
                numToChar[i] = -1;
            }
            return dfsHelper(words, result, 0, 0, 0);
        }

        // wordIndex表示递归到了words中的第几个字符串，posIndex表示从右往左数的第几个字母，nextDigts表示类似个位相加 后 需要进到十位的值，比如 7+5 = 12 那么需要往十位进1.
        private boolean dfsHelper(String[] words, String result, int wordIndex, int posIndex, int nextDigts) {
            //当posIndex == result.length()的时候，递归结束
            if (posIndex >= result.length()) {
                if (nextDigts == 0) {
                    return true;
                } else return false;
            }
            int len = words.length;
            //当wordIndex == words.length的时候，表示这一位的赋值过程结束
            if (wordIndex == len) {
                int sum = nextDigts;
                //求这一位上，所有字母对应的数值的和。
                for (String word : words) {
                    if (word.length() > posIndex) {
                        sum += charToNum[word.charAt(word.length() - posIndex - 1) - 'A'];
                    }
                }
                int charPos = result.charAt(result.length() - posIndex - 1) - 'A';
                int num = sum % 10;
                // 如果result该位的字母已经在之前赋值过了，那么直接比较大小，相等则继续递归，不等则返回false。
                if (charToNum[charPos] >= 0) {
                    if (charToNum[charPos] == num) {
                        return dfsHelper(words, result, 0, posIndex + 1, sum / 10);
                    } else return false;
                } else {
                    // 如果result该位的字母在之前并未被赋值，而该数值已经赋给其他字母了，那么根据题目中的条件2，返回flase。否则就给该字母赋值，然后继续递归。递归得到false的时候注意回溯修改charToNum 和  numToChar 的对应值位-1
                    if (numToChar[num] >= 0) {
                        return false;
                    } else {
                        charToNum[charPos] = num;
                        numToChar[num] = charPos + 'A';
                        boolean isValid = dfsHelper(words, result, 0, posIndex + 1, sum / 10);
                        if (isValid) return true;
                        else {
                            charToNum[charPos] = -1;
                            numToChar[num] = -1;
                            return false;
                        }
                    }
                }
            } else {
                // 为words中的字符串的这一位所在的字母赋值的过程
                int wordLen = words[wordIndex].length();
                //该字符串这一位为空
                if (wordLen <= posIndex) {
                    return dfsHelper(words, result, wordIndex + 1, posIndex, nextDigts);
                } else {
                    char c = words[wordIndex].charAt(wordLen - posIndex - 1);
                    int charPos = words[wordIndex].charAt(wordLen - posIndex - 1) - 'A';
                    //该字符串这一位的字母在之前已经赋值过了。
                    if (charToNum[charPos] >= 0) {
                        return dfsHelper(words, result, wordIndex + 1, posIndex, nextDigts);
                    }
                    // 尝试赋值该字符串这一位的字母为0
                    if (notZeroSet[charPos] == 0 && numToChar[0] == -1) {
                        numToChar[0] = c;
                        charToNum[charPos] = 0;
                        boolean isValid = dfsHelper(words, result, wordIndex + 1, posIndex, nextDigts);
                        if (isValid) {
                            return true;
                        } else {
                            numToChar[0] = -1;
                            charToNum[charPos] = -1;
                        }
                    }
                    // 尝试赋值该字符串这一位的字母为1~9
                    for (int i = 1; i < 10; i++) {
                        if (numToChar[i] == -1) {
                            numToChar[i] = c;
                            charToNum[charPos] = i;
                            boolean isValid = dfsHelper(words, result, wordIndex + 1, posIndex, nextDigts);
                            if (isValid) {
                                return true;
                            } else {
                                numToChar[i] = -1;
                                charToNum[charPos] = -1;
                            }
                        }
                    }
                }
                // 如果之前的return 都没触发则返回false。也就是 0-9的赋值都不满足等式。
                return false;
            }
        }
    }

}
