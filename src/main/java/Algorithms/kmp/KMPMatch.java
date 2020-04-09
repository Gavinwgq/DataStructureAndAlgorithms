package Algorithms.kmp;

import java.util.Arrays;

/**
 * kmp算法
 * @author wangguoqiang
 * @date 2020/4/9 20:00
 */
public class KMPMatch {
    public static void main(String[] args) {
        String str1 = "AABCABD CAABDAB BACDA";
        String str2 = "AABDAB";
        int[] next = kmpNext(str2);
        System.out.println("next="+ Arrays.toString(next));
        int index = kmpMatch(str1, str2, next);
        System.out.println("index="+index);
    }

    public static int kmpMatch(String str1,String str2,int[] next){
        for (int i = 0,j=0; i < str1.length(); i++) {
            while (j>0 && str1.charAt(i)!=str2.charAt(j)){
                j = next[j-1];
            }
            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if(j == str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }

    public static int[] kmpNext(String str){
        int[] next = new int[str.length()];
        next[0] = 0;
        for (int i = 1,j=0; i < str.length(); i++) {
            while (j>0 && str.charAt(i)!=str.charAt(j)){
                j = next[j-1];
            }
            if(str.charAt(i) == str.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
