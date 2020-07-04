package jnu.ssc.server.service;

import jnu.ssc.server.dao.InventoryTaskMapper;
import jnu.ssc.server.dao.StaffMapper;
import jnu.ssc.server.domain.InventoryTask;
import jnu.ssc.server.domain.Staff;
import jnu.ssc.server.inventory_administrator.InventoryTaskAssignManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdministratorService {

    //分配盘点任务
    private StaffMapper staffMapper;
    @Autowired
    public void setStaffMapper(StaffMapper staffMapper){
        this.staffMapper=staffMapper;
    }

    private InventoryTaskAssignManager inventoryTaskAssignManager;
    @Autowired
    public void setInventoryTaskAssignManager(InventoryTaskAssignManager inventoryTaskAssignManager){
        this.inventoryTaskAssignManager=inventoryTaskAssignManager;
    }

    public Staff queryStaff(String id){
        return staffMapper.getStaffById(id);
    }

    public void assignInventoryTask(Staff[] staffs, Date ddl){
        inventoryTaskAssignManager.assignInventoryTask(staffs,ddl);
    }

    //查看盘点进度
    private InventoryTaskMapper inventoryTaskMapper;
    @Autowired
    public void setInventoryTaskMapper(InventoryTaskMapper inventoryTaskMapper){
        this.inventoryTaskMapper=inventoryTaskMapper;
    }

    public InventoryTask[] queryInventoryTaskSummary(String staffId){
        return inventoryTaskMapper.queryHeadAndTailOfInventoryTask(staffId);
    }

    public double queryInventoryRate(String staffId){
        return inventoryTaskMapper.queryInventoryRate(staffId);
    }

}