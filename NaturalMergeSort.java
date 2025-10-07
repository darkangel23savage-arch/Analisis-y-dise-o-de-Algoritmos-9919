import java.util.*;

public class NaturalMergeSort {

    public static void main(String[] args) {
        int[] numeros = {5, 8, 12, 3, 7, 9, 1, 4, 10};

        System.out.println("Antes: " + Arrays.toString(numeros));
        naturalMergeSort(numeros);
        System.out.println("Después: " + Arrays.toString(numeros));
    }
    static void naturalMergeSort(int[] arr) {
        int n = arr.length;
        int[] aux = new int[n];
        boolean ordenado = false;
        while (!ordenado) {
            ordenado = true;
            int i = 0;

            while (i < n - 1) {

                int inicio1 = i;
                while (i < n - 1 && arr[i] <= arr[i + 1]) i++;
                int fin1 = i++;

                if (i >= n) break;
                int inicio2 = i;
                while (i < n - 1 && arr[i] <= arr[i + 1]) i++;
                int fin2 = i;

                merge(arr, aux, inicio1, fin1, inicio2, fin2);
                ordenado = false;
            }
        }
    }

    static void merge(int[] arr, int[] aux, int ini1, int fin1, int ini2, int fin2) {
        int i = ini1, j = ini2, k = ini1;
        while (i <= fin1 && j <= fin2)
            aux[k++] = (arr[i] <= arr[j]) ? arr[i++] : arr[j++];
        while (i <= fin1) aux[k++] = arr[i++];
        while (j <= fin2) aux[k++] = arr[j++];
        for (i = ini1; i <= fin2; i++) arr[i] = aux[i];
    }
}
