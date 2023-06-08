package examen_final2;

public class Libro {
    private String titulo;
    private String[] autores;
    private String identificador;
    private String categoria;
    private int edadRecomendada;
    private boolean prestado;
    private Usuario usuarioPrestamo;

    public Libro(String titulo, String[] autores, String identificador, String categoria, int edadRecomendada) {
        this.titulo = titulo;
        this.autores = autores;
        this.identificador = identificador;
        this.categoria = categoria;
        this.edadRecomendada = edadRecomendada;
        this.prestado = false;
        this.usuarioPrestamo = null;
    }

    public String getTitulo() {
        return titulo;
    }

    public String[] getAutores() {
        return autores;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getEdadRecomendada() {
        return edadRecomendada;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public Usuario getUsuarioPrestamo() {
        return usuarioPrestamo;
    }

    public void prestar(Usuario usuario) {
        if (!prestado) {
            prestado = true;
            usuarioPrestamo = usuario;
            System.out.println("El libro '" + titulo + "' ha sido prestado a " + usuario.getNombreCompleto());
        } else {
            System.out.println("El libro '" + titulo + "' no está disponible en este momento");
        }
    }

    public void devolver() {
        if (prestado) {
            prestado = false;
            usuarioPrestamo = null;
            System.out.println("El libro '" + titulo + "' ha sido devuelto");
        } else {
            System.out.println("El libro '" + titulo + "' no estaba prestado");
        }
    }
}

