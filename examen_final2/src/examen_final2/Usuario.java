package examen_final2;

import java.time.LocalDate;
import java.time.Period;

public class Usuario {
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String dni;

    public Usuario(String nombre, String apellidos, String fechaNacimiento, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = LocalDate.parse(fechaNacimiento);
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDni() {
        return dni;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }

    public int getEdadRecomendada() {
        LocalDate fechaActual = LocalDate.now();
        int edad = Period.between(fechaNacimiento, fechaActual).getYears();
        return edad;
    }
}


