package jnu.ssc.server.dao;

import jnu.ssc.server.domain.Clothes;
import jnu.ssc.server.domain.InventoryTask;
import jnu.ssc.server.domain.Staff;
import jnu.ssc.server.inventory_staff.InventoryTaskDetail;
import org.apache.ibatis.annotations.*;

import java.util.Date;

@Mapper
public interface InventoryTaskMapper {

    @Insert("insert into inventory_task value(null,#{staffId},#{shelf},#{position},0)")
    void insertInventoryTask(@Param("staffId") String staffId, @Param("shelf") String shelf, @Param("position") int position);

    @Select("select distinct staff_id from inventory_task")
    String[] getInventoryStaffId();

    @Select("select sum(amount) from clothes C,inventory_task IT where staff_id=#{staffId} and C.shelf=IT.shelf and C.position=IT.position")
    int getStaffInventoryAmount(@Param("staffId") String staffId);

    //查询某职工盘点任务完成进度百分比
    @Select("select count(*)/(select count(*) from inventory_task where staff_id=#{staffId}) from inventory_task where staff_id=#{staffId} and state=1")
    double queryInventoryRate(@Param("staffId") String staffId);

    //管理员查询分配给某职工的盘点任务的第一个货位和最后一个货位（因为分配到的货位都是连续的，所以客户端只显示首尾即可，因此只查首尾既不影响需求，又可以减少在网络中的通信量）
    @Select("select * from inventory_task where id=(select min(id) from inventory_task where staff_id=#{staffId}) or id=(select max(id) from inventory_task where staff_id=#{staffId})")
    InventoryTask[] queryHeadAndTailOfInventoryTask(@Param("staffId") String staffId);

    //职工查询被分配到的尚未盘点的任务详情（记得给clothes.id重命名，不然映射不到InventoryTaskDetail类里的clothesId里去）
    @Select("select clothes.id clothes_id,clothes.shelf,clothes.position,amount,staff_id,state from clothes,inventory_task where staff_id=#{staffId} and state=0 and clothes.shelf=inventory_task.shelf and clothes.position=inventory_task.position")
    InventoryTaskDetail[] getInventoryClothes(@Param("staffId") String staffId);

    @Update("update inventory_task set state=1 where staff_id=#{staffId} and shelf=#{shelf} and position=#{position}")
    void setInventoryOver(@Param("staffId") String staffId,@Param("shelf") String shelf,@Param("position") int position);

}