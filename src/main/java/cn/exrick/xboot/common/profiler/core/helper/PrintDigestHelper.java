package cn.exrick.xboot.common.profiler.core.helper;

import cn.exrick.xboot.common.profiler.connector.annotation.PrintDigest;
import cn.exrick.xboot.common.profiler.connector.annotation.PrintLevel;
import cn.exrick.xboot.common.profiler.connector.annotation.SkipDigest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class PrintDigestHelper {

    /**
     *
     *
     * @param method
     * @param defaultLevel
     * @return
     */
    public static PrintLevel getPrintLevelIfNull(Method method, PrintLevel defaultLevel) {
        PrintLevel printLevel = defaultLevel;
        if (method.getDeclaringClass().isAnnotationPresent(PrintDigest.class)) {
            printLevel = method.getDeclaringClass().getAnnotation(PrintDigest.class).printLevel();
        }

        if (method.isAnnotationPresent(PrintDigest.class)) {
            printLevel = method.getAnnotation(PrintDigest.class).printLevel();
        }
        return printLevel;
    }

    /**
     *
     *
     * @param method
     * @return
     */
    public static boolean isMethodExistsPrintDigest(Method method) {
        boolean isClazzChangePrintLevel = method.getDeclaringClass().isAnnotationPresent(PrintDigest.class);
        boolean isMethodChangePrintLevel = method.isAnnotationPresent(PrintDigest.class);
        return isClazzChangePrintLevel || isMethodChangePrintLevel;
    }

    /**
     *
     *
     * @param method
     * @return
     */
    public static boolean isMethodNeedSkipPrint(Method method) {

        Class<?> declaringClazz = method.getDeclaringClass();

        boolean isMethodNeedSkipPrint = method.isAnnotationPresent(SkipDigest.class);

        boolean isClazzNeedSkipPrint = declaringClazz.isAnnotationPresent(SkipDigest.class);
        return isMethodNeedSkipPrint || isClazzNeedSkipPrint;

    }

    /**
     *
     *
     * @param method
     * @param arguments
     * @return
     */
    public static Object[] getCanPrintArgs(Method method, Object[] arguments) {
        List<Object> argsList = new ArrayList<Object>();
        if (null == arguments) {
            return argsList.toArray();
        }
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (null != parameterAnnotations) {
            for (int i = 0; i < parameterAnnotations.length; i++) {
                Annotation[] annotations = parameterAnnotations[i];
                if (!hasSkipPrint(annotations)) {
                    argsList.add(arguments[i]);
                }
            }
        }
        return argsList.toArray();
    }

    private static boolean hasSkipPrint(Annotation[] annotations) {
        boolean hasSkipPrint = false;
        if (null == annotations) {
            return false;
        }
        for (int j = 0; j < annotations.length; j++) {

            if (annotations[j].annotationType() == SkipDigest.class) {
                hasSkipPrint = true;
            }
        }
        return hasSkipPrint;
    }
}
