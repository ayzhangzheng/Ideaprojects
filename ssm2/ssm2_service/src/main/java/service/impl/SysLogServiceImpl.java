package service.impl;


import com.github.pagehelper.PageHelper;
import dao.ISysLogDao;
import domain.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ISysLogService;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    public List<SysLog> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page, size);
        return sysLogDao.findAll();
    }

    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }
}
