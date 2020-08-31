package lesson2;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.sum;

public class ArrayHandler {

    public int getSumFromArray(String[][] args) throws MyArraySizeException, MyArrayDataException {
        checkArraySize(args);

        int result = 0;

        for (int i = 0; i < args.length; i++) {
            for (int x = 0; x < args[i].length; x++) {
                int num = checkArrayData(args[i][x], i, x);
                result = sum(result, num);
            }
        }

        return result;
    }

    private void checkArraySize(String[][] args) throws MyArraySizeException {
        if(args.length != 4 || args[0].length != 4) {
            throw new MyArraySizeException("Неподходящий размер массива");
        }
    }

    private int checkArrayData(String str, int row, int column) throws MyArrayDataException {
        try {
            return parseInt(str);
        } catch (NumberFormatException e) {
            throw new MyArrayDataException(String.format("Не удалось преобразовать ячейку со значением %s в строке - %d, колонке - %d", str, row+1, column+1));
        }
    }
}
