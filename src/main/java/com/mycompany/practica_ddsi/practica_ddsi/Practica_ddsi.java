/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.practica_ddsi.practica_ddsi;

import Config.HibernateUtil;
import org.hibernate.SessionFactory;

/**
 *
 * @author jmdom
 */
public class Practica_ddsi {

    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            System.out.println("Conexión Correcta con Hibernate");
        } catch (ExceptionInInitializerError e) {
            Throwable cause = e.getCause();
            System.out.println("Error en la conexión. Revise el fichero .cfg.xml: " + cause.getMessage());
        }
    }
}
