package com.kaikeba.test;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Fruit("Apple"));
        arrayList.add(new Fruit("Banana"));
        arrayList.add(new Fruit("Orange"));
        arrayList.add(new Fruit("Pineapple"));
        for (Object o : arrayList) {
            System.out.println(o);
        }

    }


    private static class Fruit {
        String fruit;

        public Fruit(String fruit) {
            this.fruit = fruit;
        }
    }
}
