package edu.nju.data;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Administrator on 2018/4/1.
 */
@Mapper
public interface ProjectMapper {

    @Select("Select gitlabID from projectandgitlabproject where projectID = #{projectID}")
    public String getGitlabID(String projectID);


    @Insert("Insert into projectandgitlabuser(projectID,gitlabID) values (#{projectID},#{gitlabID})")
    public String insert(String projectID,String gitlabID);
}
