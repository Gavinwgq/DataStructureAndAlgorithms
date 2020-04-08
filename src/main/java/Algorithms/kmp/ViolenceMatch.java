package Algorithms.kmp;

/**
 * 暴力匹配
 * @author wangguoqiang
 * @date 2020/4/8 21:18
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "I like you,you like me?";
        String str2 = "like you";
        System.out.println(violenceMatch(str1,str2));
        String str3 = "like me.";
        System.out.println(violenceMatch(str1,str3));
    }

    /**
     * 在字符串str1匹配字符串str2
     * @param str1
     * @param str2
     * @return 匹配到，返回匹配起始位置，否则返回-1
     */
    public static int violenceMatch(String str1,String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int i = 0;
        int j = 0;
        while (i<s1.length && j<s2.length){
            if(s1[i] == s2[j]){//字符匹配
                i++;
                j++;
            }else{
                i = i - (j-1);
                j = 0;
            }
        }
        if(j == s2.length){//完整匹配
            return i - j;
        }
        return -1;
    }
}
