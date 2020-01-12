package com.segfault.service;


import com.segfault.domin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Override
    /**
     * 声明式事务
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean save(User user) {
        return jdbcTemplate.execute("INSERT INTO USER (name,age) values(?,?);", new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, user.getName());
                ps.setInt(2, user.getAge());
                return ps.executeUpdate() > 0;
            }
        });
    }

    @Override
    //@Transactional
    /**
     * 手写，通过spring的事务管理平台PlatformTransactionManager控制事务
     */
    public boolean save2(User user) {
        Boolean result = false;
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transaction = platformTransactionManager.getTransaction(transactionDefinition);
        //transaction.setRollbackOnly();
        result = save(user);
        try {
            platformTransactionManager.commit(transaction);
        } catch (Exception e) {
            platformTransactionManager.rollback(transaction);
        }
        return result;
    }
}
