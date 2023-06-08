package examen_final2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalSystemOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testMain() {
        Main.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("Libros por título:"));
        assertTrue(output.contains("Libros por categoría:"));
        assertTrue(output.contains("Libros prestados:"));
        assertTrue(output.contains("Libros disponibles:"));
        assertTrue(output.contains("Libros por usuario:"));
    }

    @Test
    public void testCargarUsuarios() {
        Biblioteca biblioteca = new Biblioteca();
        Main.cargarUsuarios(biblioteca, "usuarios_test.txt");
        assertEquals(2, biblioteca.getUsuarios().size());
    }

    @Test
    public void testCargarLibros() {
        Biblioteca biblioteca = new Biblioteca();
        Main.cargarLibros(biblioteca, "libros_test.txt");
        assertEquals(3, biblioteca.getLibros().size());
    }

    @Test
    public void testCargarUsuarios_InvalidFile() {
        Biblioteca biblioteca = new Biblioteca();
        Main.cargarUsuarios(biblioteca, "usuarios_invalid.txt");
        assertEquals(0, biblioteca.getUsuarios().size());
    }

    @Test
    public void testCargarLibros_InvalidFile() {
        Biblioteca biblioteca = new Biblioteca();
        Main.cargarLibros(biblioteca, "libros_invalid.txt");
        assertEquals(0, biblioteca.getLibros().size());
    }

    @Test
    public void testCargarUsuarios_Exception() {
        Biblioteca biblioteca = new Biblioteca();
        Main.cargarUsuarios(biblioteca, "usuarios_exception.txt");
        assertEquals(0, biblioteca.getUsuarios().size());
    }

    @Test
    public void testCargarLibros_Exception() {
        Biblioteca biblioteca = new Biblioteca();
        Main.cargarLibros(biblioteca, "libros_exception.txt");
        assertEquals(0, biblioteca.getLibros().size());
    }

    @Test
    public void testCargarUsuarios_FileNotFound() {
        Biblioteca biblioteca = new Biblioteca();
        Main.cargarUsuarios(biblioteca, "usuarios_not_found.txt");
        assertEquals(0, biblioteca.getUsuarios().size());
    }

    @Test
    public void testCargarLibros_FileNotFound() {
        Biblioteca biblioteca = new Biblioteca();
        Main.cargarLibros(biblioteca, "libros_not_found.txt");
        assertEquals(0, biblioteca.getLibros().size());
    }

    @Test
    public void testListarLibrosPorTitulo() {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.darDeAltaLibro("El señor de los anillos", "J.R.R. Tolkien", "123", "Aventuras", 12);

        Main.listarLibrosPorTitulo(biblioteca);

        String output = outputStream.toString();
        assertTrue(output.contains("El señor de los anillos"));
    }

    @Test
    public void testListarLibrosPorCategoria() {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.darDeAltaLibro("El señor de los anillos", "J.R.R. Tolkien", "123", "Aventuras", 12);

        Main.listarLibrosPorCategoria(biblioteca);

        String output = outputStream.toString();
        assertTrue(output.contains("Aventuras"));
    }

    @Test
    public void testListarLibrosPrestados() {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.darDeAltaLibro("El señor de los anillos", "J.R.R. Tolkien", "123", "Aventuras", 12);
        biblioteca.prestarLibro("123", "12345678");

        Main.listarLibrosPrestados(biblioteca);

        String output = outputStream.toString();
        assertTrue(output.contains("El señor de los anillos"));
    }

    @Test
    public void testListarLibrosDisponibles() {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.darDeAltaLibro("El señor de los anillos", "J.R.R. Tolkien", "123", "Aventuras", 12);

        Main.listarLibrosDisponibles(biblioteca);

        String output = outputStream.toString();
        assertTrue(output.contains("El señor de los anillos"));
    }

    @Test
    public void testListarLibrosPorUsuario() {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.darDeAltaUsuario("John", "Doe", "2000-01-01", "12345678");
        biblioteca.darDeAltaLibro("El señor de los anillos", "J.R.R. Tolkien", "123", "Aventuras", 12);
        biblioteca.prestarLibro("123", "12345678");

        Main.listarLibrosPorUsuario(biblioteca);

        String output = outputStream.toString();
        assertTrue(output.contains("El señor de los anillos"));
        assertTrue(output.contains("John Doe"));
    }

    @Test
    public void testMenu() {
        Biblioteca biblioteca = new Biblioteca();

        Main.menu(biblioteca, "1");
        assertEquals(1, biblioteca.getUsuarios().size());

        Main.menu(biblioteca, "2");
        assertEquals(1, biblioteca.getLibros().size());

        Main.menu(biblioteca, "3");
        assertTrue(outputStream.toString().contains("Libros por título:"));

        Main.menu(biblioteca, "4");
        assertTrue(outputStream.toString().contains("Libros por categoría:"));

        Main.menu(biblioteca, "5");
        assertTrue(outputStream.toString().contains("Libros prestados:"));

        Main.menu(biblioteca, "6");
        assertTrue(outputStream.toString().contains("Libros disponibles:"));

        Main.menu(biblioteca, "7");
        assertTrue(outputStream.toString().contains("Libros por usuario:"));

        Main.menu(biblioteca, "8");
        assertTrue(outputStream.toString().contains("Saliendo..."));

        Main.menu(biblioteca, "9");
        assertTrue(outputStream.toString().contains("Opción inválida."));

        Main.menu(biblioteca, "invalid");
        assertTrue(outputStream.toString().contains("Opción inválida."));
    }

    @Test
    public void testMain_Exception() {
        Main.main(new String[] { "exception" });

        String output = outputStream.toString();
        assertTrue(output.contains("Se produjo un error al ejecutar la aplicación."));
    }

    @Test
    public void testMain_InvalidArguments() {
        Main.main(new String[] { "invalid" });

        String output = outputStream.toString();
        assertTrue(output.contains("Argumentos inválidos."));
    }

    @Test
    public void testMain_NoArguments() {
        Main.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("Libros por título:"));
        assertTrue(output.contains("Libros por categoría:"));
        assertTrue(output.contains("Libros prestados:"));
        assertTrue(output.contains("Libros disponibles:"));
        assertTrue(output.contains("Libros por usuario:"));
    }

    @Test
    public void testMain_LoadData() {
        Main.main(new String[] { "loaddata", "usuarios_test.txt", "libros_test.txt" });

        String output = outputStream.toString();
        assertTrue(output.contains("Datos cargados exitosamente."));
    }

    @Test
    public void testMain_LoadData_InvalidArguments() {
        Main.main(new String[] { "loaddata", "invalid" });

        String output = outputStream.toString();
        assertTrue(output.contains("Argumentos inválidos."));
    }
}


