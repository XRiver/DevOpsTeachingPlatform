package teamworkbranch.module.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamworkbranch.exception.NonprivilegedUserException;
import teamworkbranch.exception.NotExistedException;
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
    public boolean addLog(String info, String username, String projectId) {
        Log log = new Log(info,username,projectId);
        logMapper.insertLog(log);
        return true;
    }

    @Override
    public boolean deleteLog(int logId,String username) throws NotExistedException, NonprivilegedUserException {
        Log log = logMapper.selectLogById(logId);
        if(log == null){
            throw new NotExistedException();
        } else {
            if (log.getUsername().equals(username)) {
//                System.out.println("aaaaa");
//                System.out.println(log.getLogId());
                logMapper.deleteLog(logId);
            } else
                throw new NonprivilegedUserException();
        }
        return true;
    }

    @Override
    public List<Log> getLogByProject(int projecrId) throws NotExistedException {
        List<Log> logs = logMapper.selectLogByProject(projecrId);
        if (logs == null){
            throw new NotExistedException();
        } else
            return logs;
    }

    @Override
    public Log getLog(int logId) throws NotExistedException {
        Log log = logMapper.selectLogById(logId);
        if (log==null){
            throw new NotExistedException();
        } else
            return log;
    }
}
