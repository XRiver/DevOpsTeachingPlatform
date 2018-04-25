package teamworkbranch.module.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamworkbranch.module.log.dao.LogMapper;
import teamworkbranch.module.log.model.Log;
import teamworkbranch.module.log.service.LogService;

import java.util.List;

/**
 * Created by liying on 2018/4/25.
 */
@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;


    @Override
    public boolean addLog(Log log) {
        return false;
    }

    @Override
    public boolean deleteLog(int logId) {
        return false;
    }

    @Override
    public List<Log> getLogByProject(int projecrId) {
        return null;
    }

    @Override
    public Log getLog(int logId) {
        return null;
    }
}
