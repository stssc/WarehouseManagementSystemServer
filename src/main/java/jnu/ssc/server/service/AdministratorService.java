package jnu.ssc.server.service;

import jnu.ssc.server.dao.AdministratorMapper;
import jnu.ssc.server.dao.InventoryTaskMapper;
import jnu.ssc.server.dao.StaffMapper;
import jnu.ssc.server.domain.InventoryTask;
import jnu.ssc.server.domain.Staff;
import jnu.ssc.server.inventory_administrator.InventoryTaskAssignManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {

    //登录
    private AdministratorMapper administratorMapper;
    @Autowired
    public void setAdministratorMapper(AdministratorMapper administratorMapper){
        this.administratorMapper=administratorMapper;
    }

    public boolean administratorLogin(String id,String password){
        return administratorMapper.getAdministrator(id,password)!=null;
    }

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

    public void assignInventoryTask(Staff[] staffs){
        inventoryTaskAssignManager.assignInventoryTask(staffs);
    }

    //查看盘点进度
    private InventoryTaskMapper inventoryTaskMapper;
    @Autowired
    public void setInventoryTaskMapper(InventoryTaskMapper inventoryTaskMapper){
        this.inventoryTaskMapper=inventoryTaskMapper;
    }

    public String[] queryInventoryStaff(){
        return inventoryTaskMapper.getInventoryStaffId();
    }

    public InventoryTask[] queryInventoryTaskSummary(String staffId){
        return inventoryTaskMapper.queryHeadAndTailOfInventoryTask(staffId);
    }

    public int queryInventoryAmount(String staffId){
        return inventoryTaskMapper.getStaffInventoryAmount(staffId);
    }

    public double queryInventoryRate(String staffId){
        return inventoryTaskMapper.queryInventoryRate(staffId);
    }

}