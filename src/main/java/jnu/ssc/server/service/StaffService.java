package jnu.ssc.server.service;

import jnu.ssc.server.back.Back;
import jnu.ssc.server.dao.BackMapper;
import jnu.ssc.server.dao.ClothesMapper;
import jnu.ssc.server.dao.InventoryTaskMapper;
import jnu.ssc.server.dao.SpaceMapper;
import jnu.ssc.server.domain.Clothes;
import jnu.ssc.server.domain.Space;
import jnu.ssc.server.inventory_staff.InventoryManager;
import jnu.ssc.server.pick.Pick;
import jnu.ssc.server.pick.PickManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private InventoryTaskMapper inventoryTaskMapper;
    @Autowired
    public void setInventoryTaskMapper(InventoryTaskMapper inventoryTaskMapper){
        this.inventoryTaskMapper=inventoryTaskMapper;
    }

    public Clothes[] queryInventoryTask(String staffId){
        return inventoryManager.assignInventoryTask(staffId);
    }

    public void inventoryOver(String staffId,String shelf,int position){
        inventoryTaskMapper.setInventoryOver(staffId,shelf,position);
    }

    public void inventoryResultUpdate(String id, int amount){
        clothesMapper.updateAmountById(id,amount);
    }

    //商品入库
    private SpaceMapper spaceMapper;
    @Autowired
    public void setSpaceMapper(SpaceMapper spaceMapper){
        this.spaceMapper=spaceMapper;
    }

    public Space getAnEmptySpace(){
        return spaceMapper.getAnEmptySpace();
    }

    public void storeANewClothes(String id,String shelf,int position,int amount){
        clothesMapper.insertClothes(id,shelf,position,amount);
    }

    //订单退货
    private BackMapper backMapper;
    @Autowired
    public void setBackMapper(BackMapper backMapper){
        this.backMapper=backMapper;
    }

    public Back queryBackInfo(String orderId,String clothesId){
        return backMapper.getBackInfoById(orderId,clothesId);
    }

    public void backOver(String orderId,String clothesId){
        backMapper.setBackOver(orderId,clothesId);
    }

}