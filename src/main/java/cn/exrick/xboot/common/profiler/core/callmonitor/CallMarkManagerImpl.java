package cn.exrick.xboot.common.profiler.core.callmonitor;

import cn.exrick.xboot.common.profiler.connector.annotation.PrintLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallMarkManagerImpl implements CallMarkManager {

    private static Logger log = LoggerFactory.getLogger("profiler.callMark.log");


    @Override
    public void showCallMark(CallMark callMark) {
        log.info(callMark.toString());
    }
    
    @Override
    public void showCallMark(CallMark callMark, PrintLevel printLevel) {
        if (printLevel == PrintLevel.DEBUG) {
            log.debug(callMark.toString());

        } else if (printLevel == PrintLevel.INFO) {
            log.info(callMark.toString());

        } else if (printLevel == PrintLevel.WARN) {
            log.warn(callMark.toString());

        } else if (printLevel == PrintLevel.ERROR) {
            log.error(callMark.toString());
        }

    }

}
