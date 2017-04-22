package com.ekong.service;

import com.ekong.config.mybatis.ReadConn4OrderDB;
import com.ekong.mapper.OrderMapper;
import com.ekong.pojo.DO.OrderStatusDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by xiao on 2017/3/27.
 */
@Service
@Transactional(transactionManager = "transactionManager")
public class TestService {

    @Autowired
    OrderMapper orderMapper;

    /**
     * 如果没有注解，默认为主数据源
     *      有事务
     *
     * @return
     * @see com.ekong.config.mybatis.DataSourceConfiguration4OrderDB
     */
    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int testService1() throws Exception {
        Random random = new Random();
        String id = String.valueOf(random.nextInt(50000000));
        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setId(id);
        orderStatusDO.setConsume_amount(new BigDecimal(123));
        orderStatusDO.setOrder_status("aaa");
        orderMapper.addOrder(orderStatusDO);
//        if (true) {
//            throw new Exception("custom error");
//        }
        return 10;
    }

    /**
     * 采用另外一个数据源
     *      无事务
     * @return
     */
    @Transactional
    @ReadConn4OrderDB
    public int testService2() throws Exception {
        Random random = new Random();
        String id = String.valueOf(random.nextInt(50000000));
        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setId(id);
        orderStatusDO.setConsume_amount(new BigDecimal(random.nextInt(10000)));
        orderStatusDO.setOrder_status("bbb");
        orderMapper.addOrder(orderStatusDO);
//        if (true) {
//            throw new Exception("custom error");
//        }
        return 3;
    }
}
