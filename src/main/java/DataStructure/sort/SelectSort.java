package DataStructure.sort;

/**
 * 选择排序
 * @author wangguoqiang
 * @date 2020/3/20 20:06
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {3,5,2,4,1,6,9,7,8};
        selectSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t",arr[i]);
        }
        System.out.println();
    }

    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<min){
                    min = arr[j];
                    minIndex = j;
                }
            }
            arr[minIndex] = arr[i];
            arr[i] = min;
        }
    }
}
