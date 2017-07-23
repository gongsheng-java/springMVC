package JAVASETEST.ALGORITHM;

import org.junit.Test;

/**
 * Created by gongsheng on 2017/7/15.
 */
public class SortMethod {
    public static void insertSort(){
        int[] a = new int[]{1,7,6,6};
        for (int i=0,j=i;i<a.length-1;j=++i){
            int ai =a[i+1];  //记录可能的较小的值
            while (ai<a[j]){
                a[j + 1] = a[j];
                if (j-- == 0) {
                    break;
                }
            }
            a[j + 1] = ai;   //重新赋值
        }
    }


    /**
     * test ++
     */
    @Test
    public void printStr(){
        int i = 0;
        int j = i++;
        System.out.println(i);
        int k = ++i;
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
        System.out.println("testtest");
    }

    private static void quickSort(int[] array,int beg,int end){  //快排的思想很巧妙
        if(beg >= end || array == null)
            return;
        int p = partition(array, beg, end);
        quickSort(array, beg, p-1);
        quickSort(array, p+1, end);
    }

    private static int partition(int[] array,int beg,int end){
        int last = array[end];
        int i = beg -1;
        for (int j = beg; j <= end-1; j++) {
            if(array[j] <= last){
                i++;
                if(i != j){
                    array[i] = array[i]^array[j];
                    array[j] = array[i]^array[j];
                    array[i] = array[i]^array[j];
                }
            }
        }
        if((i+1) != end){
            array[i+1] = array[i+1]^array[end];
            array[end] = array[i+1]^array[end];
            array[i+1] = array[i+1]^array[end];
        }
        return i+1;
    }


   public static void main(String[] args) {
       int[] a = new int[]{1,7,9,6,3,5,4,8,7};
       quickSort(a,0,a.length-1);
       for (int i:a){
           System.out.println(i);
       }
    }
}
