/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.practica_ddsi.practica_ddsi;

import Config.HibernateUtil;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Modelo.*;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.FormatProcessor;

/**
 *
 * @author jmdom
 */
public class Practica_ddsi {

    public static void menu() {
        for (int i = 0; i < 3; i++);
        System.out.println("\n");

        System.out.println(" ---- ----  MENU  ---- ---- ");
        System.out.println("1. Informacion completa de los socios (HQL)");
        System.out.println("2. Informacion completa de los socios (SQL)");
        System.out.println("3. Informacion completa de los socios (Consulta Nombrada)");
        System.out.println("4. Nombres y telefonos de todos los socios");
        System.out.println("5. Socios por categoria");
        System.out.println("6. Monitor por nick");
        System.out.println("7. Informacion de un socio por su nombre");
        System.out.println("8. Informacion de actividades por dia y cuota");
        System.out.println("9. Informacion de los socios por categoria (Consulta nombrada HQL)");
        System.out.println("10. Informacion de los socios por categoria (Consulta nombrada SQL)");
        System.out.println("11. Insertar un Socio");
        System.out.println("12. Eliminar un socio por DNI");
        System.out.println("13. Informacion de las actividades de la que es respondable un monitor (DNI)");
        System.out.println("0. Salir ");

        for (int i = 0; i < 3; i++);
        System.out.println("\n");

        System.out.print(">>  ");
    }

