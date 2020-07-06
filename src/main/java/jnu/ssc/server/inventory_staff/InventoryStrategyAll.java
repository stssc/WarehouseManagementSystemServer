package jnu.ssc.server.inventory_staff;

import jnu.ssc.server.dao.ClothesMapper;
import jnu.ssc.server.domain.Clothes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class InventoryStrategyAll implements InventoryStrategy {
    private ClothesMapper clothesMapper;
    @Autowired
    public void setClothesMapper(ClothesMapper clothesMapper){
        this.clothesMapper=clothesMapper;
    }

    @Override
    public InventoryTaskDetail[] gotInventoryList(String staffId) {
        Clothes[] inventoryClothes=clothesMapper.getAllClothes();
        InventoryTaskDetail[] inventoryList=new InventoryTaskDetail[inventoryClothes.length];
        for (int i=0;i<inventoryClothes.length;i++){
            inventoryList[i]=new InventoryTaskDetail(inventoryClothes[i]);
        }
        return inventoryList;
    }

    @Override
    public void inventoryUpdate(String clothesId, int amount) {
        clothesMapper.updateAmountById(clothesId,amount);
    }

    @Override
    public void inventoryOver(String staffId, String shelf, int position) {

    }
}
