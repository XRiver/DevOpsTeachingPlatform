package teamworkbranch.module.log.service;

import teamworkbranch.exception.NonprivilegedUserException;
import teamworkbranch.exception.NotExistedException;
import teamworkbranch.module.log.model.Log;

import java.util.List;

/**
 * Created by liying on 2018/4/25.
 */
public interface LogService {

    /**
     *插入log
     * @param info
     * @param username
     * @param projectId
     * @return
     */
    boolean addLog(String info, String username, String projectId);

    /**
     * 删除log
     * @param logId
     * @param username
     * @return
     */
    boolean deleteLog(int logId,String username) throws NotExistedException, NonprivilegedUserException;

    /**
     * 获得一个项目的log
     * @param projecrId
     * @return
     */
    List<Log> getLogByProject(int projecrId) throws NotExistedException;

    /**
     * 根据id获得一个log
     * @param logId
     * @return
     */
    Log getLog(int logId) throws NotExistedException;
}