    public static void main(String[] args) {
        // abrimos una sesion para hacer las consultas
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // abrimos un scanner
        Scanner in = new Scanner(System.in);

        int el = 0;

        // limpio consola
        for (int i = 0; i < 40; i++) {
            System.out.println("\n");
        }

        do {
            // sale el menu y pido eleccion 
            menu();
            el = in.nextInt();

            switch (el) {
                case 0:
                    System.out.println("");
                    System.out.println("Saliendo del programa ...");
                    break;
                case 1:
                    Session session = sessionFactory.openSession();
                    Transaction tr = session.beginTransaction();

                    // hacemos la consulta en el bloque try-catch
                    try {
                        Query query = session.createQuery("FROM Socio s", Socio.class);
                        ArrayList<Socio> socios = (ArrayList<Socio>) query.getResultList();

                        for (Socio socio : socios) {
                            System.out.println("Numero socio: " + socio.getNumeroSocio() + ", Nombre: " + socio.getNombre()
                                    + ", DNI: " + socio.getDni() + ", Fecha de Nacimiento: " + socio.getFechaNacimiento()
                                    + ", Telefono: " + socio.getTelefono() + ", Correo: " + socio.getCorreo()
                                    + ", Fecha de Entrada: " + socio.getFechaEntrada() + ", Categoria: " + socio.getCategoria());
                        }
                        tr.commit();

                    } catch (Exception ex) {
                        tr.rollback();
                        System.out.println("ERROR: No se ha podido finalizar la consulta -> " + ex.getMessage());
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }

                    break;

                case 2:
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();

                    try {
                        Query query = session.createNativeQuery("SELECT * FROM SOCIO s", Socio.class);
                        ArrayList<Socio> socios = (ArrayList<Socio>) query.getResultList();

                        for (Socio socio : socios) {
                            System.out.println("Numero socio: " + socio.getNumeroSocio() + ", Nombre: " + socio.getNombre()
                                    + ", DNI: " + socio.getDni() + ", Fecha de Nacimiento: " + socio.getFechaNacimiento()
                                    + ", Telefono: " + socio.getTelefono() + ", Correo: " + socio.getCorreo()
                                    + ", Fecha de Entrada: " + socio.getFechaEntrada() + ", Categoria: " + socio.getCategoria());
                        }
                        tr.commit();

                    } catch (Exception ex) {
                        tr.rollback();
                        System.out.println("ERROR: No se ha podido finalizar la consulta -> " + ex.getMessage());
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }

                    break;

                case 3:
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();

                    try {
                        Query query = session.createNamedQuery("Socio.findAll", Socio.class);
                        ArrayList<Socio> socios = (ArrayList<Socio>) query.getResultList();

                        for (Socio socio : socios) {
                            System.out.println("Numero socio: " + socio.getNumeroSocio() + ", Nombre: " + socio.getNombre()
                                    + ", DNI: " + socio.getDni() + ", Fecha de Nacimiento: " + socio.getFechaNacimiento()
                                    + ", Telefono: " + socio.getTelefono() + ", Correo: " + socio.getCorreo()
                                    + ", Fecha de Entrada: " + socio.getFechaEntrada() + ", Categoria: " + socio.getCategoria());
                        }
                        tr.commit();

                    } catch (Exception ex) {
                        tr.rollback();
                        System.out.println("ERROR: No se ha podido finalizar la consulta -> " + ex.getMessage());
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }

                    break;

                case 4:
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();

                    try {
                        Query query = session.createQuery("SELECT s.nombre, s.telefono FROM Socio s");
                        ArrayList<Object[]> socios = (ArrayList<Object[]>) query.list();

                        for (Object[] socio : socios) {
                            System.out.println("Nombre: " + socio[0] + ", Telefono: " + socio[1]);
                        }
                        tr.commit();

                    } catch (Exception ex) {
                        tr.rollback();
                        System.out.println("ERROR: No se ha podido finalizar la consulta -> " + ex.getMessage());
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }

                    break;

                case 5:
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();

                    // solicito los datos
                    in.nextLine();
                    System.out.print("Introduce la categoria de busqueda: ");
                    String cat = in.nextLine().toUpperCase();
                    char categoria = cat.charAt(0);
                    try {
                        Query query = session.createQuery("SELECT s.nombre, s.categoria FROM Socio s WHERE s.categoria = :categoria").setParameter("categoria", categoria);
                        ArrayList<Object[]> sociosPorCat = (ArrayList<Object[]>) query.list();

                        for (Object[] socio : sociosPorCat) {
                            System.out.println("Nombre: " + socio[0] + ", Categoria: " + socio[1]);
                        }
                        tr.commit();

                    } catch (Exception ex) {
                        tr.rollback();
                        System.out.println("ERROR: No se ha podido finalizar la consulta -> " + ex.getMessage());
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }

                    break;

                case 6:
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();

                    System.out.print("Introduce el nick del monitor: ");
                    in.nextLine();
                    String nick = in.nextLine();

                    try {
                        Query query = session.createQuery("SELECT m.nombre FROM Monitor m WHERE m.nick=:nick").setParameter("nick", nick);
                        String nomMonitor = (String) query.getSingleResult();

                        System.out.println("Nombre del monitor: " + nomMonitor);
                        tr.commit();

                    } catch (Exception ex) {
                        tr.rollback();
                        System.out.println("ERROR: No se ha podido finalizar la consulta -> " + ex.getMessage());
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }
                    break;

                case 7:
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();

                    System.out.print("Introduce el nombre del socio: ");
                    in.nextLine();
                    String nombre = in.nextLine();

                    try {
                        Query query = session.createNamedQuery("Socio.findByNombre").setParameter("nombre", nombre);
                        Socio socio = (Socio) query.getSingleResult();

                        System.out.println("Numero socio: " + socio.getNumeroSocio() + ", Nombre: " + socio.getNombre()
                                + ", DNI: " + socio.getDni() + ", Fecha de Nacimiento: " + socio.getFechaNacimiento()
                                + ", Telefono: " + socio.getTelefono() + ", Correo: " + socio.getCorreo()
                                + ", Fecha de Entrada: " + socio.getFechaEntrada() + ", Categoria: " + socio.getCategoria());
                        tr.commit();

                    } catch (Exception ex) {
                        tr.rollback();
                        System.out.println("ERROR: No se ha podido finalizar la consulta -> " + ex.getMessage());
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }
                    break;

                case 8:
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();

                    in.nextLine();
                    System.out.print("Introduce el dia de la semana: ");
                    String dia = in.nextLine();
                    System.out.print("Introduce la cuota minima: ");
                    int precioBaseMes = in.nextInt();

                    try {
                        Query query = session.createQuery("FROM Actividad a WHERE a.dia=:dia AND a.precioBaseMes>=:precioBaseMes").setParameter("dia", dia).setParameter("precioBaseMes", precioBaseMes);
                        ArrayList<Actividad> actividades = (ArrayList<Actividad>) query.getResultList();

                        for (Actividad actividad : actividades) {
                            System.out.println("idActividad: " + actividad.getIdActividad() + ", Nombre: " + actividad.getNombre()
                                    + ", Dia " + actividad.getDia() + ", Hora: " + actividad.getHora() + ", Descripcion: "
                                    + actividad.getDescripcion() + ", Precio base: " + actividad.getPrecioBaseMes() + ", Monitor Responsable: "
                                    + actividad.getMonitorResponsable());
                        }
                        tr.commit();

                    } catch (Exception ex) {
                        tr.rollback();
                        System.out.println("ERROR: No se ha podido finalizar la consulta -> " + ex.getMessage());
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }
                    break;

                case 9:
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();

                    in.nextLine();
                    System.out.print("Introduce la categoria de busqueda: ");
                    cat = in.nextLine().toUpperCase();
                    categoria = cat.charAt(0);
                    try {
                        Query query = session.createNamedQuery("Socio.findByCategoria").setParameter("categoria", categoria);
                        ArrayList<Socio> socios = (ArrayList<Socio>) query.getResultList();

                        for (Socio socio : socios) {
                            System.out.println("Numero socio: " + socio.getNumeroSocio() + ", Nombre: " + socio.getNombre()
                                    + ", DNI: " + socio.getDni() + ", Fecha de Nacimiento: " + socio.getFechaNacimiento()
                                    + ", Telefono: " + socio.getTelefono() + ", Correo: " + socio.getCorreo()
                                    + ", Fecha de Entrada: " + socio.getFechaEntrada() + ", Categoria: " + socio.getCategoria());
                        }

                        tr.commit();

                    } catch (Exception ex) {
                        tr.rollback();
                        System.out.println("ERROR: No se ha podido finalizar la consulta -> " + ex.getMessage());
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }

                    break;

                case 10:
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();

                    in.nextLine();
                    System.out.print("Introduzca la categoria de busqueda: ");
                    cat = in.nextLine().toUpperCase();
                    categoria = cat.charAt(0);
                    try {
                        Query query = session.createNamedQuery("Socio.findByCategoriaNativo", Socio.class).setParameter("categoria", categoria);
                        ArrayList<Socio> socios = (ArrayList<Socio>) query.getResultList();

                        for (Socio socio : socios) {
                            System.out.println("Numero socio: " + socio.getNumeroSocio() + ", Nombre: " + socio.getNombre()
                                    + ", DNI: " + socio.getDni() + ", Fecha de Nacimiento: " + socio.getFechaNacimiento()
                                    + ", Telefono: " + socio.getTelefono() + ", Correo: " + socio.getCorreo()
                                    + ", Fecha de Entrada: " + socio.getFechaEntrada() + ", Categoria: " + socio.getCategoria());
                        }
                        tr.commit();
                    } catch (Exception ex) {
                        tr.rollback();
                        System.out.println("ERROR: No se ha podido finalizar la consulta -> " + ex.getMessage());
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }
                    break;
                case 11:
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();

                    // necesitamos pedir todos los datos
                    in.nextLine();
                    System.out.print("Numero de Socio: ");
                    String numSocio = in.nextLine();

                    System.out.print("Nombre: ");
                    String nom = in.nextLine();

                    System.out.print("DNI (incluyendo la letra): ");
                    String dni = in.nextLine();

                    System.out.print("Fecha de nacimiento (dd/mm/yyyy): ");
                    String fechaNac = in.nextLine();

                    System.out.print("Telefono: ");
                    String telefono = in.nextLine();

                    System.out.print("Correo electronico: ");
                    String correo = in.nextLine();

                    System.out.print("Fecha de entrada: ");
                    String fechaEntrada = in.nextLine();

                    System.out.print("Categoria: ");
                    cat = in.nextLine().toUpperCase();
                    categoria = cat.charAt(0);

                    try {
                        Socio socio = new Socio(numSocio, nom, dni, fechaNac, telefono, correo, fechaEntrada, categoria);
                        session.saveOrUpdate(socio);

                        tr.commit();
                    } catch (Exception ex) {
                        tr.rollback();
                        System.out.println("ERROR: No se ha podido insertar al socio -> " + ex.getMessage());
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }

                    break;

                case 12:
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();

                    System.out.print("DNI: ");
                    in.nextLine();
                    dni = in.nextLine();

                    try {
                        // dni no es la pk, es id
                        Query query = session.createQuery("FROM Socio s WHERE s.dni=:dni").setParameter("dni", dni);
                        Socio soc = (Socio) query.getSingleResult();
                        session.delete(soc);
                        tr.commit();
                        System.out.println("El socio con nombre " + soc.getNombre() + " y numero " + soc.getNumeroSocio() + " ha sido eliminado correctamente");
                    } catch (Exception ex) {
                        tr.rollback();
                        System.out.println("No se ha podido eliminar al socio con dni " + dni + " -> " + ex.getMessage());
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }

                    break;

                case 13:
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();

                    System.out.print("DNI: ");
                    in.nextLine();
                    dni = in.nextLine();
                    
                    try {
                        Query query = session.createQuery("FROM Monitor m JOIN Actividad a WHERE a.monitorResponsable.dni=:dni").setParameter("dni", dni);
                        ArrayList<Actividad> actividades = (ArrayList<Actividad>) query.getResultList();
                        
                        for(Actividad actividad : actividades){
                            System.out.println("Nombre Actividad: " + actividad.getNombre() + ", dia: " + actividad.getDia() + ", hora: " + actividad.getHora()
                                    + ", descripcion: " + actividad.getDescripcion() + ", precioBaseMes: " + actividad.getPrecioBaseMes() + 
                                    ", Monitor Responsable: " + actividad.getMonitorResponsable());
                        }
                        tr.commit();
                        System.out.println("");
                    } catch (Exception e) {
                        tr.rollback();
                        System.out.println("No se ha podido recuperar la iformacion sobre la actividad que lleva a cabo el monitor con dni " + dni + " -> " + e.getMessage());
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }
                    break;

                default:
                    System.out.println("Algo ha ido mal...");
            }

        } while (el
                != 0);

    }
}
