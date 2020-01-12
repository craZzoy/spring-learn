package com.segfault.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    long enmId;


    String empName;

    public long getEnmId() {
        return enmId;
    }

    public void setEnmId(long enmId) {
        this.enmId = enmId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
