package sort;

/**
 * 冒泡排序
 * @author wangguoqiang
 * @date 2020/3/19 20:34
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3,5,2,4,1,6,9,7,8};
        bubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t",arr[i]);
        }
    }


    public static void bubbleSort(int[] arr){
        int temp = 0;
        for (int i = 0; i < arr.length -1; i++) {
            for (int j = 0; j < arr.length -1 - i; j++) {
                if (arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
