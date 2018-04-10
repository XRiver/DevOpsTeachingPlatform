package edu.nju.data;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Administrator on 2018/4/1.
 */
@Mapper
public interface UserMapper {


    @Select("Select gitlabID from userandgitlabuser where userID = #{userID}")
    public String getGitlabID(String userID);

    @Insert("Insert into userandgitlabuser(userID,gitlabID) values (#{userID},#{gitlabID})")
    public String insert(String userID,String gitlabID);
}
