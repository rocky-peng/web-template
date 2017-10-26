package com.commons.db;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author pengqingsong
 * @date 09/09/2017
 * @desc
 */
@Aspect
@Component
public class MasterSlaveChooserAop implements Ordered {

    @Override
    public int getOrder() {
        return 1000;
    }

    @Around("@annotation(org.springframework.transaction.annotation.Transactional) || " +
            "@annotation(com.commons.db.MasterDB)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        DynamicDataSourceHolder.chooseMaster();
        return pjp.proceed();
    }
}
