package examen_final2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BibliotecaTest {
    private Biblioteca biblioteca;

    @BeforeEach
    public void setUp() {
        biblioteca = new Biblioteca();
        biblioteca.darDeAltaUsuario("John", "Doe", "2000-01-01", "12345678");
        biblioteca.darDeAltaLibro("El señor de los anillos", "J.R.R. Tolkien", "123", "Aventuras", 12);
    }

    @Test
    public void testDarDeAltaUsuario() {
        List<Usuario> usuarios = biblioteca.getUsuarios();
        assertEquals(1, usuarios.size());

        Usuario usuario = usuarios.get(0);
        assertEquals("John", usuario.getNombre());
        assertEquals("Doe", usuario.getApellidos());
        assertEquals("2000-01-01", usuario.getFechaNacimiento().toString());
        assertEquals("12345678", usuario.getDni());
    }

    @Test
    public void testDarDeAltaLibro() {
        List<Libro> libros = biblioteca.getLibros();
        assertEquals(1, libros.size());

        Libro libro = libros.get(0);
        assertEquals("El señor de los anillos", libro.getTitulo());
        assertEquals("J.R.R. Tolkien", libro.getAutor());
        assertEquals("123", libro.getIdentificador());
        assertEquals("Aventuras", libro.getCategoria());
        assertEquals(12, libro.getEdadRecomendada());
    }

    @Test
    public void testPrestarLibro() {
        boolean resultado = biblioteca.prestarLibro("123", "12345678");
        assertTrue(resultado);
    }

    @Test
    public void testDevolverLibro() {
        biblioteca.prestarLibro("123", "12345678");
        boolean resultado = biblioteca.devolverLibro("123");
        assertTrue(resultado);
    }
}