package Lab12;

import java.util.Arrays;

public class Lab12 {

    static final int SIZE = 1000000;
    static final int HALF_SIZE = SIZE/2;
    static float [] arr = new float[SIZE];

    public static void doWithoutThreads(){
        long a = System.currentTimeMillis();
        Arrays.fill(arr,1);

        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] *
                              Math.sin(0.2f + i / 5) *
                              Math.cos(0.2f + i / 5) *
                              Math.cos(0.4f + i / 2));
        }

        System.out.println("Время работы метода без потоков " + (System.currentTimeMillis() - a));

    }

    public static void doWithThreads(){
        long a = System.currentTimeMillis();
        Arrays.fill(arr,1);

        float[] a1 = new float[HALF_SIZE];
        float[] a2 = new float[HALF_SIZE];

        System.arraycopy(arr,0,a1,0,HALF_SIZE);
        System.arraycopy(arr,HALF_SIZE,a2,0,HALF_SIZE);

        ArraysFill firstThread = new ArraysFill(a1, 0);
        ArraysFill secondThread = new ArraysFill(a2, HALF_SIZE);
        firstThread.start();
        secondThread.start();

        try{
            firstThread.join();
            secondThread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, HALF_SIZE);
        System.arraycopy(a2, 0, arr, HALF_SIZE, HALF_SIZE);

        System.out.println("Время работы метода c потоками " + (System.currentTimeMillis() - a));
    }

    public static void main(String[] args) {
        doWithoutThreads();
        doWithThreads();
    }
}
