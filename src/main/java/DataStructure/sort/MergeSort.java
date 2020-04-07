package DataStructure.sort;

/**
 * 归并排序
 * @author wangguoqiang
 * @date 2020/3/23 12:35
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {3,5,2,4,1,6,9,7,8};
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t",arr[i]);
        }
    }

    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left<right){
            int mid = (left+right)/2;
            mergeSort(arr,left,mid,temp);
            mergeSort(arr,mid+1,right,temp);
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     * 合并阶段
     * @param arr 原始数组
     * @param left 左边数组初始索引
     * @param mid 数组中间索引
     * @param right 数组最右索引
     * @param temp 临时数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;
        int j = mid + 1; //右边数组初始索引
        int t = 0;//temp数组索引
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else{
                temp[t++] = arr[j++];
            }
        }
        //上一个循环结束后，如果一侧还为结束，直接复制过去即可
        while (i<=mid){
            temp[t++] = arr[i++];
        }
        while (j<=right){
            temp[t++] = arr[j++];
        }

        t = 0;
        //复制回原数组
        while (left<=right){
            arr[left++] = temp[t++];
        }
    }
}
