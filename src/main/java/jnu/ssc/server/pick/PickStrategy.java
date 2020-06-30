package jnu.ssc.server.pick;

import org.springframework.stereotype.Component;

@Component
public interface PickStrategy {

    //分配拣货任务（获取拣货列表）
    Pick[] getPickList();
    //拣货成功同步数据库
    void pickOver(String orderId, String clothesId);

}