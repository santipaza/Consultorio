# Consultorio

import java.io.*;
import java.util.*;
public class Consultorio {

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
}
public class SistemaCitas {
    private static List<Doctor> doctores = new ArrayList<>();
    private static List<Paciente> pacientes = new ArrayList<>();
    private static List<Cita> citas = new ArrayList<>();
    private static List<Administrador> administradores = new ArrayList<>();

    public static void main(String[] args) {
        cargarDatos();
        Scanner scanner = new Scanner(System.in);

        if (verificarAdministrador(scanner)) {
            int opcion;
            do {
                System.out.println("\nMenu:");
                System.out.println("1. Dar de alta Doctor");
                System.out.println("2. Dar de alta Paciente");
                System.out.println("3. Crear Cita");
                System.out.println("4. Listar Citas");
                System.out.println("5. Salir");
                System.out.print("Elija una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        darAltaDoctor(scanner);
                        break;
                    case 2:
                        darAltaPaciente(scanner);
                        break;
                    case 3:
                        crearCita(scanner);
                        break;
                    case 4:
                        listarCitas();
                        break;
                    case 5:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } while (opcion != 5);
        } else {
            System.out.println("Acceso denegado.");
        }
        scanner.close();
    }

    private static boolean verificarAdministrador(Scanner scanner) {
        System.out.print("Ingrese su identificador de administrador: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contraseña = scanner.nextLine();

        for (Administrador admin : administradores) {
            if (admin.getId().equals(id) && admin.getContraseña().equals(contraseña)) {
                return true;
            }
        }
        return false;
    }

    private static void cargarDatos() {
        // Cargar datos desde archivos CSV
        cargarAdministradores();
        cargarDoctores();
        cargarPacientes();
        cargarCitas();
    }

    private static void cargarAdministradores() {
        try (BufferedReader br = new BufferedReader(new FileReader("Administrador.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                administradores.add(new Administrador(data[0], data[1]));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar administradores.");
        }
    }

    private static void cargarDoctores() {
        try (BufferedReader br = new BufferedReader(new FileReader("Doctor.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                doctores.add(new Doctor(data[0], data[1], data[2]));  
            }
        } catch (IOException e) {
            System.out.println("Error al cargar doctores.");
        }
    }

    private static void cargarPacientes() {
        try (BufferedReader br = new BufferedReader(new FileReader("Paciente.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                pacientes.add(new Paciente(data[0], data[1]));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar pacientes.");
        }
    }

    private static void cargarCitas() {
        try (BufferedReader br = new BufferedReader(new FileReader("Cita.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Doctor doctor = encontrarDoctorPorId(data[3]);
                Paciente paciente = encontrarPacientePorId(data[4]);
                if (doctor != null && paciente != null) {
                    citas.add(new Cita(data[0], data[1], data[2], doctor, paciente));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar citas.");
        }
    }

    private static Doctor encontrarDoctorPorId(String id) {
        for (Doctor doctor : doctores) {
            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }
        return null;
    }

    private static Paciente encontrarPacientePorId(String id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }

    private static void darAltaDoctor(Scanner scanner) {
        System.out.print("Ingrese el ID del doctor: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el nombre completo del doctor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la especialidad del doctor: ");
        String especialidad = scanner.nextLine();
        doctores.add(new Doctor(id, nombre, especialidad));
        guardarDoctores();
    }

    private static void darAltaPaciente(Scanner scanner) {
        System.out.print("Ingrese el ID del paciente: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el nombre completo del paciente: ");
        String nombre = scanner.nextLine();
        pacientes.add(new Paciente(id, nombre));
        guardarPacientes();
    }

    private static void crearCita(Scanner scanner) {
        System.out.print("Ingrese el ID de la cita: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese la fecha y hora de la cita (dd/MM/yyyy HH:mm): ");
        String fechaHora = scanner.nextLine();
        System.out.print("Ingrese el motivo de la cita: ");
        String motivo = scanner.nextLine();

        System.out.print("Ingrese el ID del doctor: ");
        String doctorId = scanner.nextLine();
        Doctor doctor = encontrarDoctorPorId(doctorId);

        System.out.print("Ingrese el ID del paciente: ");
        String pacienteId = scanner.nextLine();
        Paciente paciente = encontrarPacientePorId(pacienteId);

        if (doctor != null && paciente != null) {
            Cita cita = new Cita(id, fechaHora, motivo, doctor, paciente);
            citas.add(cita);
            guardarCitas();
        } else {
            System.out.println("Doctor o paciente no encontrado.");
        }
    }

    private static void listarCitas() {
        for (Cita cita : citas) {
            System.out.println("Cita ID: " + cita.toString());
        }
    }

    private static void guardarDoctores() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Doctor.csv"))) {
            for (Doctor doctor : doctores) {
                bw.write(doctor.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar doctores.");
        }
    }

    private static void guardarPacientes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Paciente.csv"))) {
            for (Paciente paciente : pacientes) {
                bw.write(paciente.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar pacientes.");
        }
    }

    private static void guardarCitas() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Cita.csv"))) {
            for (Cita cita : citas) {
                bw.write(cita.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar citas.");
        }
    }
}
}
