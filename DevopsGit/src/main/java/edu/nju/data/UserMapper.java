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
public interface UserMapper {


    @Select("Select gitlabID from userandgitlabuser where userID = #{userID}")
    public String getGitlabID(String userID);

    @Insert("Insert into userandgitlabuser(userID,gitlabID) values (#{userID},#{gitlabID})")
    public int insert(@Param("userID") String userID, @Param("gitlabID") String gitlabID);

    @Select("Select userID from userandgitlabuser where gitlabID = #{gitlabID}")
    public String getUserID(String gitlabID);
}
