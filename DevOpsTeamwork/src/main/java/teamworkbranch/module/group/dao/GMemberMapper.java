package teamworkbranch.module.group.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import teamworkbranch.module.group.model.GMember;


/**
 * Created by liying on 2018/4/16.
 */
@Repository
public interface GMemberMapper {


        /**
         * 新增团队成员
         *
         */
        int insertGMember(GMember gMember);

        /**
         *
         * 删除团队成员
         */
        int deleteGMember(@Param("groupId") int groupId, @Param("manager") String manager);




}
