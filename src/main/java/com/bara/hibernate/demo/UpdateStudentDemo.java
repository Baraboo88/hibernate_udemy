package com.bara.hibernate.demo;

import com.bara.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UpdateStudentDemo {


    private static final Logger log = LoggerFactory.getLogger(UpdateStudentDemo.class);

    public static void main(String[] args) {



        // create session factory


        // create session

        try(SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory()){

            int studentId = 1;
            Session session = factory.getCurrentSession();


            session.beginTransaction();



            //now get a new session and start transaction

            log.info("getting the student from the db with id {}", studentId);

            Student student = session.get(Student.class, studentId);

            //retrieve student based on the id: primary key

            log.info("received student: {}" + student);

            log.info("updating student...");

            student.setFirstName("Suck");


            session.getTransaction().commit();

            session = factory.getCurrentSession();

            session.beginTransaction();

            student = session.get(Student.class, studentId);

            log.info("getting getting updated student info {}", student);
            session.getTransaction().commit();

            session = factory.getCurrentSession();

            session.beginTransaction();

            log.info("Update email for all students");

            session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();

            //retrieve student based on the id: primary key

            session.getTransaction().commit();

            session = factory.getCurrentSession();

            session.beginTransaction();

            student = session.get(Student.class, studentId);

            log.info("getting getting updated student info {}", student);
            session.getTransaction().commit();

            System.out.println("Done!");

        }




    }
}
