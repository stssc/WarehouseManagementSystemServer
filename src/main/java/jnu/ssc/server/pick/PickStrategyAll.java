package jnu.ssc.server.pick;

import jnu.ssc.server.dao.OrderInfoMapper;
import jnu.ssc.server.dao.PickMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PickStrategyAll implements PickStrategy {//拣货任务分配策略：所有人都能拣所有货，多人同时拣货时采取线程同步措施

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
        return pickMapper.getOrderInfoListOrderByPositionGroupByOrder();
    }

    @Override
    public void pickOver(String orderId, String clothesId) {
        orderInfoMapper.setOrderInfoPickOver(orderId,clothesId);
    }

}