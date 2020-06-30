package jnu.ssc.server.service;

import jnu.ssc.server.dao.ClothesMapper;
import jnu.ssc.server.domain.Clothes;
import jnu.ssc.server.inventory.InventoryManager;
import jnu.ssc.server.pick.Pick;
import jnu.ssc.server.pick.PickManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    //库存查询
    private ClothesMapper clothesMapper;
    @Autowired
    public void setClothesMapper(ClothesMapper clothesMapper){
        this.clothesMapper=clothesMapper;
    }

    public Clothes queryClothesInfo(String id){
        return clothesMapper.getClothesById(id);
    }

    //订单拣货
    private PickManager pickManager;
    @Autowired
    public  void setPickManager(PickManager pickManager){
        this.pickManager=pickManager;
    }

    public Pick[] assignPickTask(){
        return pickManager.assignPickTask();
    }

    public void pickOver(String orderId,String clothesId){
        pickManager.pickOver(orderId,clothesId);
    }

    //库存盘点
    private InventoryManager inventoryManager;
    @Autowired
    public void setInventoryManager(InventoryManager inventoryManager){
        this.inventoryManager=inventoryManager;
    }
    public Clothes[] assignInventoryTask(){
        return inventoryManager.assignInventoryTask();
    }

    public void inventoryResultUpdate(String id, int amount){
        clothesMapper.updateAmountById(id,amount);
    }

}