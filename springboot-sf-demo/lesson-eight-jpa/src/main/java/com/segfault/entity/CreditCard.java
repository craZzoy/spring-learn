package com.segfault.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 信用卡类
 */
@Entity
@Table(name = "credit_cards")
public class CreditCard {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 128)
    private String number;

    @Column(name = "register_date")
    private Date regiteredDate;

    @OneToOne(mappedBy = "creditCard", cascade = CascadeType.REMOVE)
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getRegiteredDate() {
        return regiteredDate;
    }

    public void setRegiteredDate(Date regiteredDate) {
        this.regiteredDate = regiteredDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
