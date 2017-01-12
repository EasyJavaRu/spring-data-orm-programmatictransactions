package ru.easyjava.spring.data.programmatic.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import ru.easyjava.spring.data.programmatic.entity.Greeter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * JPA based implementation of GreeterDao.
 */
@Repository
public class GreeterDaoImpl implements GreeterDao {
    /**
     * JPA EM factory, provided by Spring.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Default TransactionTemplate by Spring.
     */
    @Inject
    private TransactionTemplate transactionTemplate;

    /**
     * Transaction manager by Spring.
     */
    @Inject
    private PlatformTransactionManager transactionManager;

    @Override
    public final void addGreet(final Greeter g) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                em.persist(g);
            }
        });
    }

    @Override
    public final void updateGreet(final Greeter g, final String newTarget) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                Greeter greet = em.merge(g);
                greet.setTarget(newTarget);

                status.setRollbackOnly();
            }
        });
    }

    @Override
    public final List<Greeter> getGreetings() {
        TransactionTemplate tx = new TransactionTemplate(transactionManager);
        tx.setReadOnly(true);
        return tx.execute(status -> em.createQuery("from Greeter", Greeter.class)
                .getResultList());
    }
}
