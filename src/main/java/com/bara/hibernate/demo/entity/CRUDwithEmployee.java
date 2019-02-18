package com.bara.hibernate.demo.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class CRUDwithEmployee {

    private static final Logger log = LoggerFactory.getLogger(CRUDwithEmployee.class);

    public static void main(String[] args) {


        try(SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory()){

            log.info("implementing saving of the object");

            Employee employee = new Employee("Alex", "Bara", "Cargill");

            log.info("Creating a new object : {}", employee);

            Session session = factory.getCurrentSession();

            session.beginTransaction();

            session.save(employee);

            log.info("Object was saved");

            session.getTransaction().commit();

            log.info("Implementing retrieving of the saved object");

            session = factory.getCurrentSession();

            session.beginTransaction();

            Employee employeeTemp = session.get(Employee.class, employee.getId());

            log.info("getting the object from the db: {}", employeeTemp);

            session.getTransaction().commit();

            log.info("finding the object using 'company' name");

            session = factory.getCurrentSession();

            session.beginTransaction();

            List<Employee> employeeByCompany = session.createQuery("from Employee where company='Cargill'").getResultList();

            log.info("get objects with the company name 'Cargill': {}", employeeByCompany);

        //    employeeByCompany.stream().forEach(System.out::println);

            session.getTransaction().commit();

            log.info("implementing of the deleting of the employee with 8 primary key");

            session = factory.getCurrentSession();

            session.beginTransaction();

            session.createQuery("delete from Employee where id=8").executeUpdate();


            log.info("final list of the employees", session.createQuery("from Employee").getResultList());

            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();

            log.info("final list of the employees {}", session.createQuery("from Employee").getResultList());

            session.getTransaction().commit();


        }

    }
}
