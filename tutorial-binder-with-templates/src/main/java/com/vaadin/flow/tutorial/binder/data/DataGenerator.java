package com.vaadin.flow.tutorial.binder.data;

import java.util.ArrayList;
import java.util.List;

import org.jfairy.Fairy;
import org.jfairy.producer.person.Person;

/**
 * It generates random users using a fake data generator(Fairy).
        */
public class DataGenerator {

    private static final Fairy fairy = Fairy.create();
    private static final String COMMENT = "Blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah";


    /**
     * It creates a specific number of users.
     *
     * @param number number of users to create
     * @return users list of users
     */
    public static List<User> getUsers(int number) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Person person = fairy.person();
            users.add(new User(person.email(), person.firstName(), person.lastName(), COMMENT));
        }
        return users;
    }
}
