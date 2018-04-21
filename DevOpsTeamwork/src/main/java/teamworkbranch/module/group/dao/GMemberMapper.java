package teamworkbranch.module.group.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * Created by liying on 2018/4/16.
 */
@Repository
public interface GMemberMapper {


        /**
         * 新增团队成员
         *
         */
        int insertGMember(@Param("groupId") int groupId, @Param("member") String member
                ,@Param("is_manager") int is_manager);

        /**
         *
         * 删除团队成员
         */
        int deleteGMember(@Param("groupId") int groupId, @Param("member") String member);


        /**
        * 修改团队成员管理权限
        * @param groupId
        * @param member
        * @param is_manager
        * @return
        */
        int editGMember(@Param("groupId") int groupId, @Param("member") String member
                ,@Param("is_manager") int is_manager);




}
