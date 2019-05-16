package cn.exrick.xboot.common.profiler.core.helper;


import cn.exrick.xboot.common.profiler.connector.annotation.PrintProfiler;

import java.lang.reflect.Method;

public class PrintProfilerHelper {

	public static int getElapseTimeIfNull(Method method, int defaultTime) {
		int time = defaultTime;
		if (method.getDeclaringClass().isAnnotationPresent(PrintProfiler.class)) {
			time = method.getDeclaringClass().getAnnotation(PrintProfiler.class).elapseTime();
		}
		
		if (method.isAnnotationPresent(PrintProfiler.class)) {
			time = method.getAnnotation(PrintProfiler.class).elapseTime();
		}
		return time;
	}

	/**
	 * 
	 * 
	 * @param method
	 * @return
	 */
	public static boolean isMethodExistsPrintProfiler(Method method) {

		boolean isClazzNeedProfiler = method.getDeclaringClass().isAnnotationPresent(PrintProfiler.class);
		boolean isMethodNeedProfiler = method.isAnnotationPresent(PrintProfiler.class);
		return isClazzNeedProfiler || isMethodNeedProfiler;
	}

}
