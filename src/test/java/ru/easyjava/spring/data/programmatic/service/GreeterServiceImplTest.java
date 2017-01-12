package ru.easyjava.spring.data.programmatic.service;

import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;
import ru.easyjava.spring.data.programmatic.dao.GreeterDao;
import ru.easyjava.spring.data.programmatic.entity.Greeter;

import java.util.Collections;

import static org.easymock.EasyMock.expect;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GreeterServiceImplTest extends EasyMockSupport {
    @Rule
    public EasyMockRule em = new EasyMockRule(this);

    @Mock
    private GreeterDao dao;

    @TestSubject
    private GreeterServiceImpl testedObject = new GreeterServiceImpl();

    @Test
    public void testNoGreets() {
        expect(dao.getGreetings()).andReturn(Collections.EMPTY_LIST);

        replayAll();

        assertThat(testedObject.greet(), is("No greets"));
    }

    @Test
    public void testGreets() {
        Greeter expected = new Greeter();
        expected.setGreeting("TEST");
        expected.setTarget("TEST");
        expect(dao.getGreetings()).andReturn(Collections.singletonList(expected));

        replayAll();

        assertThat(testedObject.greet(), is("TEST, TEST"));
    }
}
