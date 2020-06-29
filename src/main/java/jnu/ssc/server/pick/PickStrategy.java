package jnu.ssc.server.pick;

import jnu.ssc.server.domain.OrderInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PickStrategy {

    //分配拣货任务（获取拣货列表）
    List<OrderInfo> getPickList();
    //拣货成功同步数据库
    void pickOver(String orderId, String clothesId);

}