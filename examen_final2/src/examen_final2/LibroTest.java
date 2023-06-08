package examen_final2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibroTest {
    @Test
    public void testPrestarLibro() {
        Libro libro = new Libro("El señor de los anillos", "J.R.R. Tolkien", "123", "Aventuras", 12);
        Usuario usuario = new Usuario("John", "Doe", "2000-01-01", "12345678");

        assertTrue(libro.prestar(usuario));
        assertTrue(libro.isPrestado());
        assertEquals(usuario, libro.getUsuarioPrestamo());
    }

    @Test
    public void testDevolverLibro() {
        Libro libro = new Libro("El señor de los anillos", "J.R.R. Tolkien", "123", "Aventuras", 12);
        Usuario usuario = new Usuario("John", "Doe", "2000-01-01", "12345678");

        libro.prestar(usuario);
        assertTrue(libro.isPrestado());

        libro.devolver();
        assertFalse(libro.isPrestado());
        assertNull(libro.getUsuarioPrestamo());
    }
}
