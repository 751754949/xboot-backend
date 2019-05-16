package cn.exrick.xboot.common.profiler.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogManagerImpl implements LogManager{
	private static Logger log = LoggerFactory.getLogger(LogManagerImpl.class);
	@Value("${profilerPointCut}")
	private String profilerPointCut;
	@Override
	public String showTree(ExecNode root) {
		Locale locale = Locale.CHINA;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",locale);
		StringBuilder sb = new StringBuilder(sdf.format(new Date(root.getStartTime()))+ "\n");
		long subTime = 0;
		for (ExecNode child : root.getChildList()) {
			subTime += child.getExecTime();
		}
		sb.append("*");
		buildLog(sb, root.getExecTime(), root.getExecTime() - subTime, 100, 0, root.getMethodName());
		for (ExecNode child : root.getChildList()) {
			showNode(sb, 0, child, root);
		}
		String treeLog = sb.toString();
		log.info(treeLog);
		sb = null;
		sdf = null;
		locale = null;
		return treeLog;
	}

	private void showNode(StringBuilder sb, int level, ExecNode node, ExecNode root) {
		level++;
		long subTime = 0;
		for (ExecNode child : node.getChildList()) {
			subTime += child.getExecTime();
		}
		sb.append("+");
		for(int i = 0; i < level; i++){
			sb.append("---");
		}
		buildLog(sb, node.getExecTime(),
				node.getExecTime() - subTime,
				node.getExecTime() * 100 / (node.getParent().getExecTime() == 0 ? 100 : node.getParent().getExecTime()),
				node.getStartTime() - root.getStartTime(),
				node.getMethodName());

		for (ExecNode child : node.getChildList()) {
			showNode(sb, level, child, root);
		}
	}

	/**
	 * @param sb
	 * @param totalTime  1.该方法总耗时： 即当前方法执行共耗时多少，包括子方法调用的时间。
	 * @param internalTime   2.方法自身执行耗时：即当前方法自身代码耗时多少，不包括在该方法内调用被代理的其他servive方法时间。
	 * @param ratio  3.耗时占比：当前方法总耗时占整个调用链耗时的比例。
	 * @param startTimeFromRoot   4.方法开始时间距离入口方法开始时间差值：当前方法调用开始时，距离调用链入口方法调用开始时的时间已经过去了多久。
	 * @param methodName methodName
	 */
	private void buildLog(StringBuilder sb, long totalTime, long internalTime, long ratio, long startTimeFromRoot, String methodName) {
		sb.append("[" + totalTime + ", " + internalTime + ", " + ratio + "%, " + startTimeFromRoot + "]" + methodName + "{"+profilerPointCut+"}"+"\n");
	}
}
