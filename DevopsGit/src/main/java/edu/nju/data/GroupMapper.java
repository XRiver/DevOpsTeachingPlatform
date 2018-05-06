package edu.nju.data;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Administrator on 2018/4/1.
 */
@Mapper
public interface GroupMapper {

    @Select("Select gitlabID from groupandgitlabgroup where groupID = #{groupID}")
    public String getGitlabID(String groupID);

    @Insert("Insert into groupandgitlabuser(groupID,gitlabID) values (#{groupID},#{gitlabID})")
    public int insert(@Param("groupID") String groupID, @Param("gitlab") String gitlabID);
}
