package teamworkbranch.module.log.dao;

import org.springframework.stereotype.Repository;
import teamworkbranch.module.log.model.Log;

import java.util.List;

/**
 * Created by liying on 2018/4/25.
 */
@Repository
public interface LogMapper {
    /**
     * 插入Log
     * @param log
     * @return
     */
    int insertLog(Log log);

    /**
     * 删除log
     * @param logId
     * @return
     */
    int deleteLog(int logId);

    /**
     * 挑选某个项目的log
     * @param projectId
     * @return
     */
    List<Log> selectLogByProject(int projectId);

    /**
     * 通过id查找log
     * @param logId
     * @return
     */
    Log selectLogById(int logId);


}
