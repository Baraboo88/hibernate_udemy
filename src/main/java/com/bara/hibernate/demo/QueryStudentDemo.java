package com.bara.hibernate.demo;

import com.bara.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class QueryStudentDemo {

    public static void main(String[] args) {

        Student tempStudent = new Student("Paul", "Wall", "paul@bara.com");

        // create session factory


        // create session

        try(SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory()){

            System.out.println("Creating a new student object...");

            Session session = factory.getCurrentSession();

            //start a transaction
            System.out.println("Saving the student...");
            session.beginTransaction();

            //save the student object
            session.save(tempStudent);

            //commit transaction
            session.getTransaction().commit();
        }




    }
}
