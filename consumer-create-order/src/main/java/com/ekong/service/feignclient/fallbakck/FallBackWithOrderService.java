package com.ekong.service.feignclient.fallbakck;

import com.ekong.common.BaseRespInfo;
import com.ekong.service.feignclient.FeignClientWithOrderService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 调用失败处理
 */
@Component
public class FallBackWithOrderService implements FallbackFactory<FeignClientWithOrderService> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public FeignClientWithOrderService create(Throwable cause) {
        logger.warn("invoke microservice saveOrder failed, cause:{}", cause.getMessage());

        return new FeignClientWithOrderService() {
            @Override
            public BaseRespInfo saveOrder() {
                return new BaseRespInfo(false, cause.getMessage(), null);
            }
        };
    }
}
