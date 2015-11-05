package com.urqa.aop.aspect;

import com.urqa.profile.UrQAProfile;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by hwangheeseon on 15. 11. 4..
 */
@Aspect
public class BeforeProfile {

    private static final String POINTCUT_METHOD =
            "execution(@com.urqa.aop.annotation.BeforeProfile * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.urqa.aop.annotation.BeforeProfile *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithBeforeProfile() {}

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedBeforeProfile() {}

    @Before("methodAnnotatedWithBeforeProfile() || constructorAnnotatedBeforeProfile()")
    public void beforeTargetMethod(JoinPoint jp) throws Throwable {



        UrQAProfile.writeProfile();


    }


}
