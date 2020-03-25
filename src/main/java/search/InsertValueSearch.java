package search;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 插值查找
 * 对于数据量比较大，分布较均匀的数组进行查找，效果比较好
 * @author wangguoqiang
 * @date 2020/3/25 18:53
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i+1;
        }
        //int i = binarySearch(arr, 0, arr.length - 1, 61);
        int i = insertValueSearch(arr,100);
        if(i == -1){
            System.out.println("未找到数据");
        }else{
            System.out.println("数据找到,下标为"+i);
        }
    }

    //递归方式
    public static int insertValueSearch(int[] arr,int left,int right,int val){
        //val<arr[0] || val>arr[right] 条件必须要有，否在 mid 可能越界
        if(left>right || val<arr[0] || val>arr[right]){
            return -1;
        }
        int mid = left + (right-left)*(val-arr[left])/(arr[right]-arr[left]);
        if(val>arr[mid]){
            return insertValueSearch(arr,mid+1,right,val);
        }else if (val == arr[mid]){
            return mid;
        }else{
            return insertValueSearch(arr,left,mid-1,val);
        }
    }

    //循环方式
    public static int insertValueSearch(int[] arr,int val){
        if(val<arr[0] || val>arr[arr.length-1]){
            return -1;
        }
        int left = 0,right = arr.length-1;
        int mid = 0;
        int count = 0;
        while (left<right){
            count++;
            //mid = (left+right)/2;
            mid = left + (right-left)*(val-arr[left])/(arr[right]-arr[left]);
            if(val>arr[mid]){
                left = mid +1;
            }else if(val < arr[mid]){
                right = mid - 1;
            }else{
                System.out.println("循环次数："+count);
                return mid;
            }
        }
        return -1;
    }


    //数组中存在多个时,找出所有索引
    public static List<Integer> insertValueSearch3(int[] arr, int left, int right, int val){
        if(left>right || val<arr[0] || val>arr[right]){
            return Lists.newArrayList();
        }
        int mid = left + (right-left)*(val-arr[left])/(arr[right]-arr[left]);
        if(val>arr[mid]){
            return insertValueSearch3(arr,mid+1,right,val);
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
            return insertValueSearch3(arr,left,mid-1,val);
        }
    }
}
