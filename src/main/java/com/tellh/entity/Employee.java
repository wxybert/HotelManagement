package com.tellh.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by tlh on 2016/11/3.
 */
@NamedQueries({
        @NamedQuery(name = "get", query = "SELECT e FROM Employee e WHERE login=:login"),
})
@Entity
@Table(name = "employee", schema = "hotel_management")
public class Employee extends Account {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "native")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "cid")
    private int id;
    private String login;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
