package org.example.rentproxy.config.jdbc.session;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionOperations;

@Repository
public class CustomIndexedJdbcSessionRepository extends JdbcIndexedSessionRepository {
    public CustomIndexedJdbcSessionRepository(JdbcOperations jdbcOperations, TransactionOperations transactionOperations) {
        super(jdbcOperations, transactionOperations);
    }
}
