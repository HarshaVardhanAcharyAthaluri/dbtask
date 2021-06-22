package com.tasktwo.dbtask.annotation;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodeParameterAspect {
	private static org.slf4j.Logger log = LoggerFactory.getLogger(MethodeParameterAspect.class);
	
	@Around("execution(* *(..)) && @annotation(com.tasktwo.dbtask.annotation.LogMethodParam)")
    public Object log(ProceedingJoinPoint point) throws Throwable 
   {
       Object result = point.proceed();
       log.info("methodName={}, methodeParameters={},methodetypedParameters={}",new Object[]{
            MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
            MethodSignature.class.cast(point.getSignature()).getMethod().getParameters(),
            MethodSignature.class.cast(point.getSignature()).getMethod().getParameterTypes()
            }
          );
       return result;
   }
}
