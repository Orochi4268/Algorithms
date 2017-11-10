package com.leong.chapter02_Sorting.section24_PriorityQueue;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 索引优先队列
 *
 * @author leongfeng created on 2017/11/8.
 */
public class IndexMaxPQ<Key extends Comparable<Key>> extends BasePriorityQueue<Key> implements Iterable<Integer> {
    /**
     * binary heap using 1-based indexing.
     */
    private int[] pq;
    /**
     * inverse of pq - qp[pq[i]] = pq[qp[i]] = i.
     */
    private int[] qp;
    /**
     * keys[i] = priority of i.
     */
    private Key[] keys;
    /**
     * number of elements on PQ.
     */
    private int n = 0;

    public IndexMaxPQ(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        keys = (Key[]) new Comparable[capacity + 1];
        pq = new int[capacity + 1];
        qp = new int[capacity + 1];
        for (int i = 0; i <= capacity; i++) {
            qp[i] = -1;
        }
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }


    public boolean contains(int i) {
        return qp[i] != -1;
    }

    public void insert(int i, Key key) {
        if (contains(i)) {
            throw new IllegalArgumentException("index is already in the priority queue.");
        }
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    /**
     * Returns an index associated with a maxium key.
     *
     * @return an index associated with a maximum key
     * @throws NoSuchElementException if priority is empty
     */
    public int maxIndex() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return qp[1];
    }

    /**
     * Returns a maxium key.
     *
     * @return a maxium key
     * @throws NoSuchElementException if priority is empty
     */
    public Key maxKey() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return keys[pq[1]];
    }

    /**
     * Removes an index associated with a maxium key.
     *
     * @return an index associated with a maxium key
     * @throws NoSuchElementException if priority is empty
     */
    public int delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        int min = pq[1];
        exch(1, n--);
        sink(1);

        assert pq[n + 1] == min;
        qp[min] = -1;
        keys[min] = null;
        pq[n + 1] = -1;
        return min;
    }

    /**
     * Returns the key associated with index {@code i}.
     *
     * @param i the index of the key to return
     * @return the key associated with index {@code i}
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException   no key is associated with index {@code i}
     */
    public Key keyOf(int i) {
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        return keys[i];
    }
    /**
     * Change the key associated with index {@code i} to the specified value.
     *
     * @param  i the index of the key to change
     * @param  key change the key associated with index {@code i} to this key
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     */
    public void changeKey(int i, Key key) {
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        keys[i] = key;
        swim(pq[i]);
        sink(qp[i]);
    }

    /**
     * Increase the key associated with index {@code i} to the specified value.
     *
     * @param i   the index of the key to increase
     * @param key increase the key associated with index {@code i} to this key
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws IllegalArgumentException if {@code key <= keyOf(i)}
     * @throws NoSuchElementException   no key is associated with index {@code i}
     */
    public void increaseKey(int i, Key key) {
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        if (keys[i].compareTo(key) >= 0) {
            throw new IllegalArgumentException("Calling increaseKey() with given argument would not strictly increase the key");
        }

        keys[i] = key;
        swim(qp[i]);
    }

    /**
     * Decrease the key associated with index {@code i} to the specified value.
     *
     * @param  i the index of the key to decrease
     * @param  key decrease the key associated with index {@code i} to this key
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws IllegalArgumentException if {@code key >= keyOf(i)}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void decreaseKey(int i, Key key) {
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        if (keys[i].compareTo(key) <= 0)
        {
            throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");
        }

        keys[i] = key;
        sink(qp[i]);
    }

    /**
     * Remove the key on the priority queue associated with index {@code i}.
     *
     * @param  i the index of the key to remove
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void delete(int i) {
        if (!contains(i)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }



    /***************************************************************************
     * General helper functions.
     ***************************************************************************/

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer>{

        private IndexMaxPQ<Key> copy;

        public HeapIterator(){
            copy = new IndexMaxPQ<>(pq.length - 1);
            for (int i = 1; i <= n; i ++){
                copy.insert(pq[i], keys[pq[i]]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Integer next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            return copy.delMax();
        }
    }

    public static void main(String[] args) {
        String[] strings = {"it", "was", "the", "best", "of", "times", "it", "was", "the", "worst"};

        IndexMaxPQ<String> pq = new IndexMaxPQ<>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // print each key using the iterator
        for (int i : pq) {
            StdOut.println(i + " " + strings[i]);
        }

        StdOut.println();
        // increase or decrease the key
        for (int i = 0; i < strings.length; i++) {
            if (StdRandom.uniform() < 0.5) {
                pq.increaseKey(i, strings[i] + strings[i]);

            } else {
                pq.decreaseKey(i, strings[i].substring(0, 1));
            }
        }

        // delete and print each key
        while (!pq.isEmpty()) {
            String key = pq.maxKey();
            int i = pq.delMax();
            StdOut.println(i + " " + key);
        }
        StdOut.println();

        // reinsert the same strings
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // delete them in random order
        int[] perm = new int[strings.length];
        for (int i = 0; i < strings.length; i++)
        {
            perm[i] = i;
        }
        StdRandom.shuffle(perm);
        for (int i = 0; i < perm.length; i++) {
            String key = pq.keyOf(perm[i]);
            pq.delete(perm[i]);
            StdOut.println(perm[i] + " " + key);
        }
    }
}
