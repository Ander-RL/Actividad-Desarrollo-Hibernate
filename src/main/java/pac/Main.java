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
        /*SessionFactory*/ sessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
        //Session session = sessionFactory.openSession();
        System.out.println("CONFIGURACION REALIZADA");

        // Para insertar datos
        //Transaction transaction = session.beginTransaction();

        // Modulos
        addModulo("Programacion B", "M03B");
        addModulo("Acceso a Datos", "M06");
        addModulo("Desarrollo de Aplicaciones Moviles", "M08");
        addModulo("Servicios y Procesos", "M09");
        /*pac.Modulo programacionB = new pac.Modulo(1,"Programacion B", "M03B");
        pac.Modulo accesoAdatos = new pac.Modulo(2,"Acceso a Datos", "M06");
        pac.Modulo desarrolloAplicaciones = new pac.Modulo(3,"Desarrollo de Aplicaciones Moviles", "M08");
        pac.Modulo serviciosYprocesos = new pac.Modulo(4,"Servicios y Procesos", "M09");*/

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

        // pac.Profesor
        addProfesor("Alvaro", "Hombre");
        //pac.Profesor alvaro = new pac.Profesor(0, "Alvaro", "Hombre");

        // Se insertan los datos
        addAlumno("Juan","Espaniola", 26, "Hombre", juanModulos);
        addAlumno("Pedro","Andorrana", 21, "Hombre", pedroModulos);
        addAlumno("Marta","Espaniola", 19, "Mujer", martaModulos);
        addAlumno("Carla","Francesa", 35, "Mujer", carlaModulos);

        /*insertModulos(programacionB, session);
        insertModulos(accesoAdatos, session);
        insertModulos(desarrolloAplicaciones, session);
        insertModulos(serviciosYprocesos, session);

        insertAlumnos(1, "Juan","Espaniola", 26, "Hombre", juanModulos, session);
        insertAlumnos(2, "Pedro","Andorrana", 21, "Hombre", pedroModulos, session);
        insertAlumnos(3, "Marta","Espaniola", 19, "Mujer", martaModulos, session);
        insertAlumnos(4, "Carla","Francesa", 35, "Mujer", carlaModulos, session);

        insertProfesor(0, "Alvaro", "Hombre", session);*/

        // Commit the todos los datos
        //transaction.commit();
        // Cerramos la sesion
        //session.close();
        //sessionFactory.close();
    }

    /*public static void insertModulos(pac.Modulo modulo, Session session){
        session.save(modulo);
        System.out.println("Inser into " + modulo.toString());
    }

    public static void insertAlumnos(int idAlumno, String nombre, String nacionalidad, int edad, String sexo, Set<pac.Modulo> modulos, Session session){
        pac.Alumno alumno = new pac.Alumno(idAlumno, nombre, nacionalidad, edad, sexo, modulos);
        session.save(alumno);
        System.out.println("Inser into " + alumno.toString());
    }

    public static void insertProfesor(int idProfesor, String nombre, String sexo, Session session){
        pac.Profesor profesor = new pac.Profesor(idProfesor, nombre, sexo);
        session.save(profesor);
        System.out.println("Inser into " + profesor.toString());
    }*/

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
