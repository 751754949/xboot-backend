package cn.exrick.xboot.common.profiler.core.callmonitor;

import cn.exrick.xboot.common.profiler.connector.annotation.PrintLevel;

public interface CallMarkManager {

    public void showCallMark(CallMark callMark);

    public void showCallMark(CallMark callMark, PrintLevel printLevel);
}
