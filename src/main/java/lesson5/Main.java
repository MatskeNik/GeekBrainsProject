package lesson5;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        doSomethingWithArray();
        doSomethingWithArray2();
    }

    private static void doSomethingWithArray() {
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        for(int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - a);
    }

    private static void doSomethingWithArray2() {
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        Thread newThread1 = new Thread(new NewThread(a1));
        Thread newThread2 = new Thread(new NewThread(a2));
        try {
            newThread1.start();
            newThread1.join();
            newThread2.start();
            newThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println(System.currentTimeMillis() - a);
    }
}
