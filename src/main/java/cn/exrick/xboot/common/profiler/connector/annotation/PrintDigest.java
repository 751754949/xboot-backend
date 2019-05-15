package cn.exrick.xboot.common.profiler.connector.annotation;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PrintDigest {
	PrintLevel printLevel() default PrintLevel.INFO;
}
