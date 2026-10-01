package com.cedacri.internship;

import com.cedacri.internship.entities.Bank;
import com.cedacri.internship.entities.Client;
import com.cedacri.internship.repositories.BankRepository;
import com.cedacri.internship.repositories.ClientRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("javax.net.ssl.trustStoreType", "Windows-ROOT");
//        try (SessionFactory sessionFactory = new Configuration()
//                .configure()
//                .buildSessionFactory()) {
//            try (Session session = sessionFactory.openSession()) {
////                session.beginTransaction();
////                session.persist(Bank.builder().branch("Maib Telecentru").address("Grenoblea").build());
////                session.getTransaction().commit();
//
//                BankRepository repository = new HibernateBankRepositoryImpl(sessionFactory);
//
//                repository.create(Bank.builder()
//                        .branch("MAIB")
//                        .address("Adress")
//                        .build());
//
//                ClientRepository clientRepository = new HibernateClientRepositoryImpl(sessionFactory);
//
//                clientRepository.create(
//                        Client.builder()
//                                .birthDate(LocalDate.now())
//                                .fullName("NAME")
//                                .bank(repository.getById(1))
//                                .build()
//                );
//
//            } catch (Exception e) {
//                System.err.println(e.getMessage());
//            }
//        }
    }
}