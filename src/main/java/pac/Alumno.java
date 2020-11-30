package pac;

import java.util.Set;

public class Alumno {

    private long id;
    private String nombre;
    private String nacionalidad;
    private int edad;
    private String sexo;
    //private Set<pac.Modulo> alumno_modulo = new HashSet<pac.Modulo>();
    private Set<Modulo> alumno_modulo;

    public Alumno(){}

    public Alumno(String nombre, String nacionalidad, int edad, String sexo, Set<Modulo> alumno_modulo) {
        //this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
        this.sexo = sexo;
        this.alumno_modulo = alumno_modulo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Set<Modulo> getAlumno_modulo() {
        return alumno_modulo;
    }

    public void setAlumno_modulo(Set<Modulo> alumno_modulo) {
        this.alumno_modulo = alumno_modulo;
    }

    @Override
    public String toString() {
        return "pac.Alumno: nombre=" + nombre + ", nacionalidad=" + nacionalidad
                + ", edad=" + edad + ", sexo=" + sexo + ", modulos=" + alumno_modulo.toString();
    }
}
