package sort;

/**
 * 希尔排序
 * @author wangguoqiang
 * @date 2020/3/21 14:20
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {3,5,2,4,1,6,9,7,8};
        shellSort3(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t",arr[i]);
        }
    }

    public static void shellSort(int[] arr){
        int temp = 0,j = 0;
        for(int gap = arr.length/2;gap>0;gap /= 2){
            for (int i = gap; i < arr.length ; i++) {
                temp = arr[i];
                j = i - gap;
                for (; j >=0 && temp<arr[j] ; j -= gap) {
                    arr[j+gap] = arr[j];
                }
                arr[j+gap] = temp;
            }
        }
    }

    public static void shellSort3(int[] arr){
        int temp = 0,j = 0;
        for(int gap = arr.length/2;gap>0;gap /= 2){
            for (int i = gap; i < arr.length ; i++) {
                temp = arr[i];
                j = i - gap;
                while (j >= 0 && temp < arr[j]){
                    //数据后移
                    arr[j+gap] = arr[j];
                    j-=gap;
                }
                arr[j+gap] = temp;
            }
        }
    }

    public static void shellSort2(int[] arr){
        int temp = 0;
        for(int gap = arr.length/2;gap>0;gap /= 2){
            for (int i = gap; i < arr.length ; i++) {
                for(int j = i - gap;j >= 0;j -= gap){
                    if(arr[j]>arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }
    }
}
