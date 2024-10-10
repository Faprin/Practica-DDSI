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
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Algo ha ido mal...");
            }

        } while (el != 9);

    }
}
