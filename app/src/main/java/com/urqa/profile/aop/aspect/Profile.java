package com.urqa.profile.aop.aspect;

import com.urqa.profile.BaseProfile;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by hwangheeseon on 15. 11. 4..
 */
@Aspect
public class Profile {

    private static final String POINTCUT_METHOD =
            "execution(@com.urqa.profile.aop.annotation.Profile * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.urqa.profile.aop.annotation.Profile *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithProfile() {}

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedProfile() {}

    @Around("methodAnnotatedWithProfile() || constructorAnnotatedProfile()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {


        BaseProfile.writeProfileDataToFile();
        Object result = joinPoint.proceed();
        BaseProfile.writeProfileDataToFile();

        return result;
    }


}

