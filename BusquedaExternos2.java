import java.util.*;

public class BusquedaExternos2 {

    static List<List<Integer>> bloques = new ArrayList<>();

    public static void main(String[] args) {
        int elementosPorBloque = 5;
        int totalElementos = 25;
        generarBloques(totalElementos, elementosPorBloque);

        Scanner sc = new Scanner(System.in);
        System.out.println("=== SIMULADOR DE ALGORITMOS DE BÚSQUEDA EXTERNA ===");
        mostrarBloques();

        System.out.print("\nIngrese el número que desea buscar: ");
        int valorBuscado = sc.nextInt();

        busquedaSecuencialPorBloques(valorBuscado);

        busquedaSecuencialConIndice(valorBuscado);

        busquedaPorHash(valorBuscado);

        sc.close();
    }

    public static void generarBloques(int total, int porBloque) {
        Random random = new Random();
        List<Integer> todos = new ArrayList<>();

        for (int i = 0; i < total; i++) {
            todos.add(random.nextInt(100));
        }

        Collections.sort(todos);

        for (int i = 0; i < total; i += porBloque) {
            int fin = Math.min(i + porBloque, total);
            bloques.add(new ArrayList<>(todos.subList(i, fin)));
        }
    }

    public static void mostrarBloques() {
        System.out.println("\nDatos simulados en bloques:");
        for (int i = 0; i < bloques.size(); i++) {
            System.out.println("Bloque " + i + ": " + bloques.get(i));
        }
    }

    public static void busquedaSecuencialPorBloques(int valor) {
        System.out.println("\n[1] Búsqueda Secuencial por Bloques:");
        int comparaciones = 0;
        for (int i = 0; i < bloques.size(); i++) {
            for (int dato : bloques.get(i)) {
                comparaciones++;
                if (dato == valor) {
                    System.out.println("✔ Valor encontrado en el bloque " + i + " tras " + comparaciones + " comparaciones.");
                    return;
                }
            }
        }
        System.out.println("✘ Valor no encontrado. Comparaciones: " + comparaciones);
    }

    public static void busquedaSecuencialConIndice(int valor) {
        System.out.println("\n[2] Búsqueda Secuencial con Índice:");
        List<Integer> indice = new ArrayList<>();
        for (List<Integer> bloque : bloques) {
            indice.add(bloque.get(bloque.size() - 1));
        }

        System.out.println("Índice de bloques: " + indice);
        int comparaciones = 0;
        int bloqueSeleccionado = -1;

        for (int i = 0; i < indice.size(); i++) {
            comparaciones++;
            if (valor <= indice.get(i)) {
                bloqueSeleccionado = i;
                break;
            }
        }

        if (bloqueSeleccionado == -1) {
            System.out.println("✘ Valor mayor a todos los índices. No encontrado.");
            return;
        }

        for (int dato : bloques.get(bloqueSeleccionado)) {
            comparaciones++;
            if (dato == valor) {
                System.out.println("✔ Valor encontrado en bloque " + bloqueSeleccionado + " tras " + comparaciones + " comparaciones.");
                return;
            }
        }

        System.out.println("✘ Valor no encontrado. Comparaciones: " + comparaciones);
    }

    public static void busquedaPorHash(int valor) {
        System.out.println("\n[3] Búsqueda por Transformación de Claves (Hash):");

        int tamañoTabla = 10;
        List<List<Integer>> tablaHash = new ArrayList<>();
        for (int i = 0; i < tamañoTabla; i++) tablaHash.add(new ArrayList<>());

        for (List<Integer> bloque : bloques) {
            for (int dato : bloque) {
                int hash = dato % tamañoTabla;
                tablaHash.get(hash).add(dato);
            }
        }

        int clave = valor % tamañoTabla;
        System.out.println("Clave hash generada: " + clave);
        System.out.println("Contenido de la posición " + clave + ": " + tablaHash.get(clave));

        int comparaciones = 0;
        for (int dato : tablaHash.get(clave)) {
            comparaciones++;
            if (dato == valor) {
                System.out.println("✔ Valor encontrado en el bucket hash " + clave + " tras " + comparaciones + " comparaciones.");
                return;
            }
        }

        System.out.println("✘ Valor no encontrado. Comparaciones: " + comparaciones);
    }
}
