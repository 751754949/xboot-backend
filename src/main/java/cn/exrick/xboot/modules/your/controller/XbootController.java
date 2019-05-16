package cn.exrick.xboot.modules.your.controller;

import cn.exrick.xboot.base.XbootBaseController;
import cn.exrick.xboot.common.utils.PageUtil;
import cn.exrick.xboot.common.utils.ResultUtil;
import cn.exrick.xboot.common.vo.PageVo;
import cn.exrick.xboot.common.vo.Result;
import cn.exrick.xboot.modules.your.entity.Xboot;
import cn.exrick.xboot.modules.your.service.XbootService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Exrick
 */
@Slf4j
@RestController
@Api(description = "测试管理接口")
@RequestMapping("/xboot/xboot")
@Transactional
public class XbootController extends XbootBaseController<Xboot, String>{

    @Autowired
    private XbootService xbootService;

    @Override
    public XbootService getService() {
        return xbootService;
    }

}
