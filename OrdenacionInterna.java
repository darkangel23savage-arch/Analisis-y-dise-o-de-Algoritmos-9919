public class OrdenacionInterna {

    public static void main(String[] args) {
        int[] numeros = {5, 2, 9, 1, 7, 4};

        System.out.println("Arreglo original:");
        mostrarArreglo(numeros);

        bubbleSort(numeros);

        System.out.println("\nArreglo ordenado:");
        mostrarArreglo(numeros);
    }
    public static void bubbleSort(int[] arreglo) {
        int n = arreglo.length;
        boolean intercambio;

        for (int i = 0; i < n - 1; i++) {
            intercambio = false;

            for (int j = 0; j < n - 1 - i; j++) {
                if (arreglo[j] > arreglo[j + 1]) {
                    int temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                    intercambio = true;
                }
            }
            if (!intercambio) {
                break;
            }
        }
    }
    public static void mostrarArreglo(int[] arreglo) {
        for (int num : arreglo) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
