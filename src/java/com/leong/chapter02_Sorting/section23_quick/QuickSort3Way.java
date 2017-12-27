package com.leong.chapter02_Sorting.section23_quick;

import com.leong.chapter02_Sorting.BaseSort;

import static edu.princeton.cs.algs4.StdOut.print;
import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 三向切分快速查找。
 * a[lo...lt-1] 的元素都 <v， a[gt+1...hi]中的元素都 >v，a[i...gt]元素都 =v：
 * 1. a[i] < v，将 a[lt] 和 a[i] 交换，lt ++ 和 i ++；
 * 2. a[i] > v，将 a[gt] 和 a[i] 交换，gt --；
 * 3. a[i] == v， i ++
 * @author leongfeng created on 2017/11/5.
 */
public class QuickSort3Way extends BaseSort{
    @Override
    public BaseSort sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
        assert isSorted(arr);
        return this;
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exchange(a, lt++, i++);
            } else if (cmp > 0) {
                exchange(a, i, gt--);
            } else {
                i ++;
            }
        }
        print("lt：" + lt + "；数组：");
        show(a);
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }


    public static void main(String[] args) {
        BaseSort sort = new QuickSort3Way();
//        Integer[] arr = new Integer[] {43,24,24,14,18,31,37,24,26,32};
        String[] arr = new String[] {"R","W","W","U","X"};
        println("排序前：");
        sort.show(arr);
        println("排序：");
        sort.sort(arr);
        println("排序后：");
        sort.show(arr);
    }
}
