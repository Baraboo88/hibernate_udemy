package com.bara.hibernate.demo;

import com.bara.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class ReadStudentDemo {

    public static void main(String[] args) {



        // create session factory


        // create session

        try(SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory()){

            System.out.println("Creating a new student object...");
            Student tempStudent = new Student("Daffy", "Duck", "daffy@bara.com");
            Session session = factory.getCurrentSession();

            session.beginTransaction();

            //start a transaction
            System.out.println("Saving the student...");
            System.out.println(tempStudent);


            //save the student object
            session.save(tempStudent);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Saved student, Generated id: " + tempStudent.getId());

            session = factory.getCurrentSession();

            session.beginTransaction();




            //now get a new session and start transaction
            System.out.println("getting the student from the db");
            Student student = session.get(Student.class, tempStudent.getId());

            //retrieve student based on the id: primary key

            //commit the transaction
            System.out.println(student.toString());

            List<Student> theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();

            theStudents.stream().forEach(System.out::println);

            session.getTransaction().commit();

            System.out.println("Done!");

        }




    }
}
