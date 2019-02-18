package com.bara.hibernate.demo;

import com.bara.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

    public static void main(String[] args) {



        try(SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory()){

            //create 3 student objects
            System.out.println("Creating  3 student object...");

            Student tempStudent1 = new Student("John", "Doe", "john@bara.com");
            Student tempStudent2 = new Student("Mary", "Public", "mary@bara.com");
            Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@bara.com");
            Session session = factory.getCurrentSession();

            //start a transaction
            System.out.println("Saving the student...");
            session.beginTransaction();

            //save the student object
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            //commit transaction
            session.getTransaction().commit();


        }

    }
}
