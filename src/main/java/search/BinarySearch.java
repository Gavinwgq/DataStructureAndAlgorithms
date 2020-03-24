package search;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 二分查找
 * @author wangguoqiang
 * @date 2020/3/24 21:27
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,21,34,47,59,61,72};
        //int i = binarySearch(arr, 0, arr.length - 1, 61);
        int i = binarySearch(arr,61);
        if(i == -1){
            System.out.println("未找到数据");
        }else{
            System.out.println("数据找到,下标为"+i);
        }

        System.out.println("################");

        int[] arr2 = {1,21,34,47,47,47,59,61,72};
        List<Integer> list = binarySearch3(arr2, 0, arr2.length - 1, 47);
        System.out.println(list);
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

    //循环方式
    public static int binarySearch(int[] arr,int val){
        int left = 0,right = arr.length-1;
        int mid = 0;
        while (left<right){
            mid = (left + right) / 2;
            if(val>arr[mid]){
                left = mid +1;
            }else if(val < arr[mid]){
                right = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }


    //数组中存在多个时,找出所有索引
    public static List<Integer> binarySearch3(int[] arr, int left, int right, int val){
        if(left>right){
            return Lists.newArrayList();
        }
        int mid = (left + right) / 2;
        if(val>arr[mid]){
            return binarySearch3(arr,mid+1,right,val);
        }else if (val == arr[mid]){
            List<Integer> result = Lists.newArrayList();
            //先从mid向左查找
            int temp = mid -1;
            while (true){
                //因为数组是有序的，所以arr[temp]!=val,就可以退出了
                if(temp <0 || arr[temp]!=val){
                    break;
                }
                result.add(temp--);
            }
            result.add(mid);
            //再从mid向右查找
            temp = mid + 1;
            while (true){
                if(temp >arr.length || arr[temp]!=val){
                    break;
                }
                result.add(temp++);
            }
            return result;
        }else{
            return binarySearch3(arr,left,mid-1,val);
        }
    }
}
