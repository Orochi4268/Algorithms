package com.tts.chapter02.chapter2_1;

import com.tts.chapter02.BaseSort;
import std.lib.In;

import java.io.File;

/**
 * @author: mike
 * @since: 2017/4/9
 */
public class ShellSort extends BaseSort {
    public static void main(String[] args) {
        BaseSort shellSort = new ShellSort();
        String[] a = new In(new File(BaseSort.class.getResource("").getPath() + File.separator + "words3.txt")).readAllStrings();
        shellSort.sort(a).show(a);
    }

    private boolean isHsorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++)
            if (less(a[i], a[i - h])) return false;
        return true;
    }

    @Override
    public BaseSort sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1; // 1, 4, 13...
        }

        while (h >= 1) {
            //
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }

            }
            assert isHsorted(a, h);
            h /= 3;
        }
        assert isSorted(a);
        return this;
    }
}
