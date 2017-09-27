package com.tts.chapter02.chapter2_2;

import com.tts.chapter02.BaseSort;

/**
 * @author: mike
 * @since: 2017/4/10
 */
public class MergeSort extends BaseSort{
    public static void main(String[] args) {
//        String[] a = new In(new File(BaseSort.class.getResource("").getPath() + File.separator + "words3.txt")).readAllStrings();
        String[] a = new String[] {"A","E","Q","S"};
        BaseSort mergeSort = new MergeSort();
        mergeSort.sort(a).show(a);
    }
    private static Comparable[] aux;
    @Override
    public BaseSort sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
        assert isSorted(a);
        return this;
    }

    private  void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
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
    }
}
