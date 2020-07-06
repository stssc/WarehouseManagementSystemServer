package jnu.ssc.server.pick;

import jnu.ssc.server.dao.OrderInfoMapper;
import jnu.ssc.server.dao.PickMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PickStrategyPart implements PickStrategy {//拣货任务分配策略：每个人最多拣5个sku
    private static final int MAX_SKU=5;

    private PickMapper pickMapper;
    @Autowired
    public void setPickMapper(PickMapper pickMapper){
        this.pickMapper=pickMapper;
    }

    private OrderInfoMapper orderInfoMapper;
    @Autowired
    public void setOrderInfoMapper(OrderInfoMapper orderInfoMapper){
        this.orderInfoMapper=orderInfoMapper;
    }

    @Override
    public Pick[] gotPickList() {
        Pick[] picks=pickMapper.getOrderInfoListOrderByPositionGroupByOrder();
        List<Pick> pickList=new ArrayList<>();
        int sku=0;
        for (Pick pick:picks){
            if (sku+pick.getAmount()>MAX_SKU)
                continue;
            else if (sku+pick.getAmount()==MAX_SKU){
                pickList.add(pick);
                break;
            }
            else{
                pickList.add(pick);
                sku+=pick.getAmount();
            }
        }
        return pickList.toArray(new Pick[0]);//所以传这个new Pick[0]干嘛用？只是为了告诉abstract<T> T[] toArray() T的类型吗哈哈哈？
    }

    @Override
    public void pickOver(String orderId, String clothesId) {
        orderInfoMapper.setOrderInfoPickOver(orderId,clothesId);
    }
}
