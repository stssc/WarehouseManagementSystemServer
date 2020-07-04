package jnu.ssc.server.inventory_staff;

import jnu.ssc.server.dao.InventoryTaskMapper;
import jnu.ssc.server.domain.Clothes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryStrategyPart implements InventoryStrategy {
    private InventoryTaskMapper inventoryTaskMapper;
    @Autowired
    private void setInventoryTaskMapper(InventoryTaskMapper inventoryTaskMapper){
        this.inventoryTaskMapper=inventoryTaskMapper;
    }

    @Override
    public Clothes[] getInventoryList(String staffId) {
        return inventoryTaskMapper.getInventoryClothes(staffId);
    }

    @Override
    public void inventoryUpdate(String clothesId, int amount) {

    }
}
