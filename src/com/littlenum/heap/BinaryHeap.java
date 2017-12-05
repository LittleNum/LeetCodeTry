package com.littlenum.heap;

/**
 * Created by hero on 2017/12/4.
 */
public class BinaryHeap<T extends Comparable<? super T>> {
    private static final int DEFAULT_CAPACITY = 10;

    private int mCurrentSize;
    private T[] mItems;

    public BinaryHeap() {

    }

    public BinaryHeap(int capacity) {

    }

    public BinaryHeap(T[] items) {
        mCurrentSize = items.length;
        mItems = (T[]) new Comparable[(mCurrentSize + 2) * 11 / 10];
        int i = 1;
        for (T item : items) {
            mItems[i++] = item;
        }
        buildHeap();
    }

    //插入，上滤，将插入节点放在最后，与父比较，小于父，则取代父节点
    public void insert(T t) {
        if (mCurrentSize == mItems.length - 1) {
            enlargeArray(mItems.length * 2 + 1);
        }
        int hole = ++mCurrentSize;
        for (; hole > 1 && t.compareTo(mItems[hole / 2]) < 0; hole = hole / 2) {
            mItems[hole] = mItems[hole / 2];
        }
        mItems[hole] = t;
    }

    public T findMin() {
        return mItems[1];
    }

    //删除最小，下滤
    public T delteMin() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        T min = findMin();
        mItems[1] = mItems[mCurrentSize--];
        percolateDown(1);
        return min;
    }

    public boolean isEmpty() {
        return mCurrentSize <= 0;
    }

    public void makeEmpty() {
        mCurrentSize = 0;
        mItems = (T[]) new Comparable[DEFAULT_CAPACITY + 1];
    }

    //下滤，子节点关键字小于父，取左右最小的取代父
    private void percolateDown(int hole) {
        int child;
        T tmp = mItems[hole];
        for (; hole * 2 <= mCurrentSize; hole = child) {
            child = hole * 2;
            if (child != mCurrentSize && mItems[child + 1].compareTo(mItems[child]) < 0) {
                child++;
            }
            if (mItems[child].compareTo(tmp) < 0) {
                mItems[hole] = mItems[child];
            } else {
                break;
            }
        }
        mItems[hole] = tmp;
    }

    //通过下滤进行构造
    private void buildHeap() {
        //叶子不需要下滤
        for (int i = mCurrentSize / 2; i > 0; i--) {
            percolateDown(i);
        }
    }

    private void enlargeArray(int newSize) {
        T[] array = (T[]) new Comparable[newSize];
        System.arraycopy(mItems, 0, array, 0, 1 + mCurrentSize);
        mItems = array;
        mCurrentSize = newSize;
    }
}
