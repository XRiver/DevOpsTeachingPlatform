package module.group.DAO;

import module.group.model.GMember;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by caosh on 2018/4/12.
 */
@Repository
public interface GMemberMapper {

    /**
     * 新增成员
     *
     */
    int insertGMember(GMember gMember) ;

    /**
     *
     * 删除成员
     */
    int deleteGMember(@Param("groupId") int projectId, @Param("manager") String manager);

}
