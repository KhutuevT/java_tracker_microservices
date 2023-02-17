package com.backand.tracker.annotations;

import com.backand.tracker.modules.user.User;
import com.backand.tracker.utils.UserPermissionsCheck;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CheckTaskPermissionsAspect {
    @Around("@annotation(checkTaskPermissions) && execution(* *(Long, Long, ..))")
    public void checkTaskPermissionMethod(JoinPoint joinPoint, CheckTaskPermissions checkTaskPermissions){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) userDetails;
        UserPermissionsCheck.inTaskWithException(user, (Long) joinPoint.getArgs()[1], checkTaskPermissions.permission());
    }
}
