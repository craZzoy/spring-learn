package com.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 定义一个切面
 * Created by zwz on 2019/5/4.
 */
@Aspect
public class Audience {

    private int peopleCount;

    @Pointcut("execution(* com.aspect.Performance.perform(..))")
    public void performace() {
    }

    @Pointcut("execution(* com.aspect.Performance.countPerson(int)) && args(count)")
    public void performance(int count) {
    }

    @Before("performace()")
    public void silenceCellPhone() {
        System.out.println("Silencing cell phones");
    }

    @Before("performace()")
    public void takeSeats() {
        System.out.println("taking seats");
    }


    @AfterReturning("performace()")
    public void applause() {
        System.out.println("CLAP CLAP CLAP");
    }

    @AfterThrowing("performace()")
    public void demandRefund() {
        System.out.println("demanding a refund");
    }

    @Around("performace()")
    public void watchPerformance(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("Sliencing cell phone");
            System.out.println("taking seats");
            //proceed不被调用时，将会阻塞对被通知方法的访问
            joinPoint.proceed();
            System.out.println("CLAP CLAP CLAP");
        } catch (Throwable throwable) {
            System.out.println("demanding a refund");
        }
    }

    @Before("performance(count)")
    public void countPerson(int count) {
        this.peopleCount += count;
        System.out.println("people count: " + count);
    }

}
