package lesson5;

public class NewThread implements Runnable {
    private final float[] arr;

    public NewThread(float[] arr) {
        this.arr = arr;
    }

    public float[] getArr() {
        return arr;
    }

    private synchronized void writeToArr() {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    @Override
    public void run() {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
