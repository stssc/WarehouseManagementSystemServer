package jnu.ssc.server.inventory;

import jnu.ssc.server.domain.Clothes;
import org.springframework.stereotype.Component;

@Component
public interface InventoryStrategy {

    //分配盘点任务
    Clothes[] getInventoryList();
    //根据盘点结果更新数据库
    void inventoryUpdate(String clothesId,int amount);

}
