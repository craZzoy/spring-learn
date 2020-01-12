package com.segfault.entity;

import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "books")
@NoRepositoryBean
public class Book {

    @Id
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "books")
    private Collection<Customer> customers;
}
