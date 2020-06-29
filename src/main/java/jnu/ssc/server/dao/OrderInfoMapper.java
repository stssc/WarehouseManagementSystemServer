package jnu.ssc.server.dao;

import jnu.ssc.server.domain.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderInfoMapper {

    @Select("select order_info.* from order_info,clothes where state=0 and order_info.clothes_id=clothes.id order by shelf,position")
    List<OrderInfo> getOrderInfoListOrderByPosition();

    @Update("update order_info set state=1 where order_id=#{orderId} and clothes_id=#{clothesId}")
    void setOrderInfoPickOver(String orderId,String clothesId);

}