package pac;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class Main {
    private static SessionFactory sessionFactory;
    public static void main(String[] args) {

        // Indicamos la configuracion de Hibernate
        Configuration cfg = new Configuration();
        cfg.configure();
        // Creamos la instacia de SessionFactory
        sessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
        System.out.println("CONFIGURACION REALIZADA");

        // Modulos
        addModulo("Programacion B", "M03B");
        addModulo("Acceso a Datos", "M06");
        addModulo("Desarrollo de Aplicaciones Moviles", "M08");
        addModulo("Servicios y Procesos", "M09");

        // Alumnos
        // Coleccion de modulos por alumno
        Set<Modulo> juanModulos = new HashSet<Modulo>();
        juanModulos.add(new Modulo("Programacion B", "M03B"));
        juanModulos.add(new Modulo("Acceso a Datos", "M06"));
        juanModulos.add(new Modulo("Desarrollo de Aplicaciones Moviles", "M08"));
        juanModulos.add(new Modulo("Servicios y Procesos", "M09"));

        Set<Modulo> pedroModulos = new HashSet<Modulo>();
        pedroModulos.add(new Modulo("Programacion B", "M03B"));
        pedroModulos.add(new Modulo("Acceso a Datos", "M06"));
        pedroModulos.add(new Modulo("Servicios y Procesos", "M09"));

        Set<Modulo> martaModulos = new HashSet<Modulo>();
        martaModulos.add(new Modulo("Desarrollo de Aplicaciones Moviles", "M08"));
        martaModulos.add(new Modulo("Servicios y Procesos", "M09"));

        Set<Modulo> carlaModulos = new HashSet<Modulo>();
        carlaModulos.add(new Modulo("Programacion B", "M03B"));
        carlaModulos.add(new Modulo("Acceso a Datos", "M06"));
        carlaModulos.add(new Modulo("Servicios y Procesos", "M09"));

        // Profesor
        addProfesor("Alvaro", "Hombre");

        // Se insertan los datos
        addAlumno("Juan","Espaniola", 26, "Hombre", juanModulos);
        addAlumno("Pedro","Andorrana", 21, "Hombre", pedroModulos);
        addAlumno("Marta","Espaniola", 19, "Mujer", martaModulos);
        addAlumno("Carla","Francesa", 35, "Mujer", carlaModulos);

        sessionFactory.close();
    }

    public static void addAlumno(String nombre, String nacionalidad, int edad, String sexo, Set modulos){
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Alumno alumno = new Alumno(nombre, nacionalidad, edad, sexo, modulos);
            session.save(alumno);
            tx.commit();
            System.out.println("INSERT INTO pac.Alumno, nombre: " + nombre + ", nacionalidad: " + nacionalidad +
                    ", edad: " + edad + ", sexo:" + ", modulos: " + modulos.toString());
        } catch (HibernateException e){
            if (tx!=null) {tx.rollback();}
        } finally {
            session.close();
        }
    }

    public static void addProfesor(String nombre, String sexo){
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Profesor profesor = new Profesor(nombre, sexo);
            session.save(profesor);
            tx.commit();
            System.out.println("INSERT INTO pac.Profesor, nombre: " + nombre + ", sexo: " + sexo);
        } catch (HibernateException e){
            if (tx!=null) {tx.rollback();}
        } finally {
            session.close();
        }
    }

    public static void addModulo(String nombre, String codigo){
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            Modulo modulo = new Modulo(nombre, codigo);
            session.save(modulo);
            tx.commit();
            System.out.println("INSERT INTO pac.Modulo, nombre: " + nombre + ", codigo: " + codigo);
        } catch (HibernateException e){
            if (tx!=null) {tx.rollback();}
        } finally {
            session.close();
        }
    }
}
