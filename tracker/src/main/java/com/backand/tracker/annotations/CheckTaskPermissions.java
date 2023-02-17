package com.backand.tracker.annotations;

import com.backand.tracker.modules.task_role_permission.TaskPermission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckTaskPermissions {
    TaskPermission permission();
}
