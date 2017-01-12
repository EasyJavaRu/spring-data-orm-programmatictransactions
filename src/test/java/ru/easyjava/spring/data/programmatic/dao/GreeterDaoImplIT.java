package ru.easyjava.spring.data.programmatic.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.easyjava.spring.data.programmatic.entity.Greeter;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@ContextConfiguration(classes = ru.easyjava.spring.data.programmatic.ContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class GreeterDaoImplIT {
    @Inject
    private GreeterDao testedObject;

    @DirtiesContext
    @Test
    public void testRetrieve() {
        Greeter expected = new Greeter();
        expected.setGreeting("TEST");
        expected.setTarget("TEST");

        testedObject.addGreet(expected);

        List<Greeter> actual = testedObject.getGreetings();
        Iterator<Greeter> it = actual.iterator();
        Greeter actualGreet =it.next();
        assertThat(actualGreet.getGreeting(), is("TEST"));
        assertThat(actualGreet.getTarget(), is("TEST"));
    }

}
