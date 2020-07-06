package jnu.ssc.server.dao;

import jnu.ssc.server.domain.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdministratorMapper {

    @Select("select * from administrator where id=#{id} and password=#{password}")
    Administrator getAdministrator(@Param("id") String id, @Param("password") String password);

}
