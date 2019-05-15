package cn.exrick.xboot.common.profiler;

import cn.exrick.xboot.common.profiler.connector.annotation.PrintLevel;
import cn.exrick.xboot.common.profiler.core.ExecData;
import cn.exrick.xboot.common.profiler.core.ExecNode;
import cn.exrick.xboot.common.profiler.core.LogManager;
import cn.exrick.xboot.common.profiler.core.LogManagerImpl;
import cn.exrick.xboot.common.profiler.core.callmonitor.CallMark;
import cn.exrick.xboot.common.profiler.core.callmonitor.CallMarkManager;
import cn.exrick.xboot.common.profiler.core.callmonitor.CallMarkManagerImpl;

import java.util.Calendar;
/**
* @Description 
* @Author
* @Date 8:59 2019/2/28
* @Parameters 
* @ReturnType        
**/
public class Profiler {

	private Profiler(){}


	private static final  ThreadLocal<ExecData> dataHolder = new ThreadLocal<>();

	private static int elapseTimeMsToLog = 500;
	
	private static LogManager logManager = new LogManagerImpl();
	private static CallMarkManager callMarkManager = new CallMarkManagerImpl();

	public static void start(String logName) {
		start(logName, elapseTimeMsToLog);
	}

	public static void start(String logName, int elapseTimeMsToLog) {
		ExecData execData = dataHolder.get();
		ExecNode currentNode = new ExecNode(logName, System.currentTimeMillis(), elapseTimeMsToLog);
		if (execData == null) {
			execData = new ExecData();
			execData.root = currentNode;
			dataHolder.set(execData);
		} else {
			ExecNode parent = execData.currentNode;
			currentNode.setParent(parent);
			parent.getChildList().add(currentNode);
		}
		execData.currentNode = currentNode;
		execData.level++;
	}

	public static void stop() {
		ExecData execData = dataHolder.get();
		long nowTime = System.currentTimeMillis();
		execData.currentNode.setEndTime(nowTime);
		ExecNode child = execData.currentNode;
		execData.currentNode = child.getParent();
		execData.level--;
		if (execData.level == 0) {
			if ((nowTime - execData.root.getStartTime()) >= execData.root.getElapseTimeMsToLog() || execData.showflag) {
				logManager.showTree(execData.root);
			}
			dataHolder.remove();
		} else {
			if (child.getExecTime() >= child.getElapseTimeMsToLog()) {
				execData.showflag = true;
			}
		}
	}


	public static void callMark(String methodFullName, Object[] args, PrintLevel printLevel) {
		CallMark callMark = new CallMark(Calendar.getInstance(), methodFullName, args);
		callMarkManager.showCallMark(callMark, printLevel);
	}


	public static void callMark(String methodFullName) {
		CallMark callMark = new CallMark(Calendar.getInstance(), methodFullName);
		callMarkManager.showCallMark(callMark);
	}
}
