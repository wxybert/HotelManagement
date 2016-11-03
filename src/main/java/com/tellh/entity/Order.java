package com.tellh.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by tlh on 2016/10/30.
 */
@NamedQueries({
        @NamedQuery(name = "ByState", query = "select o from Order o where state=:state")
})
@Entity
@Table(name = "order", schema = "hotel_management")
public class Order {
    public enum State {
        VALID,//有效
        CHECK_OUT,//已退房，交还押金
        EXPIRE,//逾期未退房
        PAID//已结算
    }

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "native")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "oid")
    private int id;
    @Column(name = "date_check_in")
    private Timestamp dCheckIn;
    private Timestamp deadline;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cid")
    private Customer customer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rid")
    private Room room;

    @Transient
    @JSONField(serialize = false)
    private int days;
    private State state = State.VALID;

    @JSONField(serialize = false)
    public boolean isValid() {
        return deadline.after(new Date());
    }

    public State getState() {
        if (state == State.VALID && new Timestamp(System.currentTimeMillis()).after(deadline))
            state = State.EXPIRE;
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getdCheckIn() {
        return dCheckIn;
    }

    public void setdCheckIn(Timestamp dCheckIn) {
        this.dCheckIn = dCheckIn;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "Order{" +
                "dCheckIn=" + dCheckIn +
                ", deadline=" + deadline +
                ", customer=" + customer +
                ", room=" + room.getRoomNo() +
                ", state=" + state.name() +
                '}';
    }
}
