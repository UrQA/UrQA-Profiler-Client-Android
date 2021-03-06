package com.urqa.profile.aop.aspect;


import com.urqa.profile.BaseProfile;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
/**
 * Created by hwangheeseon on 15. 11. 4..
 */
@Aspect
public class AfterProfile {

    private static final String POINTCUT_METHOD =
            "execution(@com.urqa.profile.aop.annotation.AfterProfile * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.urqa.profile.aop.annotation.AfterProfile *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithAfterProfile() {}

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedAfterProfile() {}

    @After("methodAnnotatedWithAfterProfile() || constructorAnnotatedAfterProfile()")
    public void afterTargetMethod() throws Throwable {
        BaseProfile.writeProfileDataToFile();
    }

}
