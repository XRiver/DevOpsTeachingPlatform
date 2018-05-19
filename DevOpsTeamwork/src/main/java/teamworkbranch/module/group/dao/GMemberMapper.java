package teamworkbranch.module.group.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import teamworkbranch.module.group.model.GMember;

import java.util.List;


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
        int deleteGMember(@Param("groupId") int groupId, @Param("member_Name") String member_Name);


        /**
        * 修改团队成员管理权限
        * @param gMember
        * @return
        */
        int editGMember(GMember gMember);

        /**
        * 查看团队成员列表
        * @param groupId
        * @return
        */
        List<GMember> getGMemberByGroup(int groupId);

        /**
        *通过ID获取团队成员信息
        * @param groupId
        * @param member_Name
        * @return
        */
        GMember selectById(@Param("groupId") int groupId,@Param("member_Name") String member_Name);



}
