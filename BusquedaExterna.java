import java.io.*;
import java.util.Scanner;

public class BusquedaExterna {
    public static void main(String[] args) {
        String nombreArchivo = "datos.txt";
        crearArchivo(nombreArchivo);

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingresa el número que deseas buscar: ");
        int numeroBuscado = sc.nextInt();

        boolean encontrado = buscarEnArchivo(nombreArchivo, numeroBuscado);

        if (encontrado)
            System.out.println(" El número " + numeroBuscado + " fue encontrado en el archivo.");
        else
            System.out.println(" El número " + numeroBuscado + " NO se encuentra en el archivo.");
    }
    static void crearArchivo(String nombreArchivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
            pw.println("10");
            pw.println("25");
            pw.println("37");
            pw.println("49");
            pw.println("55");
            pw.println("63");
            pw.println("72");
            pw.println("89");
            System.out.println("Archivo de datos creado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }
    static boolean buscarEnArchivo(String nombreArchivo, int numeroBuscado) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                int numero = Integer.parseInt(linea);
                if (numero == numeroBuscado)
                    return true;
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return false;
    }
}
