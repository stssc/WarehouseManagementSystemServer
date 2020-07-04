package jnu.ssc.server.inventory_administrator;

import jnu.ssc.server.domain.Staff;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InventoryTaskAssignManager {

    private InventoryTaskAssignStrategy inventoryTaskAssignStrategy;
    public InventoryTaskAssignManager(InventoryTaskAssignStrategy inventoryTaskAssignStrategy){
        this.inventoryTaskAssignStrategy=inventoryTaskAssignStrategy;
    }

    public void assignInventoryTask(Staff[] staffs, Date ddl){
        inventoryTaskAssignStrategy.assignInventoryTask(staffs,ddl);
    }

}
