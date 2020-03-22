package sort;

/**
 * 快速排序
 * @author wangguoqiang
 * @date 2020/3/21 16:04
 */
public class QuickSort {
    public static void main(String[] args) {
        //int[] arr = {3,5,2,6,4,1,4,7,8};
        int[] arr = {3,4,2,1};
        quickSort(arr,0,arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d\t",arr[i]);
        }
    }

    public static void quickSort(int[] arr,int left,int right){
        int l = left;
        int r = right;
        int pivot = arr[(left+right)/2];//数组中间的值
        int temp = 0;
        while (l<r){
            //从左边开始找比中间值大的，找到后退出
            while (arr[l]<pivot){
                l++;
            }
            //在右边倒着找比中间值小的，找到后退出
            while (arr[r]>pivot){
                r--;
            }

            if(l>=r){
                //此时middle左边的都比middle小，middle右边的都比middle大
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果不做相等的处理，当待排序的数组中出现和基准值相等的时候，出导致死循环的出现 需要移动坐标
            if(arr[l] == pivot){
                //不能是 l++;
                r--;
            }
            if(arr[r] == pivot){
                l++;
            }
        }
        //没有这个判断，会出现栈溢出
        if(l == r){
            l++;
            r--;
        }

        if(left<r){
            quickSort(arr,left,r);
        }
        if(right >l){
            quickSort(arr,l,right);
        }
    }
}
