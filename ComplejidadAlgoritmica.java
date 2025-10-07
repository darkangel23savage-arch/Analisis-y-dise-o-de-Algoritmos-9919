import java.util.Arrays;
import java.util.Random;

public class ComplejidadAlgoritmica {

    public static void main(String[] args) {
        int n = 10000;
        int[] arreglo = generarArregloAleatorio(n);

        System.out.println("Arreglo generado con " + n + " elementos.");

        long inicioOrden = System.nanoTime();
        Arrays.sort(arreglo);
        long finOrden = System.nanoTime();

        System.out.println("Tiempo de ordenamiento: " + (finOrden - inicioOrden) / 1_000_000.0 + " ms");

        int objetivo = arreglo[new Random().nextInt(n)];

        long inicioBusqueda = System.nanoTime();
        int resultado = busquedaBinaria(arreglo, objetivo);
        long finBusqueda = System.nanoTime();

        if (resultado != -1) {
            System.out.println("Elemento encontrado en la posición: " + resultado);
        } else {
            System.out.println("Elemento no encontrado.");
        }

        System.out.println("Tiempo de búsqueda: " + (finBusqueda - inicioBusqueda) / 1_000_000.0 + " ms");

        System.out.println("\n--- Complejidad Algorítmica ---");
        System.out.println("Generación de datos: O(n)");
        System.out.println("Ordenamiento (Arrays.sort): O(n log n)");
        System.out.println("Búsqueda binaria: O(log n)");
        System.out.println("Complejidad total aproximada: O(n log n)");
    }

    public static int[] generarArregloAleatorio(int n) {
        Random random = new Random();
        int[] arreglo = new int[n];
        for (int i = 0; i < n; i++) {
            arreglo[i] = random.nextInt(1_000_000);
        }
        return arreglo;
    }

    public static int busquedaBinaria(
            int[] arreglo,
            int objetivo) {
        int izquierda = 0;
        int derecha = arreglo.length - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            if (arreglo[medio] == objetivo) {
                return medio;
            } else if (arreglo[medio] < objetivo) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return -1;
    }
}

