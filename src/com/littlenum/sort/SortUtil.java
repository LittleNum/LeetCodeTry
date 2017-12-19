package com.littlenum.sort;

/**
 * Created by hero on 2017/12/19.
 */
public class SortUtil {
    /**
     * 插入排序：利用每次交换前0到p-1上的元素已经处于排过序的状态；
     * 第p趟，将位置p上元素向左移动，直到在前p+1个元素中找到正确的位置。
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        int j;
        for (int p = 1; p < a.length; p++) {
            T tmp = a[p];
            for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    public static <T extends Comparable<? super T>> void insertionSort(T[] a, int left, int right) {
        if (a == null || a.length == 0) {
            return;
        }
        int j;
        for (int p = left; p <= right; p++) {
            T tmp = a[p];
            for (j = p; j > left && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    /**
     * 希尔排序：通过比较相聚一定间隔的元素来工作；
     * 各趟比较所用的距离随着算法的进行而减小；
     * 直到只比较相邻元素的最后一趟排序为止。
     * 也叫 缩减增量排序
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void shellSort(T[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        int j;
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                T tmp = a[i];
                for (j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = tmp;
            }
        }
    }

    /**
     * 堆排序：利用二叉堆的buildHeap，以及deleteMin的复杂度为O(N log N)
     * 考虑到使用deleteMin会依赖一个辅助数组存储最小值
     * 所以直接创建一个max堆，并把最大值放在最后，再进行下滤，维持堆序
     * 在剩下的数组中重复操作，节省了空间
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void heapSort(T[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        //构建二叉堆，max堆
        for (int i = a.length / 2; i >= 0; i--) {
            percDown(a, i, a.length);
        }
        //首先把最大值放在最后一个，最后的值放在第一个
        //执行下滤操作，把原本的最后一个值放在合适的位置
        //在剩下的length-1个值中重复上述操作
        //节省了辅助数组
        for (int i = a.length - 1; i >= 0; i--) {
            T tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            percDown(a, 0, i);
        }
    }

    /**
     * 此处二叉堆从序号0开始，所以左子节点为2 * parent + 1
     * 如果从1开始，则为2 * parent
     *
     * @param i
     * @return
     */
    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * 构建二叉堆（max堆，最大值在第一个位置）
     * 下滤操作，把小的值放下去
     *
     * @param a
     * @param i
     * @param n
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void percDown(T[] a, int i, int n) {
        int child;
        T tmp;
        for (tmp = a[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0) {
                child++;
            }
            if (tmp.compareTo(a[child]) < 0) {
                a[i] = a[child];
            } else {
                break;
            }
        }
        a[i] = tmp;
    }

    /**
     * 归并排序：通过递归将已排序的两个表进行合并
     * 会用到辅助数组，以及数组拷贝的时间消耗
     * 由于java的Comparator不容易内嵌，导致动态调用的开销会减慢速度
     *
     * @param a
     * @param tmpArray
     * @param left
     * @param right
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] tmpArray, int left, int right) {
        if (a == null || a.length == 0) {
            return;
        }
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    public static <T extends Comparable<? super T>> void merge(T[] a, T[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) < 0) {
                tmpArray[tmpPos++] = a[leftPos++];
            } else {
                tmpArray[tmpPos++] = a[rightPos++];
            }
        }
        while (leftPos <= leftEnd) {
            tmpArray[leftPos++] = a[leftPos];
        }
        while (rightPos <= rightEnd) {
            tmpArray[rightPos++] = a[rightPos];
        }
        for (int i = 0; i < numElements; i++, rightEnd--) {
            a[rightEnd] = tmpArray[rightEnd];
        }
    }

    private static <T extends Comparable<? super T>> void swap(T[] a, int m, int n) {
        T tmp = a[m];
        a[m] = a[n];
        a[n] = tmp;
    }

    public static <T extends Comparable<? super T>> void quickSort(T[] a) {
        quickSort(a, 0, a.length - 1);
    }

    /**
     * 三数中值分割法，确保枢纽元不是最大或最小，减少比较次数
     *
     * @param a
     * @param left
     * @param right
     * @param <T>
     * @return
     */
    private static <T extends Comparable<? super T>> T pivot(T a[], int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0) {
            swap(a, center, left);
        }
        if (a[right].compareTo(a[left]) < 0) {
            swap(a, left, right);
        }
        if (a[right].compareTo(a[center]) < 0) {
            swap(a, center, right);
        }
        swap(a, center, right - 1);
        return a[right - 1];
    }

    /**
     * 快速排序：分治算法
     * 1.取集合S中的一个元素v，作为枢纽元
     * 2.将S-{v}划分为两个不相交的集合S1，S2,使S1中的元素都小于v，S2中的元素都大于v
     * 3.返回[quickSort(s1),v,quickSort(s2)]
     *
     * @param a
     * @param left
     * @param right
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] a, int left, int right) {
        if (left + 10 <= right) {
            T pivot = pivot(a, left, right);
            int i = left;
            int j = right - 1;
            for (; ; ) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {
                }
                if (i < j) {
                    swap(a, i, j);
                } else {
                    break;
                }
            }
            swap(a, i, right - 1);
            quickSort(a, left, i - 1);
            quickSort(a, i + 1, right);
        } else {
            insertionSort(a, left, right);
        }
    }
}
