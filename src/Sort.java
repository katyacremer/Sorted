import java.util.Random;

public class Sort {
    public static void main(String[] args) {
        Random rand = new Random();
        int[][] array = new int[1000][1000];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                int upperbound = 250;
                array[i][j] = rand.nextInt(upperbound);
            }
        }

        Sorted sort = new Sorted();
        sort.choice(array);
        long startchoice = System.nanoTime();
        sort.choice(array);
        long endchoice = System.nanoTime();
        System.out.println("Сортировка выбором: " + (endchoice-startchoice) + " ns");

        sort.insert(array);
        long startinsert = System.nanoTime();
        sort.insert(array);
        long endinsert = System.nanoTime();
        System.out.println("Сортировка вставками: " + (endinsert-startinsert) + " ns");

        sort.exchange(array);
        long startexchange = System.nanoTime();
        sort.exchange(array);
        long endexchange = System.nanoTime();
        System.out.println("Сортировка обменом: " + (endexchange-startexchange) + " ns");

        sort.shell(array);
        long startshell = System.nanoTime();
        sort.shell(array);
        long endshell = System.nanoTime();
        System.out.println("Сортировка Шелла: " + (endshell-startshell) + " ns");

        int low = 0;
        int high = array.length - 1;
        sort.quickSort(array, low, high);
        long startquick = System.nanoTime();
        sort.quickSort(array, low, high);
        long endquick = System.nanoTime();
        System.out.println("Быстрая сортировка: " + (endquick-startquick) + " ns");


    }
}
class Sorted {

    void choice(int array[][]) {
        //сортировка выбором
        for (int i = 0; i < array.length; i++) {// i - номер строки
            for (int j = 0; j < array.length; j++) {
                int pos = j;
                int min = array[i][j]; // цикл выбора наименьшего элемента
                for (int k = j + 1; k < array.length; k++) {
                    if (array[i][k] < min) {
                        pos = k;    // pos - индекс наименьшего элемента
                        min = array[i][k];
                    }
                }
                array[i][pos] = array[i][j];
                array[i][j] = min;    // меняем местами наименьший с array[i]
            }
        }
    }

    void insert(int array[][]) {
        //сортировка вставками
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                int min = j;
                for (int k = j + 1; k < array.length; k++) {
                    if (array[i][k] < array[i][min]) {
                        min = k;
                    }
                }
                int tmp = array[i][j];
                array[i][j] = array[i][min];
                array[i][min] = tmp;
            }
        }
    }

    void exchange(int array[][]) {
        //сортировка обменом
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j >= 1; j--) {  //Внешний цикл
                for (int k = 0; k < j; k++) {       //Внутренний цикл
                    if (array[i][k] > array[i][k + 1]) {
                        int min = array[i][k];      //во временную переменную помещаем первый элемент
                        array[i][k] = array[i][k + 1];       //на место первого ставим второй элемент
                        array[i][k + 1] = min;
                    }
                }
            }
        }
    }

    void shell(int array[][]) {
        //сортировка Шелла
        int temp;
        int h = 0;//величина интервала

        //вычисляем исходное значение интервала
        while (h <= array.length / 3)
            h = 3 * h + 1;

        for (int i = 0; i < array.length; i++) {
            for (int k = h; k > 0; k = (k - 1) / 3)
                for (int j = k; j < array.length; j++) {
                    temp = array[i][j];
                    int a;
                    for (a = j; a >= k; a -= k) {
                        if (temp < array[i][a - k])
                            array[i][a] = array[i][a - k];
                        else
                            break;
                    }
                    array[i][a] = temp;
                }
        }
    }

    void quickSort(int array[][], int low, int high) {
        //быстрая сортировка
        for (int l = 0; l < array.length; l++) {
            if (array.length == 0)
                return;//завершить выполнение, если длина массива равна 0
            if (low >= high)
                return;//завершить выполнение если уже нечего делить
            int middle = low + (high - low) / 2;
            int opora = array[l][middle];
            int i = low, j = high;
            while (i <= j) {
                while (array[l][i] < opora) {
                    i++;
                }

                while (array[l][j] > opora) {
                    j--;
                }
                if (i <= j) {//меняем местами
                    int temp = array[l][i];
                    array[l][i] = array[l][j];
                    array[l][j] = temp;
                    i++;
                    j--;
                }
            }
            // вызов рекурсии для сортировки левой и правой части
            if (low < j)
                quickSort(array, low, j);

            if (high > i)
                quickSort(array, i, high);
        }
    }
}
