package com.zti.bountyHunter.aspect;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.zti.bountyHunter.dao.AuthoritiesInterface;
import com.zti.bountyHunter.models.Authorities;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* Aspect class
*/
@Aspect
@Component
public class AspectLogger {
    @Autowired
    AuthoritiesInterface authoritiesInterface;

	/**
	* An aspect that logs all endpoint executions. Has the following format:<br>
    *
    * "{datetime}  {username}({role})-{@literal >}{request method}:{endpoint}"<br>
    *
    * Logs after endpoint execution<br>
	*
	* @param joinPoint	The aspects joint point
	*/
    @Around("execution(* com.zti.bountyHunter.controllers..*(..))")
    public Object aspectLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Authorities auth = authoritiesInterface.getById(authentication.getName());
        String role;
        if(authentication.getName().equals("anonymousUser"))
            role = "ANONYMOUS";
        else
            role = auth.getAuthority();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
        LocalDateTime now = LocalDateTime.now();
        Object proceed = joinPoint.proceed();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RequestMapping myAnnotation = method.getAnnotation(RequestMapping.class);
        System.out.printf("%s  %s(%s)->%s:%s%n", dtf.format(now), authentication.getName(), role, myAnnotation.method()[0].name(), myAnnotation.value()[0]);
        return proceed;
    }
}