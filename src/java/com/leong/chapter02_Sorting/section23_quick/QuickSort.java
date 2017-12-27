package com.leong.chapter02_Sorting.section23_quick;

import com.leong.chapter02_Sorting.BaseSort;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.stream.IntStream;

import static edu.princeton.cs.algs4.StdOut.print;
import static edu.princeton.cs.algs4.StdOut.println;

/**
 * 快速排序（O(N lg N)）
 * 快速排序是一种分治的排序算法，它将一个数组分成两个子数组，将两部分独立的排序，与归并排序不同的是，
 * 快速排序是当两个子数组都有序时整个数组也就自然有序了。
 * <p>
 * 快速排序递归将a[lo...hi]排序，关键在于切分：
 * 1. 对于 j, a[j] 位置已经排定不变；
 * 2. a[lo...j-1] 的元素都不大于a[j]；
 * 3. a[j+1...hi] 的元素都不小于a[j]。
 * 切分过程总是能排定一个元素。
 * <p>
 * 切分策略：
 * 1. 随意取a[lo]作为切分元素，此元素将会被排定；
 * 2. 从左端开始向右端扫描（指针为left=lo），直到a[++left]>=a[lo]时break；
 * 3. 然后从右端向左端扫描（指针为right=hi+1），直到a[--right]<=a[lo]时break；
 * 4. 显然a[left]和a[right]两个元素位置还没有排定，因此我们需要交换这两个元素；如此继续，保证左边元素<=切分元素，右边>=切分元素；
 * 5. 当 left 和 right 指针相遇时(left>=right)时，我们只需将a[lo]和左子数组的最右侧元素a[right]交换，然后返回right即可。
 *
 * @author leongfeng created on 2017/4/13
 */
public class QuickSort extends BaseSort {

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        Integer[] arr = new Integer[] {3,44,38,5,47,15};
        println("排序前：");
        sort.show(arr);
        println("排序：");
        sort.sort(arr);
        println("排序后：");
        sort.show(arr);
//        StdOut.println(sort.median3(arr, 0, (arr.length -1) / 2, arr.length - 1));

    }

    @Override
    public BaseSort sort(Comparable[] a) {
        // 2.3.1.3 保持数组的随机性
//        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
        return this;
    }

    /**
     * 递归排序。
     * CUTOFF 约等于 10
     *
     * @param a  数组
     * @param lo 下界
     * @param hi 上界
     */
    private void sort(Comparable[] a, int lo, int hi) {
        // 改进1：对小规模数组使用插入排序
        /**
         if (hi <= lo + CUTOFF -1){
         插入排序
         }
         */
        // 2.3.1.2 不要越界
        if (lo >= hi) {
            return;
        }
        // 改进2：三取样分割
        /**
         int m = median3(a, lo, (hi-lo)/2, hi);
         exchange(a, lo, m);
         */

        // 切分元素下标
        int j = partition(a, lo, hi);
        print("切分元素：" + a[j] + "; 数组：");
        show(a);
        // sorting left
        sort(a, lo, j - 1);
        // right
        sort(a, j + 1, hi);
    }

    /**
     * 取 lo, mid, hi 中的中位数下标。
     * @param a 目标数组
     * @param lo low
     * @param mid middle
     * @param hi high
     * @return median index
     */
    private int median3(Comparable[] a, int lo, int mid, int hi) {
        return (less(a[lo], a[mid]) ?
                (less(a[mid], a[hi]) ? mid : less(a[lo], a[hi]) ? hi : lo) :
                (less(a[hi], a[mid]) ? mid : less(a[hi], a[lo]) ? hi : lo));
    }


    /**
     * 取a[lo]作为切分元素，当left right 指针相遇时退出循环，交换a[lo]和a[right]的位置，返回 right。
     *
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private int partition(Comparable[] a, int lo, int hi) {
        // 取每1个元素作为切分元素
        Comparable v = a[lo];
        int left = lo, right = hi + 1;
        while (true) {
            // 比较左半部分元素，如果元素小于v，不需要作交换，指针向右移动(++left)，直到移动到hi
            while (less(a[++left], v)) {
                if (left == hi) {
                    break;
                }
            }
            // 比较右半部分元素，如果元素大于v，说明不需要作交换，并向左移动指针(--right)；
            while (less(v, a[--right])) {
                if (right == lo) {
                    break;
                }
            }
            // left和right相遇，说明已经排好序了
            if (left >= right) {
                break;
            }
            // 剩下的情况就是需要进行位置互换的了
            exchange(a, left, right);
        }
        // 最后交换v和right位置
        exchange(a, lo, right);
        return right;
    }
}
