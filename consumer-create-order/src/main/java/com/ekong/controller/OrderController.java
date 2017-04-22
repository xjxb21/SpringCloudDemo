package com.ekong.controller;

import com.ekong.base.BaseController;
import com.ekong.common.BaseRespInfo;
import com.ekong.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiao on 2017/3/23.
 */

@RequestMapping("/order")
@RestController
public class OrderController extends BaseController {

    @Autowired
    OrderService orderService;

    /**
     *  创建订单号
     * @return
     */
    @ApiOperation(value = "创建订单号", notes = "")
    @RequestMapping(value = "/createOrder", method = RequestMethod.GET)
    public BaseRespInfo createOrder() {
        String orderId = orderService.getOrderId();
        BaseRespInfo respInfo = new BaseRespInfo();
        respInfo.setObject(orderId);
        return respInfo;
    }

}
