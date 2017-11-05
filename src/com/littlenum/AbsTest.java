package com.littlenum;

/**
 * Created by hero on 2017/7/13.
 */
public abstract class AbsTest<T> {

    public void test() {
        long start = System.currentTimeMillis();
        System.out.println("answer:" + doTest());
        long end = System.currentTimeMillis();
        System.out.println("spend:" + (end - start) + "ms\r\n");
    }

    public abstract T doTest();

}
