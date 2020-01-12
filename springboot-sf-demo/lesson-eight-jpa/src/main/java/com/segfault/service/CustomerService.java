package com.segfault.service;

import com.segfault.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CustomerService {

    //JPA标准实现
    //持久化上下文，type默认PersistenceContextType.TRANSACTION
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 添加客户
     *
     * @param customer
     */
    @Transactional(rollbackFor = Exception.class)
    public void addCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    /**
     * 查询客户
     *
     * @param id
     * @return
     */
    public Customer findCustomer(Long id) {
        //return entityManager.find(Customer.class,id);
        return (Customer) entityManager.createQuery("select id,name from customers where id=:id")
                .setParameter("id", id)
                .setMaxResults(10)
                .getResultList().get(0);
    }


}
