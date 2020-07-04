package jnu.ssc.server.pick;

public interface PickStrategy {

    //分配拣货任务（获取拣货列表）
    Pick[] gotPickList();
    //拣货成功同步数据库
    void pickOver(String orderId, String clothesId);

}