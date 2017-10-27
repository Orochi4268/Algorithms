package com.leong.chapter02_Sorting.section22_merge;

import com.leong.chapter02_Sorting.BaseSort;
import edu.princeton.cs.algs4.In;

import java.io.File;

/**
 * 归并排序（O(NlogN)）
 * 创建一个适当大小的数组然后将两个输入数组中的元素一个个从小到大的放入这个数组中。
 * @author leongfeng created on 2017/4/10
 */
public class MergeSort extends BaseSort{
    private Comparable[] aux;
    @Override
    public BaseSort sort(Comparable[] arr) {
        aux = new Comparable[arr.length];
        sort(arr, 0, arr.length - 1);
        return this;
    }

    /**
     * 自顶向下归并排序操作。
     * @param a
     * @param lo
     * @param hi
     */
    private void sort(Comparable[] a, int lo, int hi){
        // 改进1：对小规模数组使用插入排序
        /**
         if (hi <= lo + COUNT -1){
            插入排序
         }
         */
        // 结束递归的条件
        if (lo >= hi){
            return;
        }
        int mid = (lo + hi) / 2;

        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        // 改进2：测试数组是否已经有序，如果有序就不用merge
        if (less(a[mid], a[mid+1])){
            return;
        }
        merge(a, lo, mid, hi);
    }

    /**
     * 归并排序具体实现方法.
     * @param a 需要归并的目标数组
     * @param lo low boundary
     * @param mid middle index
     * @param hi high boundary
     */
    public void merge(Comparable[] a, int lo, int mid, int hi){
        // 1. 从a[]拷贝需要操作的元素到aux[]
        for (int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }

        // 2. 定义 i[lo, mid], j[mid+1, hi] 指针，指向的是 aux[]
        int i = lo;
        int j = mid + 1;
        // 3. 归并加a[lo, hi]
        for (int k = lo; k <= hi; k++){
            // 3.1 aux[]左半部分已经用尽，需要归并右半部分元素
            if (i > mid){
                a[k] = aux[j++];
            // 3.2 aux[]右半部分已经用尽，需要操作左半部分元素
            } else if (j > hi){
                a[k] = aux[i++];
            // 3.3 如果a[j] < a[i]，即右边大于左边，那么取右边的当前元素
            } else if (less(aux[j], aux[i])){
                a[k] = aux[j++];
            // 3.4 a[j] > a[i]，取左边的元素
            } else {
                a[k] = aux[i++];
            }
        }
        assert isSorted(a, lo, hi);
    }

    public static void main(String[] args) {
        String[] a = new In(new File(BaseSort.class.getResource("").getPath() + File.separator + "words3.txt")).readAllStrings();
        a = new String[] {"E","E","G","M","R","A","C","E","R","T"};
        BaseSort mergeSort = new MergeSort();
        mergeSort.sort(a).show(a);
    }
    /*public static void main(String[] args) {
//        String[] a = new In(new File(BaseSort.class.getResource("").getPath() + File.separator + "words3.txt")).readAllStrings();
        String[] a = new String[] {"A","E","Q","S"};
        BaseSort mergeSort = new MergeSort();
        mergeSort.sort(a).show(a);
    }
    private static Comparable[] aux;
    @Override
    public BaseSort sort(Comparable[] arr){
        aux = new Comparable[arr.length];
        sort(arr, 0, arr.length - 1);
        assert isSorted(arr);
        return this;
    }

    private  void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid +1;
        for (int k = lo; k <=hi; k++){
            aux[k] = a[k]; // 复制
        }

        for (int k = lo; k <= hi; k++){
            if (i > mid){
                a[k] = aux[j++]; // 左半边用尽
            } else if (j > hi){
                a[k] = aux[i++]; // 右半边用尽
            } else if (less(aux[j], aux[i])){
                a[k] = aux[j++]; // 右半边当前元素 < 左
            } else {
                a[k] = aux[i++];  // 右半边当前元素 > 左
            }
        }
        assert isSorted(a, lo, hi);
    }*/
}
