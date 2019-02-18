package com.bara.hibernate.demo;

import com.bara.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudentDemo {

    public static void main(String[] args) {



        try(SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory()){

            Session session = factory.getCurrentSession();

            session.beginTransaction();


            //query student
            List<Student> theStudents = session.createQuery("from Student").getResultList();
            //display the student
            theStudents.stream().forEach(System.out::println);

            //query students: last name = 'Doe'

            theStudents = session.createQuery("from Student s where s.lastName='Public'").getResultList();

            theStudents.stream().forEach(System.out::println);


            // query students: lastName = 'Dow' OR firstName = 'Daffy'

            theStudents = session.createQuery("from Student s where s.lastName='Doe' OR  s.firstName='Daffy'").getResultList();

            theStudents.stream().forEach(System.out::println);


            //query students where email Like "bara.com"

            theStudents = session.createQuery("from Student s where s.email like '%bara.com'").getResultList();

            theStudents.stream().forEach(System.out::println);

            //commit transaction
            session.getTransaction().commit();
        }




    }
}
