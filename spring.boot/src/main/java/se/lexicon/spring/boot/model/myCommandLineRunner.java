package se.lexicon.spring.boot.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component
public class myCommandLineRunner implements CommandLineRunner {

    @Autowired
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello from spring boot application ");

        //for specifying the student we not need to specify the Id because it is auto increment
        Student student = new Student("Mohsen", "forouzmand", "mohsen@meta.com",
                LocalDate.parse("1986-04-11"), true);


// After defining the instance it is time to define and call the entityManager , calling persist and what object we want to add is student

        entityManager.persist(student);
        System.out.println(student);

        student =entityManager.find(Student.class, 1);


        //findAll
        Query query =entityManager.createNativeQuery("SELECT * FROM students ");
        query.getResultList().forEach(System.out::println);
        System.out.println(student);

    }
}



