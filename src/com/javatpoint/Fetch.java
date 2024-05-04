package com.javatpoint;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Fetch {
    public Fetch() {
    }

    public static void main(String[] args) {
        // Set up Hibernate configuration
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        // Open a Hibernate session
        Session session = factory.openSession();

        // Create a query to fetch Employee entities
        TypedQuery query = session.createQuery("from Employee e");
        List<Employee> list = query.getResultList();

        // Iterate over the result list and print Employee and Address information
        Iterator<Employee> itr = list.iterator();
        while(itr.hasNext()) {
            Employee emp = itr.next();
            System.out.println(emp.getEmployeeId() + " " + emp.getName() + " " + emp.getEmail());

            // Access the associated Address entity
            Address address = emp.getAddress();
            System.out.println(address.getAddressLine1() + " " + address.getCity() + " " +
                    address.getState() + " " + address.getCountry() + " " + address.getPincode());
        }

        // Close the Hibernate session
        session.close();
        System.out.println("success");
    }
}
