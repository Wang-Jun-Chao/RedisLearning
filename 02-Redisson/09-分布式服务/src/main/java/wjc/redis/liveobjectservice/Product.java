package wjc.redis.liveobjectservice;

import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Author: 王俊超
 * Date: 2017-12-05 08-05
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
@REntity
public class Product {

    @RId
    private Long id;

    private String name;

    private Map<String, Integer> itemName2Amount;

    private BigDecimal price;

    private Integer unitsInStock;

    protected Product() {
    }

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;

    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getUnitsInStock() {
        return unitsInStock;

    }

    public void setUnitsInStock(Integer unitsInStock) {
        this.unitsInStock = unitsInStock;

    }

    public String getName() {
        return name;

    }

    public Map<String, Integer> getItemName2Amount() {
        return itemName2Amount;
    }

}