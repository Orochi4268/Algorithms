package com.leong.chapter02_Sorting.section23_quick;

import com.leong.chapter02_Sorting.BaseSort;

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

    }


    public static void main(String[] args) {

    }
}
