package search;

/**
 * 二分查找
 * @author wangguoqiang
 * @date 2020/3/24 21:27
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,21,34,47,59,61,72};
        int i = binarySearch(arr, 0, arr.length - 1, 61);
        if(i == -1){
            System.out.println("未找到数据");
        }else{
            System.out.println("数据找到,下标为"+i);
        }
    }

    //递归方式
    public static int binarySearch(int[] arr,int left,int right,int val){
        if(left>right){
            return -1;
        }
        int mid = (left + right) / 2;
        if(val>arr[mid]){
            return binarySearch(arr,mid+1,right,val);
        }else if (val == arr[mid]){
            return mid;
        }else{
            return binarySearch(arr,left,mid-1,val);
        }
    }
}
