package jnu.ssc.server.inventory;

import jnu.ssc.server.domain.Clothes;
import org.springframework.stereotype.Component;

@Component
public class InventoryManager {

    private InventoryStrategy inventoryStrategy;
    public InventoryManager(InventoryStrategy inventoryStrategy){
        this.inventoryStrategy=inventoryStrategy;
    }

    //分配盘点任务（获取库存信息列表）
    public Clothes[] assignInventoryTask(){
        return inventoryStrategy.getInventoryList();
    }

    //根据盘点结果更新数据库
    public void inventoryResultUpdate(String clothesId,int amount){
        inventoryStrategy.inventoryUpdate(clothesId,amount);
    }

}
