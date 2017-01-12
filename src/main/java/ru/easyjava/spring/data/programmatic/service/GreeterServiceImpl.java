package ru.easyjava.spring.data.programmatic.service;

import org.springframework.stereotype.Service;
import ru.easyjava.spring.data.programmatic.dao.GreeterDao;
import ru.easyjava.spring.data.programmatic.entity.Greeter;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

/**
 * Simple greeter implementation.
 */
@Service
public class GreeterServiceImpl implements GreeterService {
    /**
     * Our data layer.
     */
    @Inject
    private GreeterDao dao;

    @Override
    public final String greet() {
        List<Greeter>  greets = dao.getGreetings();
        Iterator<Greeter> it = greets.iterator();
        if (!it.hasNext()) {
            return "No greets";
        }
        Greeter greeter = it.next();
        return greeter.getGreeting() + ", " + greeter.getTarget();
    }
}
