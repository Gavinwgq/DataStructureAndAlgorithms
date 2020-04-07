package DataStructure.sort;

/**
 * 冒泡排序
 * @author wangguoqiang
 * @date 2020/3/19 20:34
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3,5,2,4,1,6,9,7,8};
        bubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t",arr[i]);
        }
    }


    public static void bubbleSort(int[] arr){
        int temp = 0;
        boolean flag = false;//标识一趟排序种是否发生过交换，如果没有交换过，可提前结束排序
        for (int i = 0; i < arr.length -1; i++) {
            for (int j = 0; j < arr.length -1 - i; j++) {
                if (arr[j]>arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if(!flag){
                break;
            }else{
                flag = false;//重置为false,进行下一趟排序
            }
        }
    }
}
