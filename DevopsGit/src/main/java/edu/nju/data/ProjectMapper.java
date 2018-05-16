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
public interface ProjectMapper {

    @Select("Select gitlabID from projectandgitlabproject where projectID = #{projectID}")
    public String getGitlabID(String projectID);


    @Insert("Insert into projectandgitlabproject(projectID,gitlabID) values (#{projectID},#{gitlabID})")
    public int insert(@Param("projectID") String projectID, @Param("gitlabID") String gitlabID);
}
