/*
 * Copyright (c) 2023. Programacion Avanzada, DISC, UCN.
 */

package cl.ucn.disc.pa.bibliotech.model;

import cl.ucn.disc.pa.bibliotech.services.Utils;
import edu.princeton.cs.stdlib.StdOut;

/**
 * Clase que representa a un Socio.
 *
 * @author Programacion Avanzada.
 */
public final class Socio {

    /**
     * Numero maximo de libros que puede tener el Socio.
     */
    private static final int NUMERO_LIBROS_MAXIMO = 5;

    /**
     * Nombre del socio.
     */

    private String nombre;

    /**
     * Apellido del socio.
     */
    private String apellido;

    /**
     * Email del socio.
     */
    private String correoElectronico;

    /**
     * Numero del socio.
     */
    private final int numeroDeSocio;

    /**
     * Contrasenia del socio.
     */
    private String contrasenia;

    /**
     * Libros que el Socio tiene en prestamo (maximo 10).
     */
    private final Libro[] librosEnPrestamo = new Libro[10];

    /**
     * The Constructor.
     *
     * @param nombre            del socio.
     * @param apellido          del socio.
     * @param correoElectronico del socio.
     * @param numeroDeSocio     del socio.
     * @param contrasenia       del socio.
     */
    public Socio(String nombre, String apellido, String correoElectronico, int numeroDeSocio, String contrasenia) {

        if (nombre == null || nombre.length() == 0) {
            throw new IllegalArgumentException("nombre no valido!");
        }
        this.nombre = nombre;

        if (apellido == null || apellido.length() == 0) {
            throw new IllegalArgumentException("apellido no valido!");
        }
        this.apellido = apellido;

        // metodo estatico para validacion de email.
        Utils.validarEmail(correoElectronico);
        this.correoElectronico = correoElectronico;

        if (numeroDeSocio <= 0) {
            throw new IllegalArgumentException("Numero de socio no valido!");
        }
        this.numeroDeSocio = numeroDeSocio;

        if (contrasenia == null || contrasenia.length() == 0) {
            throw new IllegalArgumentException("contrasenia no valido!");
        }
        this.contrasenia = contrasenia;
    }

    /**
     * Getter
     * @return el nombre completo del Socio.
     */
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }

    /**
     * Getter
     * @return el correo electronico del Socio.
     */
    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    /**
     * Getter
     * @return el numero del Socio.
     */
    public int getNumeroDeSocio() {
        return this.numeroDeSocio;
    }

    /**
     * Getter
     * @return la contrasenia del Socio.
     */
    public String getContrasenia() {
        return this.contrasenia;
    }


    /**
     * Agrega un libro en prestamo al Socio.
     * @param libro a agregar.
     */
    public void agregarLibro(final Libro libro) {

            // agrego el libro
            for (int i = 0 ; i < librosEnPrestamo.length ; i++) {
                if (librosEnPrestamo[ i ] == null) {
                    this.librosEnPrestamo[ i ] = libro;
                    StdOut.println ("Prestamo del libro realizado.");
                    break;
                }else if(librosEnPrestamo[i]!=null&&i==4) {
                    // validacion
                    throw new IllegalArgumentException ("El Socio ya tiene la maxima cantidad de libros en prestamo: " + NUMERO_LIBROS_MAXIMO);
                }
            }
        }



    /**
     * @param nombre del socio.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param apellido del socio.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @param correoElectronico del socio.
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @param contrasenia del socio.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
