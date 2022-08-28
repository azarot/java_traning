package com.azarot.homework.hw_27.demo;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;

import com.azarot.homework.hw_27.demo.entity.Person;
import com.azarot.homework.hw_27.session.impl.SessionFactoryImpl;

public class DemoApp {
    public static void main(String[] args) {
        var dataSource = initDataSource();
        var sessionFactory = new SessionFactoryImpl();
        var session = sessionFactory.createSession(dataSource);

        Person person = session.findByID(3, Person.class);
        System.out.println(person);
        Person theSamePerson = session.findByID(3, Person.class);
        System.out.println(person);
        System.out.println(theSamePerson == person);
    }

    private static DataSource initDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");

        return dataSource;
    }
}
