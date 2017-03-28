package com.xiao.service.feignclient;

import com.xiao.common.BaseRespInfo;
import com.xiao.service.feignclient.fallbakck.FallBackWithOrderService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 订单服务调用
 */
@FeignClient(name = "feiClientOrderService", fallbackFactory = FallBackWithOrderService.class)
public interface FeignClientWithOrderService {

    /**
     * 保存订单服务信息
     * 设置个properties 方便后期修改
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    BaseRespInfo saveOrder();
}
