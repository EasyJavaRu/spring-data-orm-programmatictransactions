package ru.easyjava.spring.data.programmatic.dao;

import java.util.List;

import ru.easyjava.spring.data.programmatic.entity.Greeter;

/**
 * Database layer.
 */
public interface GreeterDao {
    /**
     * Adds some greet to the database.
     * @param g Greeting data.
     */
    void addGreet(Greeter g);

    /**
     * Replaces greeting target in a greeter.
     *
     * @param g Greeter to modify.
     * @param newTarget new target value.
     */
    void updateGreet(Greeter g, String newTarget);

    /**
     * Returns all greetings.
     * @return List with all greetings in the database.
     */
    List<Greeter> getGreetings();
}
