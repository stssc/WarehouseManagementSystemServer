package jnu.ssc.server.inventory_staff;

import jnu.ssc.server.domain.Clothes;

public interface InventoryStrategy {

    //分配盘点任务
    InventoryTaskDetail[] gotInventoryList(String staffId);
    //根据盘点结果更新数据库
    void inventoryUpdate(String clothesId,int amount);
    //盘点完成
    void inventoryOver(String staffId,String shelf,int position);

}
