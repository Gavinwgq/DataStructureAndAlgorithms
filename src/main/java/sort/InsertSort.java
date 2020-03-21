package sort;

/**
 * 插入排序
 * @author wangguoqiang
 * @date 2020/3/20 20:33
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {3,5,2,4,1,6,9,7,8};
        //insertSort(arr);
        insertSort2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t",arr[i]);
        }
    }

    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];//待插入的值
            int insertIndex = i-1;//待插入的位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                //数据后移
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex+1] = insertVal;
        }
    }

    public static void insertSort2(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i-1;
            for (; j >=0 && temp<arr[j] ; j--) {
                arr[j+1] = arr[j];
            }
            arr[j+1] = temp;
        }
    }
}
