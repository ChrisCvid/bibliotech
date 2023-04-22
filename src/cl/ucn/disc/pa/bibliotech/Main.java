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
        // Se muestra el catálogo de libros disponibles en el sistema
        StdOut.println(sistema.obtegerCatalogoLibros());
        // Variable para almacenar la opción seleccionada por el usuario
        String opcion = null;

        // Bucle que se repite mientras el usuario no haya seleccionado la opción "2" (salir)
        while (!Objects.equals(opcion, "2")) {

            // Se muestra el menú principal de la aplicación
            StdOut.println("""
                    [*] Bienvenido a BiblioTech [*]
                                    
                    [1] Iniciar Sesion
                    [2] Salir
                    """);
            StdOut.print("Escoja una opcion: ");
            opcion = StdIn.readLine();

            // Se utiliza switch para ejecutar el código correspondiente a la opción seleccionada
            switch (opcion) {
                case "1" -> iniciarSesion(sistema);
                case "2" -> StdOut.println("¡Hasta Pronto!");
                default -> StdOut.println("Opcion no valida, intente nuevamente");
            }
        }
    }

    /**
     * Inicia la sesion del Socio en el Sistema.
     *Método que se encarga de iniciar la sesión del usuario en el sistema.
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

    /**
     * Metodo para mostrar el menú
     * @param sistema a utilizar
     */
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
                case "1" -> menuPrestamo(sistema); // Llama a la función de préstamo de un libro
                case "2" -> editarInformacion(sistema); // Llama a la función de edición de información de perfil
                case "3" -> calificarLibro(sistema); // Llama a la función de calificación de un libro
                case "4" -> sistema.cerrarSession(); // Cierra la sesión del usuario
                default -> StdOut.println("Opcion no valida, intente nuevamente"); // Si la opción es inválida, imprime un mensaje de error
            }
        }
    }

    /**
     *Método para calificar un libro.
     *@param sistema Objeto de la clase Sistema que representa el sistema de la biblioteca.
     *@throws IOException Si ocurre un error de entrada o salida durante la ejecución del método.
     */
    private static void calificarLibro(Sistema sistema) throws IOException {
        // Mostrar mensaje de bienvenida y catálogo de libros
        StdOut.println("[*] Calificar un libro [*]");
        StdOut.println(sistema.obtegerCatalogoLibros());

        // Solicitar al usuario el ISBN del libro a calificar
        StdOut.println ("ingrese ISBN del libro a calificar:");
        String isbn = StdIn.readLine ();

        // Solicitar al usuario la calificación del libro y validar que esté en el rango permitido
        while (true) {
                StdOut.println ("Como calificas el libro (1 a 5 estrellas)");
                int Calificacion = StdIn.readInt ();
            if(Calificacion>=0&&Calificacion<=5) {
                // Si la calificación es válida, se califica el libro y se sale del bucle
                sistema.calificarLibro (isbn , Calificacion);
                break;
            }else {
                // Si la calificación no está entre el 1 y el 5, se muestra un mensaje de error y se solicita una nueva calificación
                StdOut.println ("El valor ingresado no corresponde al formato indicado!");
            }
        }
    }


    /**
     * Metodo para desplegar el menú de préstamo
     * @param sistema a utilizar
     */
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

    /**
     *Metodo que permite la edición de la información del perfil de un socio en el sistema.
     *@param sistema en el que se encuentra el socio logeado
     @throws IOException si ocurre algún error
     */
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

    /**
     * @param sistema a utilizar
     * @throws IOException si hay un error
     */
    private static void editarNombre(Sistema sistema) throws IOException {
        StdOut.println("[*] Editar Nombre [*]");
        StdOut.println(sistema.obtenerDatosSocioLogeado());
        StdOut.println ("ingresar el nuevo nombre de usuario");
        String Nombre = StdIn.readString ();
        // Llamar al método editarNombre del sistema para actualizar con el nombre ingresado
        sistema.editarNombre(Nombre);
    }

    /**
     * @param sistema a utilizar
     * @throws IOException si hay un erros
     */
    private static void editarApellido(Sistema sistema) throws IOException {
        StdOut.println("[*] Editar Apellido [*]");
        StdOut.println(sistema.obtenerDatosSocioLogeado());
        StdOut.println ("ingresar el nuevo Apellido de usuario");
        String Apellido = StdIn.readString ();
        //Llama al método editarApellido del sistema para actualizar con el apellido ingresado
        sistema.editarApellido(Apellido);
    }

    /**
     * @param sistema a utilizar
     * @throws IOException
     */
    private static void EditarcorreoElectronico(Sistema sistema) throws IOException {
        StdOut.println("[*] Editar Correo Electronico [*]");
        StdOut.println(sistema.obtenerDatosSocioLogeado());
        StdOut.println ("ingresar el nuevo Correo Electronico de usuario");
        String correoElectronico = StdIn.readString ();
        //Llama al método editarCorreoElectronico del sistema para actualizar con el correo ingresado
        sistema.editarCorreoElectronico(correoElectronico);
    }

    /**
     * @param sistema a utilizar
     * @throws IOException si hay algún error
     */
    private static void cambiarContrasenia(Sistema sistema) throws IOException {
        StdOut.println("[*] Editar Contrasenia [*]");
        StdOut.println(sistema.obtenerDatosSocioLogeado());
        StdOut.println ("ingresar la nueva contrasenia de usuario");
        String Contrasenia = StdIn.readString ();
        //Llama al método editarCorreoElectronico del sistema para actualizar con el correo ingresado
        sistema.editarContrasenia(Contrasenia);

    }
}