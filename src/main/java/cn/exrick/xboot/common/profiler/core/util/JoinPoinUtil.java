package cn.exrick.xboot.common.profiler.core.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

public class JoinPoinUtil {
    private JoinPoinUtil() {
    }

    public static String createMethodFullName(Method method) {
        Class<?> declaringClazz = method.getDeclaringClass();
        String pageName = getPkgName(declaringClazz);
        return pageName + "." + method.getName();
    }

    public static Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        return getTargetClass(jp).getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    public static Class<? extends Object> getTargetClass(JoinPoint jp) {
        return jp.getTarget().getClass();
    }

    private static String getPkgName(Class<?> declaringClazz) {
        String name = declaringClazz.getName();
        if (name.startsWith("com.sun.proxy")) {
            Class<?>[] declaredClasses = declaringClazz.getInterfaces();
            if (declaredClasses != null && declaredClasses.length > 0) {
                return declaredClasses[0].getName();
            }
        } else {
            return declaringClazz.getName();
        }
        return null;
    }
}
