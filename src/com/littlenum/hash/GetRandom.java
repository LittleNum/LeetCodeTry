package com.littlenum.hash;

import com.littlenum.AbsTest;

import java.util.*;

/**
 * Created by hero on 2017/11/8.
 */
public class GetRandom extends AbsTest<Object> {
    @Override
    public Object doTest() {
        return null;
    }

    class RandomizedSet {


        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {

        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if(map.containsKey(val)){
                return false;
            }
            map.put(val,list.size());
            list.add(val);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            if(!map.containsKey(val)){
                return false;
            }
            int index = map.get(val);
            if(index < list.size() -1) {
                int last = list.get(list.size() - 1);
                list.set(index, last);
                map.put(last,index);
            }
            list.remove(list.size() -1);
            map.remove(val);
            return true;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            return list.get(random.nextInt(list.size()-1));
        }
    }
}
