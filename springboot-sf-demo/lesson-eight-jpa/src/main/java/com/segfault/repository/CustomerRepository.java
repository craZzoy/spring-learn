package com.segfault.repository;

import com.segfault.entity.Customer;
import org.hibernate.annotations.ManyToAny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * 客户仓储
 */
@Repository
@Transactional(readOnly = false)
public class CustomerRepository extends SimpleJpaRepository<Customer, Long> {

    @Autowired
    public CustomerRepository(EntityManager em) {
        super(Customer.class, em);
    }
}
