package com.xiao.pojo.DO;

import java.math.BigDecimal;

/**
 *  order_status 表
 */
public class OrderStatusDO {

    private String id;
    private BigDecimal consume_amount;
    private String order_status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getConsume_amount() {
        return consume_amount;
    }

    public void setConsume_amount(BigDecimal consume_amount) {
        this.consume_amount = consume_amount;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    @Override
    public String toString() {
        return "OrderStatusDO{" +
                "id='" + id + '\'' +
                ", consume_amount=" + consume_amount +
                ", order_status='" + order_status + '\'' +
                '}';
    }
}
