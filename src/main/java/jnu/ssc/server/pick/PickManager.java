package jnu.ssc.server.pick;

import org.springframework.stereotype.Component;

@Component
public class PickManager {
    private PickStrategy pickStrategy;
    public PickManager(PickStrategy pickStrategy){
        this.pickStrategy=pickStrategy;
    }

    //分配拣货任务（获取拣货列表）
    public Pick[] assignPickTask(){
        return pickStrategy.gotPickList();
    }

    //拣货成功同步数据库
    public void pickOver(String orderId, String clothesId){
        pickStrategy.pickOver(orderId,clothesId);
    }
}