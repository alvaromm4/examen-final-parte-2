package examen_final2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {
    @Test
    public void testGetNombreCompleto() {
        Usuario usuario = new Usuario("John", "Doe", "2000-01-01", "12345678");

        assertEquals("John Doe", usuario.getNombreCompleto());
    }

    @Test
    public void testGetEdadRecomendada() {
        Usuario usuario = new Usuario("John", "Doe", "2000-01-01", "12345678");

        int edadRecomendada = usuario.getEdadRecomendada();
        assertEquals(21, edadRecomendada);
    }
}
