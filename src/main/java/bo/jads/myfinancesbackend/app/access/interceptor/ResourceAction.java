package bo.jads.myfinancesbackend.app.access.interceptor;

import bo.jads.myfinancesbackend.app.domain.entities.enums.ActionCode;
import bo.jads.myfinancesbackend.app.domain.entities.enums.EntityType;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface ResourceAction {

    @Nonbinding
    ActionCode action() default ActionCode.NONE;

    @Nonbinding
    EntityType entity() default EntityType.NONE;

}
