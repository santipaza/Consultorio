public class Doctor {
    private String id;
    private String nombre;
    private String especialidad;

    public Doctor(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public String toString() {
        return id + "," + nombre + "," + especialidad;
    }
}
public class Paciente {
    private String id;
    private String nombre;

    public Paciente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return id + "," + nombre;
    }
}

public class Cita {
    private String id;
    private String fechaHora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(String id, String fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return id + "," + fechaHora + "," + motivo + "," + doctor.getId() + "," + paciente.getId();
    }
}
public class Administrador {
    private String id;
    private String contraseña;

    public Administrador(String id, String contraseña) {
        this.id = id;
        this.contraseña = contraseña;
    }

    public String getId() {
        return id;
    }

    public String getContraseña() {
        return contraseña;
    }
