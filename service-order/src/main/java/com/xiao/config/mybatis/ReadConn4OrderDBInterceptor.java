package com.xiao.config.mybatis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Created by xiao on 2017/3/27.
 */

@Aspect
@Component
public class ReadConn4OrderDBInterceptor implements Ordered {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(readConn4OrderDB)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, ReadConn4OrderDB readConn4OrderDB) throws Throwable {
        try {
            logger.debug("use Slave database.");
            DbContextHolder4OrderDB.setDbType(DbContextHolder4OrderDB.DbType.SLAVE);

            Object result = proceedingJoinPoint.proceed();
            return result;
        }finally {
            DbContextHolder4OrderDB.clearDbType();
        }
    }

    @Override
    public int getOrder() {
        return -5;
    }
}
