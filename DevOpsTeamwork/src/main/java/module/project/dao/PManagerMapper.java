package module.project.dao;

import module.project.model.PManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liying on 2018/4/16.
 */
@Repository
public interface PManagerMapper {


        /**
         * 新增管理人员
         *
         */
        int insertPManager(PManager pManager) ;

        /**
         *
         * 删除管理人员
         */
        int deletePManager(@Param("projectId") int projectId, @Param("manager") String manager);




}
