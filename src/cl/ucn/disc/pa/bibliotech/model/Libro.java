/*
 * Copyright (c) 2023. Programacion Avanzada, DISC, UCN.
 */

package cl.ucn.disc.pa.bibliotech.model;

/**
 * Clase que representa un Libro.
 *
 * @author Programacion Avanzada.
 */
public final class Libro {

    /**
     * The ISBN.
     */
    private final String isbn;

    /**
     * The Titulo.
     */
    private final String titulo;

    /**
     * The Author.
     */
    private final String autor;

    /**
     * The Categoria
     */
    private final String categoria;
    /**
     * The calificacion.
     */
    private double promCalificacion;


    private double calificacion;

    private double contCalificacion;

    private int cantLibro;


    public void setContCalificacion(double contCalificacion) {
        this.contCalificacion = contCalificacion;
    }

    /**
     * The Constructor.
     *
     * @param isbn      del libro.
     * @param titulo    del libro.
     * @param autor     del libro
     * @param categoria del libro.
     * @param calificacion del libro.
     *
     */
    public Libro(final String isbn, final String titulo, final String autor, final String categoria,final double calificacion,final int contCalificacion,final double Promcalificacion, final int cantLibro) {

        // TODO: agregar validacion de ISBN
        if (isbn == null || isbn.length() == 0) {
            throw new IllegalArgumentException("Isbn no valida!");
        }
        this.isbn = isbn;

        // validacion del titulo
        if (titulo == null || titulo.length() == 0) {
            throw new IllegalArgumentException("Titulo no valido!");
        }

        this.titulo = titulo;

        if (autor == null || autor.length() == 0) {
            throw new IllegalArgumentException("Autor no valido!");
        }
        // TODO: Agregar validacion

        this.autor = autor;

        // TODO: Agregar validacion
        if (categoria == null || categoria.length() == 0) {
            throw new IllegalArgumentException("Categoria no valida!");
        }
        this.categoria = categoria;

        this.calificacion = calificacion;

        this.contCalificacion= contCalificacion;

        this.promCalificacion = Promcalificacion;

        this.cantLibro = cantLibro;
    }

    /**
     * @return the ISBN.
     */
    public String getIsbn() {
        return this.isbn;
    }

    /**
     * @return the titulo.
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * @return the autor.
     */
    public String getAutor() {
        return this.autor;
    }

    /**
     * @return the categoria.
     */
    public String getCategoria() {
        return this.categoria;
    }

    /**
     * @return the Calificacion.
     */
    public double getPromCalificacion() {return this.promCalificacion;}
    /**
     * @set the Calificacion.
     *
     */
    public double getContCalificacion() {
        return contCalificacion;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public int getCantLibro() {
        return cantLibro;
    }
    public void setCantLibro(int cantLibro) {
      this.cantLibro= cantLibro;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public void setPromCalificacion(double promCalificacion) {
        this.promCalificacion = promCalificacion;
    }

}
