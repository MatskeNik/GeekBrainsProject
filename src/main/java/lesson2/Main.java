package lesson2;

public class Main {

    public static void main(String[] args) {
        String[][] strings = {
                {"5","4","7","8"},
                {"3","10","11f","5"},
                {"7","3","55","101"},
                {"9","67","32","45"}
        };

        try {
            System.out.println("Результат расчета: "+new ArrayHandler().getSumFromArray(strings));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }
}
