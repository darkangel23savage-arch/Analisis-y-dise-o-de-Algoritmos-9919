class Persona {
    private String nombre;
    private int edad;
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    public void saludar() {
        System.out.println("Hola, mi nombre es " + nombre + " y tengo " + edad + " años.");
    }
}
public class Main {
    public static void main(String[] args) {
        Persona persona1 = new Persona("Luis", 20);
        Persona persona2 = new Persona("Andrea", 18);
        persona1.saludar();
        persona2.saludar();
    }
}
