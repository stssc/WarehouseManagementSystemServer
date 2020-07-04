package jnu.ssc.server.dao;

import jnu.ssc.server.domain.Clothes;
import jnu.ssc.server.domain.InventoryTask;
import org.apache.ibatis.annotations.*;

import java.util.Date;

@Mapper
public interface InventoryTaskMapper {

    @Insert("insert into inventory_task value(null,#{staffId},#{shelf},#{position},#{ddl},0)")
    void insertInventoryTask(@Param("staffId") String staffId, @Param("shelf") String shelf, @Param("position") int position, @Param("ddl")Date ddl);

    //查询某职工盘点任务完成进度百分比
    @Select("select count(*)/(select count(*) from inventory_task where staff_id=#{staffId}) from inventory_task where staff_id=#{staffId} and state=1")
    double queryInventoryRate(@Param("staffId") String staffId);

    //查询分配给某职工的盘点任务的第一个货位和最后一个货位（因为分配到的货位都是连续的，所以客户端只显示首尾即可，因此只查首尾既不影响需求，又可以减少在网络中的通信量）
    @Select("select * from inventory_task where id=(select min(id) from inventory_task where staff_id=#{staffId}) or id=(select max(id) from inventory_task where staff_id=#{staffId})")
    InventoryTask[] queryHeadAndTailOfInventoryTask(@Param("staffId") String staffId);

    @Select("select clothes.* from clothes,inventory_task where staff_id=#{staffId} and clothes.shelf=inventory_task.shelf and clothes.position=inventory_task.position")
    Clothes[] getInventoryClothes(@Param("staffId") String staffId);

    @Update("update inventory_task set state=1 where staff_id=#{staffId} and shelf=#{shelf} and position=#{position}")
    void setInventoryOver(@Param("staffId") String staffId,@Param("shelf") String shelf,@Param("position") int position);

}