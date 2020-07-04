package jnu.ssc.server.inventory_administrator;

import jnu.ssc.server.domain.Staff;

import java.util.Date;

public interface InventoryTaskAssignStrategy {

    //将盘点任务分给指定若干个工作人员
    void assignInventoryTask(Staff[] staffs, Date ddl);

}