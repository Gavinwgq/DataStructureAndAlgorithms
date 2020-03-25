package search;

import java.util.Arrays;

/**
 * 斐波那契（黄金分割查找）
 * @author wangguoqiang
 * @date 2020/3/25 19:24
 */
public class FibonacciSearch {
    static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1,21,34,47,59,61,72};
        int i = fibonacciSearch(arr,73);
        if(i == -1){
            System.out.println("未找到数据");
        }else{
            System.out.println("数据找到,下标为"+i);
        }
    }

    /**
     * 构造一个斐波那契数列
     * @return
     */
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }

    public static int fibonacciSearch(int[] arr,int val){
        int low = 0;
        int high = arr.length -1;
        int mid = 0;
        int k = 0;//存放斐波那契数列下标
        int[] f = fib();
        while (high > f[k] -1){
            k++;
        }
        //f[k]可能大于arr的长度，因此需要进行扩充
        int[] temp = Arrays.copyOf(arr,f[k]);
        //多余的部分补充为原数组最后一个
        for (int i = high+1; i < f[k]; i++) {
            temp[i] = arr[high];
        }

        while (low <= high){
            mid = low + f[k-1] -1;
            if(val<temp[mid]){
                high = mid -1;
                k--;//向左查找
            }else if(val>temp[mid]){
                low = mid + 1;
                k-=2;//向右查找
            }else{
                return mid;
            }
        }
        return -1;
    }
}
