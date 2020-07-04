package jnu.ssc.server.inventory_staff;

import jnu.ssc.server.domain.Clothes;
import org.springframework.stereotype.Component;

@Component
public class InventoryManager {

    private InventoryStrategy inventoryStrategy;
    public InventoryManager(InventoryStrategy inventoryStrategy){
        this.inventoryStrategy=inventoryStrategy;
    }

    //获取盘点任务
    public Clothes[] assignInventoryTask(String staffId){
        return inventoryStrategy.getInventoryList(staffId);
    }

    //根据盘点结果更新数据库
    public void inventoryResultUpdate(String clothesId,int amount){
        inventoryStrategy.inventoryUpdate(clothesId,amount);
    }

}
