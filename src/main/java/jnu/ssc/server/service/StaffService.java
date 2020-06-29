package jnu.ssc.server.service;

import jnu.ssc.server.WarehouseManagementSystemApplication;
import jnu.ssc.server.dao.ClothesMapper;
import jnu.ssc.server.dao.OrderInfoMapper;
import jnu.ssc.server.domain.Clothes;
import jnu.ssc.server.domain.OrderInfo;
import jnu.ssc.server.pick.PickManager;
import jnu.ssc.server.pick.PickStrategy;
import jnu.ssc.server.pick.PickStrategyAll;
import org.apache.catalina.mbeans.MBeanFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

    public List<OrderInfo> assignPickTask(){
        return pickManager.assignPickTask();
    }

    public void pickOver(String orderId,String clothesId){
        pickManager.pickOver(orderId,clothesId);
    }

    //库存盘点
    public Clothes queryAmount(String shelf, int position){
        return clothesMapper.getClothesByPosition(shelf,position);
    }

    public void updateAmount(String id, int amount){
        clothesMapper.updateAmountById(id,amount);
    }

}