import java.io.*;
import java.util.*;
import java.util.stream.*;


public class QuickSort {
    public static void main(String[] args) throws IOException {
        int[] arr1 = {};
        System.out.println(Arrays.toString(solve(arr1)));
        int[] arr2 = {6,4,1,8,9,2,7,5,3};
        System.out.println(Arrays.toString(solve(arr2)));
        int[] arr3 = {1};
        System.out.println(Arrays.toString(solve(arr3)));
        int[] arr4 = {6,4,2,10,9,1,7,11,5,3,0,8};
        System.out.println(Arrays.toString(solve(arr4)));
    }
    static int[] solve(int[] arr) {
        return quickSort(arr, 0, arr.length-1);
    }
    static int[] quickSort(int[] arr, int left, int right) {
        if (arr.length == 0) {
            return arr;
        }
        int l = left, r = right;
        int pivot = arr[(l+r)/2];
        int tmp;

        while (l <= r) {
            while(arr[l] < pivot) {
                l++;
            }
            while(arr[r] > pivot) {
                r--;
            }
            if (l <= r) {
                tmp = arr[l];
                arr[l] = arr[r];
                arr[r] = tmp;
                l++;
                r--;
            }
        }
        if (left < r) {
            quickSort(arr, left, r);
        }
        if (l < right) {
            quickSort(arr, l, right);
        }
        return arr;
    }
}