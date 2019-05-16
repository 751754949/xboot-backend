package cn.exrick.xboot.common.profiler.connector;

import cn.exrick.xboot.common.profiler.Profiler;
import cn.exrick.xboot.common.profiler.core.helper.PrintProfilerHelper;
import cn.exrick.xboot.common.profiler.core.util.JoinPoinUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Aspect
@Configuration
public class PrintProfilerAspect {

	private int monitorTime;



	@Pointcut("execution(* cn.exrick.xboot..*.*(..))")
	public void pointcut() {
	}

	@Around("pointcut()")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		String methodFullName = "unkonwMethod()";
		int monitorTime = 100;
		try {
			Method method = JoinPoinUtil.getMethod(pjp);
			methodFullName = JoinPoinUtil.createMethodFullName(method);
			monitorTime = PrintProfilerHelper.getElapseTimeIfNull(method, monitorTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Profiler.start(methodFullName, monitorTime);
			return pjp.proceed();
		} finally {
			Profiler.stop();
		}
	}



}
