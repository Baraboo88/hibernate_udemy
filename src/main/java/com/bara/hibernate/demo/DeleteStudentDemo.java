package com.bara.hibernate.demo;

import com.bara.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DeleteStudentDemo {


    private static final Logger log = LoggerFactory.getLogger(DeleteStudentDemo.class);

    public static void main(String[] args) {



        // create session factory


        // create session

        try(SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory()){

            int studentId = 2;
            Session session = factory.getCurrentSession();


            session.beginTransaction();

            //now get a new session and start transaction

            log.info("getting the student from the db with id {}", studentId);

            Student student = session.get(Student.class, studentId);

            //retrieve student based on the id: primary key

            log.info("received student: {}" + student);

            log.info("deleting student...");

            session.createQuery("delete from Student where id=2").executeUpdate();

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
