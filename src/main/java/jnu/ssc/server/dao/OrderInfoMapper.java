package jnu.ssc.server.dao;

import jnu.ssc.server.domain.OrderInfo;
import jnu.ssc.server.pick.Pick;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderInfoMapper {

    @Update("update order_info set state=1 where order_id=#{orderId} and clothes_id=#{clothesId}")
    void setOrderInfoPickOver(String orderId,String clothesId);

}