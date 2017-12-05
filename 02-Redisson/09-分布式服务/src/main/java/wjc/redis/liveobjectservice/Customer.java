package wjc.redis.liveobjectservice;

import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;
import org.redisson.liveobject.resolver.UUIDGenerator;

import java.util.List;

/**
 * Author: 王俊超
 * Date: 2017-12-05 08-07
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
@REntity
public class Customer {

    @RId(generator = UUIDGenerator.class)
    private String id;

    private List<Order> orders;

    private String name;

    private String address;

    private String phone;

    protected Customer() {
    }

    public Customer(String id) {
        super();
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String getId() {
        return id;
    }
}