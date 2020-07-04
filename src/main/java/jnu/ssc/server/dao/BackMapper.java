package jnu.ssc.server.dao;

import jnu.ssc.server.back.Back;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BackMapper {

    @Select("select order_id,clothes_id,back_amount,shelf,position from order_info,clothes where order_id=#{orderId} and clothes_id=#{clothesId} and clothes_id=clothes.id")
    Back getBackInfoById(@Param("orderId") String orderId,@Param("clothesId") String clothesId);

    @Update("update order_info set back_amount=0 where order_id=#{orderId} and clothes_id=#{clothesId}")
    void setBackOver(@Param("orderId") String orderId,@Param("clothesId") String clothesId);

}
