/*
 * Copyright (c) 2023. Programacion Avanzada, DISC, UCN.
 */

package cl.ucn.disc.pa.bibliotech;
import cl.ucn.disc.pa.bibliotech.services.Sistema;
import edu.princeton.cs.stdlib.StdIn;
import edu.princeton.cs.stdlib.StdOut;
import java.io.IOException;
import java.util.Objects;

/**
 * The Main.
 *
 * @author Programacion Avanzada.
 */
public final class Main {

    /**
     * The main.
     *
     * @param args to use.
     * @throws IOException en caso de un error.
     */
    public static void main(final String[] args) throws IOException {

        // inicializacion del sistema.
        Sistema sistema = new Sistema();

        StdOut.println(sistema.obtegerCatalogoLibros());

        String opcion = null;
        while (!Objects.equals(opcion, "2")) {

            StdOut.println("""
                    [*] Bienvenido a BiblioTech [*]
                                    
                    [1] Iniciar Sesion
                    [2] Salir
                    """);
            StdOut.print("Escoja una opcion: ");
            opcion = StdIn.readLine();

            switch (opcion) {
                case "1" -> iniciarSesion(sistema);
                case "2" -> StdOut.println("¡Hasta Pronto!");
                default -> StdOut.println("Opcion no valida, intente nuevamente");
            }
        }
    }

    /**
     * Inicia la sesion del Socio en el Sistema.
     *
     * @param sistema a utilizar.
     */
    private static void iniciarSesion(final Sistema sistema) throws IOException {
        StdOut.println("[*] Iniciar sesion en BiblioTech [*]");
        StdOut.print("Ingrese su numero de socio: ");
        int numeroSocio = StdIn.readInt();
        StdIn.readLine();

        StdOut.print("Ingrese su contrasenia: ");
        String contrasenia = StdIn.readLine();

        // intento el inicio de session
        try {
            sistema.iniciarSession(numeroSocio, contrasenia);
        } catch (IllegalArgumentException ex) {
            StdOut.println("Ocurrio un error: " + ex.getMessage());
            return;
        }

        // mostrar menu principal
        menuPrincipal(sistema);
    }

    private static void menuPrincipal(final Sistema sistema) throws IOException {
        String opcion = null;
        while (!Objects.equals(opcion, "4")) {
            StdOut.println("""
                    [*] BiblioTech [*]
                                        
                    [1] Prestamo de un libro
                    [2] Editar información
                    [3] Calificar libro
                                        
                    [4] Cerrar sesion
                    """);

            StdOut.print("Escoja una opcion: ");
            opcion = StdIn.readLine();

            switch (opcion) {
                case "1" -> menuPrestamo(sistema);
                case "2" -> editarInformacion(sistema);
                case "3" -> calificarLibro(sistema);
                case "4" -> sistema.cerrarSession();
                default -> StdOut.println("Opcion no valida, intente nuevamente");
            }
        }
    }

    private static void calificarLibro(Sistema sistema) throws IOException {
        StdOut.println("[*] Calificar un libro [*]");
        StdOut.println(sistema.obtegerCatalogoLibros());

        StdOut.println ("ingrese ISBN del libro a calificar:");
        String isbn = StdIn.readLine ();
        
        
        while (true) {
                StdOut.println ("Como calificas el libro (1 a 5 estrellas)");
                int Calificacion = StdIn.readInt ();
            if(Calificacion>=0&&Calificacion<=5) {
                sistema.calificarLibro (isbn , Calificacion);
                break;
            }else {
                StdOut.println ("El valor ingresado no corresponde al formato indicado!");
            }
        }


    }


    private static void menuPrestamo(Sistema sistema) {
        StdOut.println("[*] Préstamo de un Libro [*]");
        StdOut.println(sistema.obtegerCatalogoLibros());

        StdOut.print("Ingrese el ISBN del libro a tomar prestado: ");
        String isbn = StdIn.readLine();

        try {
            sistema.realizarPrestamoLibro(isbn);
        } catch (IOException ex) {
            StdOut.println("Ocurrio un error, intente nuevamente: " + ex.getMessage());
        }
    }

    private static void editarInformacion(Sistema sistema) throws IOException {

        String opcion = null;
        while (!Objects.equals(opcion, "5")) {

            StdOut.println("[*] Editar Perfil [*]");
            StdOut.println(sistema.obtenerDatosSocioLogeado());
            StdOut.println("""               
                    [1] Editar Nombre
                    [2] Editar Apellido
                    [3] Editar correo Electronico
                    [4] Editar Contraseña
                                                            
                    [5] Volver atrás
                    """);
            StdOut.print("Escoja una opción: ");
            opcion = StdIn.readString ();

            switch (opcion) {
                case "1" -> editarNombre(sistema);
                case "2" -> editarApellido(sistema);
                case "3" -> EditarcorreoElectronico(sistema);
                case "4" -> cambiarContrasenia(sistema);
                case "5" -> StdOut.println("Volviendo al menú anterior...");
                default  -> StdOut.println("Opcion no valida, intente nuevamente");
            }

        }
    }
    private static void editarNombre(Sistema sistema) throws IOException {
        StdOut.println("[*] Editar Nombre [*]");
        StdOut.println(sistema.obtenerDatosSocioLogeado());
        StdOut.println ("ingresar el nuevo nombre de usuario");
        String Nombre = StdIn.readString ();
        sistema.editarNombre(Nombre);

    }
    private static void editarApellido(Sistema sistema) throws IOException {
        StdOut.println("[*] Editar Apellido [*]");
        StdOut.println(sistema.obtenerDatosSocioLogeado());
        StdOut.println ("ingresar el nuevo Apellido de usuario");
        String Apellido = StdIn.readString ();
        sistema.editarApellido(Apellido);
    }
    private static void EditarcorreoElectronico(Sistema sistema) throws IOException {
        StdOut.println("[*] Editar Correo Electronico [*]");
        StdOut.println(sistema.obtenerDatosSocioLogeado());
        StdOut.println ("ingresar el nuevo Correo Electronico de usuario");
        String correoElectronico = StdIn.readString ();
        sistema.editarCorreoElectronico(correoElectronico);
    }

    private static void cambiarContrasenia(Sistema sistema) throws IOException {
        StdOut.println("[*] Editar Contrasenia [*]");
        StdOut.println(sistema.obtenerDatosSocioLogeado());
        StdOut.println ("ingresar la nueva contrasenia de usuario");
        String Contrasenia = StdIn.readString ();
        sistema.editarContrasenia(Contrasenia);

    }
}