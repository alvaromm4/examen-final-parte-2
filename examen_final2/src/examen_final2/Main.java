package examen_final2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Cargar usuarios desde el archivo usuarios.txt
        cargarUsuarios(biblioteca, "usuarios.txt");

        // Cargar libros desde el archivo libros.txt
        cargarLibros(biblioteca, "libros.txt");

        // Listar libros por título
        System.out.println("Libros por título:");
        biblioteca.listarLibrosPorTitulo();

        // Listar libros por categoría
        System.out.println("Libros por categoría:");
        biblioteca.listarLibrosPorCategoria();

        // Listar libros prestados
        System.out.println("Libros prestados:");
        biblioteca.listarLibrosPrestados();

        // Listar libros disponibles
        System.out.println("Libros disponibles:");
        biblioteca.listarLibrosDisponibles();

        // Listar libros por usuario
        System.out.println("Libros por usuario:");
        biblioteca.listarLibrosPorUsuario();
    }

    private static void cargarUsuarios(Biblioteca biblioteca, String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    String nombre = datos[0];
                    String apellidos = datos[1];
                    String fechaNacimiento = datos[2];
                    String dni = datos[3];
                    biblioteca.darDeAltaUsuario(nombre, apellidos, fechaNacimiento, dni);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar usuarios desde el archivo: " + e.getMessage());
        }
    }

    private static void cargarLibros(Biblioteca biblioteca, String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    String titulo = datos[0];
                    String[] autores = datos[1].split(";");
                    String identificador = datos[2];
                    String categoria = datos[3];
                    int edadRecomendada = Integer.parseInt(datos[4]);
                    biblioteca.darDeAltaLibro(titulo, autores, identificador, categoria, edadRecomendada);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar libros desde el archivo: " + e.getMessage());
        }
    }
}
