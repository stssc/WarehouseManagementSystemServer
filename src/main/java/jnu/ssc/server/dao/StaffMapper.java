package jnu.ssc.server.dao;

import jnu.ssc.server.domain.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StaffMapper {

    @Select("select staff.id,staff.name from staff where id=#{id}")
    Staff getStaffById(@Param("id") String id);

    @Select("select * from staff where id=#{id} and password=#{password}")
    Staff getStaff(@Param("id") String id,@Param("password") String password);

}
