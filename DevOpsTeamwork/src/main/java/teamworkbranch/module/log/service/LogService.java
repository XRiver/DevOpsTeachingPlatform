package teamworkbranch.module.log.service;

import teamworkbranch.module.log.model.Log;

import java.util.List;

/**
 * Created by liying on 2018/4/25.
 */
public interface LogService {

    /**
     *插入log
     * @param log
     * @return
     */
    boolean addLog(Log log);

    /**
     * 删除log
     * @param logId
     * @return
     */
    boolean deleteLog(int logId);

    /**
     * 获得一个项目的log
     * @param projecrId
     * @return
     */
    List<Log> getLogByProject(int projecrId);

    /**
     * 根据id获得一个log
     * @param logId
     * @return
     */
    Log getLog(int logId);
}
