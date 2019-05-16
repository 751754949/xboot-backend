package cn.exrick.xboot.modules.your.serviceimpl;

import cn.exrick.xboot.modules.your.dao.XbootDao;
import cn.exrick.xboot.modules.your.entity.Xboot;
import cn.exrick.xboot.modules.your.service.XbootService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试接口实现
 * @author Exrick
 */
@Slf4j
@Service
@Transactional
public class XbootServiceImpl implements XbootService {

    @Autowired
    private XbootDao xbootDao;

    @Override
    public XbootDao getRepository() {
        return xbootDao;
    }
}