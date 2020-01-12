package com.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * 通过AOP为已有的类增加新功能
 * Created by zwz on 2019/5/4.
 */
@Aspect
public class EncoreableIntroducer {

    @DeclareParents(value = "com.aspect.Performance+", defaultImpl = DefaultEncoreable.class)
    public static Encoreable encoreable;
}
