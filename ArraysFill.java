package Lab12;

class ArraysFill extends Thread {
    private float []array;
    private int shift;
    public  ArraysFill(float[]array, int shift){
        this.array = array;
        this.shift = shift;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] *
                    Math.sin(0.2f + (i + shift) / 5) *
                    Math.cos(0.2f + (i + shift)  / 5) *
                    Math.cos(0.4f + (i + shift)  / 2));
        }
    }
}

