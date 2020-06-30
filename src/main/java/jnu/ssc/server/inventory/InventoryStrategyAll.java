package jnu.ssc.server.inventory;

import jnu.ssc.server.dao.ClothesMapper;
import jnu.ssc.server.domain.Clothes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryStrategyAll implements InventoryStrategy {
    private ClothesMapper clothesMapper;
    @Autowired
    public void setClothesMapper(ClothesMapper clothesMapper){
        this.clothesMapper=clothesMapper;
    }

    @Override
    public Clothes[] getInventoryList() {
        return clothesMapper.getAllClothes();
    }

    @Override
    public void inventoryUpdate(String clothesId, int amount) {
        clothesMapper.updateAmountById(clothesId,amount);
    }
}
