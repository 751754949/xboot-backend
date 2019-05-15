package cn.exrick.xboot.common.profiler.connector.annotation;

import java.lang.annotation.*;


@Target({ ElementType.PARAMETER,ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SkipDigest {

}
