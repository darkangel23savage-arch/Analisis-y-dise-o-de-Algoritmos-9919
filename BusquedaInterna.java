import java.util.LinkedList;

public class BusquedaInterna {
    public static void main(String[] args) {
        int[] datos = {15, 27, 36, 49, 50, 63, 75};
        int tamaño = 10;

        System.out.println("=== HASHING CON ENCADENAMIENTO ===");
        HashEncadenamiento hash1 = new HashEncadenamiento(tamaño);
        for (int x : datos) hash1.insertar(x);
        hash1.mostrar();

        System.out.println("\n=== HASHING CON LINEAR PROBING ===");
        HashLineal hash2 = new HashLineal(tamaño);
        for (int x : datos) hash2.insertar(x);
        hash2.mostrar();

        System.out.println("\n=== HASHING CON QUADRATIC PROBING ===");
        HashCuadratico hash3 = new HashCuadratico(tamaño);
        for (int x : datos) hash3.insertar(x);
        hash3.mostrar();
    }
}

class HashEncadenamiento {
    private LinkedList<Integer>[] tabla;

    public HashEncadenamiento(int tamaño) {
        tabla = new LinkedList[tamaño];
        for (int i = 0; i < tamaño; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    private int hash(int clave) {
        return clave % tabla.length;
    }

    public void insertar(int clave) {
        int indice = hash(clave);
        tabla[indice].add(clave);
    }

    public void mostrar() {
        for (int i = 0; i < tabla.length; i++) {
            System.out.println(i + " → " + tabla[i]);
        }
    }
}

class HashLineal {
    private Integer[] tabla;

    public HashLineal(int tamaño) {
        tabla = new Integer[tamaño];
    }

    private int hash(int clave) {
        return clave % tabla.length;
    }

    public void insertar(int clave) {
        int i = hash(clave);
        while (tabla[i] != null) {
            i = (i + 1) % tabla.length;
        }
        tabla[i] = clave;
    }

    public void mostrar() {
        for (int i = 0; i < tabla.length; i++) {
            System.out.println(i + " → " + tabla[i]);
        }
    }
}

class HashCuadratico {
    private Integer[] tabla;

    public HashCuadratico(int tamaño) {
        tabla = new Integer[tamaño];
    }

    private int hash(int clave) {
        return clave % tabla.length;
    }

    public void insertar(int clave) {
        int i = hash(clave);
        int k = 1;
        while (tabla[i] != null) {
            i = (i + k * k) % tabla.length;
            k++;
        }
        tabla[i] = clave;
    }

    public void mostrar() {
        for (int i = 0; i < tabla.length; i++) {
            System.out.println(i + " → " + tabla[i]);
        }
    }
}
