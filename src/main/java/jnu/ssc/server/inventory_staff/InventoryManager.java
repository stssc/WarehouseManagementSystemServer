package jnu.ssc.server.inventory_staff;

import org.springframework.stereotype.Component;

@Component
public class InventoryManager {

    private InventoryStrategy inventoryStrategy;
    public InventoryManager(InventoryStrategy inventoryStrategy){
        this.inventoryStrategy=inventoryStrategy;
    }

    //获取盘点任务
    public InventoryTaskDetail[] getInventoryTask(String staffId){
        return inventoryStrategy.getInventoryList(staffId);
    }

    //根据盘点结果更新数据库
    public void inventoryResultUpdate(String clothesId,int amount){
        inventoryStrategy.inventoryUpdate(clothesId,amount);
    }

    public void inventoryOver(String staffId,String shelf,int position){
        inventoryStrategy.inventoryOver(staffId,shelf,position);
    }

}
