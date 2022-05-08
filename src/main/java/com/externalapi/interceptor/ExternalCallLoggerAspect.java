package com.externalapi.interceptor;

import com.externalapi.model.Feriado;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExternalCallLoggerAspect {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MongoTemplate mongoTemplate;


    @Pointcut("execution(* *..ExternalApiCallService.findAllFeriados(..))")
    public void externalApiCallAllService(){}

    @Pointcut("execution(* *..ExternalApiCallService.findExtra(..))")
    public void externalApiCallExtraService(){}

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
    }

    @Pointcut("execution(* *.*(..))")
    protected void allMethod() {
    }

    // inserta en la base de datos.
    @Around("controller() && allMethod()")
    public Feriado logCalls(ProceedingJoinPoint pjp) {

        String status = "COMPLETED";
        Feriado response = null;
        try {
            Object result = pjp.proceed();
            mongoTemplate.insert(result,"feriados");
            return (Feriado) result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument");
            throw e;
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }



}
