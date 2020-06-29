package jnu.ssc.server.pick;

import jnu.ssc.server.dao.OrderInfoMapper;
import jnu.ssc.server.domain.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PickStrategyAll implements PickStrategy {//拣货任务分配策略：所有人都能拣所有货，多人同时拣货时采取线程同步措施

    private OrderInfoMapper orderInfoMapper;
    @Autowired
    public void setOrderInfoMapper(OrderInfoMapper orderInfoMapper){
        this.orderInfoMapper=orderInfoMapper;
    }

    @Override
    public List<OrderInfo> getPickList() {
        return orderInfoMapper.getOrderInfoListOrderByPosition();
    }

    @Override
    public void pickOver(String orderId, String clothesId) {
        orderInfoMapper.setOrderInfoPickOver(orderId,clothesId);
    }

}