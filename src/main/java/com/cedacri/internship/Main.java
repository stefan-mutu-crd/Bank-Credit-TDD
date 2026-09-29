package com.cedacri.internship;

import com.cedacri.internship.config.DataSourceFactory;
import com.cedacri.internship.repositories.BankRepository;
import com.cedacri.internship.repositories.LoanRepository;
import com.cedacri.internship.repositories.impl.BankRepositoryImpl;
import com.cedacri.internship.repositories.impl.LoanRepositoryImpl;
import com.cedacri.internship.services.BankService;
import com.cedacri.internship.services.impl.BankServiceImpl;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.setProperty("javax.net.ssl.trustStoreType", "Windows-ROOT");

        HikariDataSource dataSource = DataSourceFactory.create("jdbc:postgresql://localhost:5432/bank-system?user=root&password=admin");
        BankRepository bankRepository = new BankRepositoryImpl(dataSource);
        LoanRepository loanRepository = new LoanRepositoryImpl(dataSource);
        BankService bankService = new BankServiceImpl(bankRepository, loanRepository);

        System.out.println(bankService.findAll());
    }
}