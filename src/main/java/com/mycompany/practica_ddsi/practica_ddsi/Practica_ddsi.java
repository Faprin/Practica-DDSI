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
        System.out.println("9. Salir ");

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
                    int cuota = in.nextInt();
                    
                    try {
                        Query query = session.createQuery("SELECT a FROM Actividad a WHERE dia=:dia AND ")
                    } catch (Exception ex) {
                        tr.rollback();
                        System.out.println("ERROR: No se ha podido finalizar la consulta -> " + ex.getMessage());
                    }finally {
                        if(session != null && session.isOpen())
                            session.close();
                    }
                    break;
                case 11:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Algo ha ido mal...");
            }

        } while (el != 11);

    }
}
