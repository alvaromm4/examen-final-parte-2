package examen_final2;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Libro> libros;
    private List<Usuario> usuarios;

    public Biblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public Biblioteca(List<Libro> libros, List<Usuario> usuarios) {
        this.libros = libros;
        this.usuarios = usuarios;
    }

    public void darDeAltaLibro(String titulo, String[] autores, String identificador, String categoria, int edadRecomendada) {
        Libro libro = new Libro(titulo, autores, identificador, categoria, edadRecomendada);
        libros.add(libro);
        System.out.println("El libro '" + titulo + "' ha sido dado de alta");
    }

    public void darDeAltaUsuario(String nombre, String apellidos, String fechaNacimiento, String dni) {
        Usuario usuario = new Usuario(nombre, apellidos, fechaNacimiento, dni);
        usuarios.add(usuario);
        System.out.println("El usuario '" + usuario.getNombreCompleto() + "' ha sido dado de alta");
    }

    public void prestarLibro(String identificador, String dni) {
        Libro libro = buscarLibroPorIdentificador(identificador);
        if (libro != null) {
            Usuario usuario = buscarUsuarioPorDni(dni);
            if (usuario != null) {
                libro.prestar(usuario);
            } else {
                System.out.println("No se encontró un usuario con el DNI especificado");
            }
        } else {
            System.out.println("No se encontró un libro con el identificador especificado");
        }
    }

    public void devolverLibro(String identificador) {
        Libro libro = buscarLibroPorIdentificador(identificador);
        if (libro != null) {
            libro.devolver();
        } else {
            System.out.println("No se encontró un libro con el identificador especificado");
        }
    }

    public void listarLibrosPorTitulo() {
        System.out.println("Lista de libros por título:");
        for (Libro libro : libros) {
            System.out.println(libro.getTitulo());
        }
    }

    public void listarLibrosPorCategoria() {
        System.out.println("Lista de libros por categoría:");
        for (Libro libro : libros) {
            System.out.println(libro.getTitulo() + " - " + libro.getCategoria());
        }
    }

    public void listarLibrosPrestados() {
        System.out.println("Lista de libros prestados:");
        for (Libro libro : libros) {
            if (libro.isPrestado()) {
                System.out.println(libro.getTitulo() + " - Prestado a: " + libro.getUsuarioPrestamo().getNombreCompleto());
            }
        }
    }

    public void listarLibrosDisponibles() {
        System.out.println("Lista de libros disponibles:");
        for (Libro libro : libros) {
            if (!libro.isPrestado()) {
                System.out.println(libro.getTitulo());
            }
        }
    }

    public void listarLibrosPorUsuario() {
        System.out.println("Lista de libros por usuario:");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNombreCompleto() + ":");
            for (Libro libro : libros) {
                if (libro.isPrestado() && libro.getUsuarioPrestamo() == usuario) {
                    System.out.println("- " + libro.getTitulo());
                }
            }
        }
    }

    private Libro buscarLibroPorIdentificador(String identificador) {
        for (Libro libro : libros) {
            if (libro.getIdentificador().equals(identificador)) {
                return libro;
            }
        }
        return null;
    }

    private Usuario buscarUsuarioPorDni(String dni) {
        for (Usuario usuario : usuarios) {
            if (usuario.getDni().equals(dni)) {
                return usuario;
            }
        }
        return null;
    }
}
