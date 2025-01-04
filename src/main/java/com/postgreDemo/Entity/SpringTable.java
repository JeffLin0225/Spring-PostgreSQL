package com.postgreDemo.Entity;


import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;


@Entity
@Table(name = "springtable")
public class SpringTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer age;
    private Date time;

    private Timestamp createtime;

    @PrePersist
    public void setCreatetime() {
        if (createtime == null) {
            this.createtime = new Timestamp(System.currentTimeMillis());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
