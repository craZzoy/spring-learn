package com.segfault.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Entity
@IdClass(DependentId.class)
public class Dependent {
    @Id
    String name;

    @Id
    @ManyToOne
    Employee emp;
}

class DependentId implements Serializable {
    //映射到具体类字段
    String name;
    long emp;
}