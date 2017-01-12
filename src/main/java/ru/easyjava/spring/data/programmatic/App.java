package ru.easyjava.spring.data.programmatic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.easyjava.spring.data.programmatic.dao.GreeterDao;
import ru.easyjava.spring.data.programmatic.entity.Greeter;
import ru.easyjava.spring.data.programmatic.service.GreeterService;

/**
 * Simple example of JDBC usage.
 */
public final class App {
    /**
     * Do not construct me.
     */
    private App() {
    }

    /**
     * Entry point.
     *
     * @param args Command line args. Not used.
     */
    public static void main(final String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);
        GreeterService greeterService = context.getBean(GreeterService.class);
        GreeterDao dao = context.getBean(GreeterDao.class);

        Greeter greeter = new Greeter();
        greeter.setGreeting("Hello");
        greeter.setTarget("World");

        dao.addGreet(greeter);

        System.out.println(greeterService.greet());

        dao.updateGreet(greeter, "Fail");
        System.out.println(greeterService.greet());

        System.exit(0);
    }
}
