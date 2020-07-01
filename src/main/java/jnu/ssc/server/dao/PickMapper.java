package jnu.ssc.server.dao;

import jnu.ssc.server.pick.Pick;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PickMapper {

    @Select("select order_info.*,clothes.shelf,clothes.position from order_info,clothes where state=0 and order_info.clothes_id=clothes.id order by order_id,shelf,position;")
   Pick[] getOrderInfoListOrderByPositionGroupByOrder();

}