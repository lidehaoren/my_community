package study.lide.community.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import study.lide.community.model.User;

/**
 * @author lide
 * @create 2021-06-26-11:53
 */
@Component
@Mapper
public interface UserMapper {
    @Insert("insert into user(name, account_id, token, gmt_create, gmt_modify) values(#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModify})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token")String token);
}
