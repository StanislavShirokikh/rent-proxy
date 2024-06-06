package org.example.rentproxy.config.jdbc.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

public class JdbcSessionConfig {
    @Bean
    @Primary
    public JdbcIndexedSessionRepository jdbcIndexedSessionRepository(JdbcOperations jdbcOperations, PlatformTransactionManager transactionManager) {
        TransactionTemplate transactionTemplate = createTransactionTemplate(transactionManager);
        return new CustomIndexedJdbcSessionRepository(jdbcOperations, transactionTemplate);
    }

    private TransactionTemplate createTransactionTemplate(PlatformTransactionManager transactionManager) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(3);
        transactionTemplate.afterPropertiesSet();
        return transactionTemplate;
    }
}
