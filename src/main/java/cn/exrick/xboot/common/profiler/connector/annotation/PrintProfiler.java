package cn.exrick.xboot.common.profiler.connector.annotation;

import java.lang.annotation.*;


@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrintProfiler {
	int elapseTime() default 500;
	String extraInfo() default "";
}
