package jnu.ssc.server.inventory_staff;

import com.google.gson.Gson;
import jnu.ssc.server.dao.ClothesMapper;
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

    private ClothesMapper clothesMapper;
    @Autowired
    public void setClothesMapper(ClothesMapper clothesMapper){
        this.clothesMapper=clothesMapper;
    }

    @Override
    public InventoryTaskDetail[] getInventoryList(String staffId) {
        return inventoryTaskMapper.getInventoryClothes(staffId);
    }

    @Override
    public void inventoryUpdate(String clothesId, int amount) {
        clothesMapper.updateAmountById(clothesId,amount);
    }

    @Override
    public void inventoryOver(String staffId, String shelf, int position) {
        inventoryTaskMapper.setInventoryOver(staffId,shelf,position);
    }
}
