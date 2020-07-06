package jnu.ssc.server;

import com.google.gson.Gson;
import jnu.ssc.server.domain.Staff;
import jnu.ssc.server.service.AdministratorService;
import jnu.ssc.server.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@EnableAutoConfiguration
public class Controller {
    private Gson gson=new Gson();

    private StaffService staffService;
    @Autowired
    public void setStaffService(StaffService staffService){
        this.staffService=staffService;
    }

    private AdministratorService administratorService;
    @Autowired
    public void setAdministratorService(AdministratorService administratorService){
        this.administratorService=administratorService;
    }

    @RequestMapping("/hello")
    public String hello(){
        return "Welcome to warehouse management system.";
    }

    /*
    工作人员服务区
     */

    //登录
    @RequestMapping("/staff/login")
    public boolean staffLogin(@RequestParam(value="id") String id,@RequestParam(value="password") String password){
        return staffService.staffLogin(id,password);
    }

    //库存查询
    @RequestMapping("/staff/query")
    public String queryClothesInfo(@RequestParam(value="id") String id){
        return gson.toJson(staffService.queryClothesInfo(id));
    }

    //订单拣货-分配拣货任务
    @RequestMapping("/staff/pick_task")
    public String assignPickTask(){
        return gson.toJson(staffService.assignPickTask());
    }

    //订单拣货-拣货完成
    @RequestMapping("/staff/pick_over")
    public void pickOver(@RequestParam(value="orderId") String orderId,@RequestParam(value="clothesId") String clothesId){
        staffService.pickOver(orderId,clothesId);
    }

    //库存盘点-查看盘点任务
    @RequestMapping("/staff/inventory_task")
    public String queryInventoryTask(@RequestParam(value="staffId") String staffId){
        return gson.toJson(staffService.queryInventoryTask(staffId));
    }

    //库存盘点-盘点勘误
    @RequestMapping("/staff/inventory_errata")
    public void inventoryResultUpdate(@RequestParam(value="id") String id,@RequestParam(value="amount") int amount){
        staffService.inventoryResultUpdate(id,amount);
    }

    //库存盘点-盘点完成
    @RequestMapping("/staff/inventory_over")
    public void inventoryOver(@RequestParam(value="staffId") String staffId,@RequestParam(value="shelf") String shelf,@RequestParam(value="position") int position){
        staffService.inventoryOver(staffId,shelf,position);
    }

    //商品入库-查询空货位
    @RequestMapping("/staff/store_get")
    public String getAnEmptySpace(){
        return gson.toJson(staffService.getAnEmptySpace());
    }

    //商品入库-入库完成
    @RequestMapping("/staff/store_set")
    public void storeANewClothes(@RequestParam(value="id") String id,@RequestParam(value="shelf") String shelf,@RequestParam(value="position")int position,@RequestParam(value="amount") int amount){
        staffService.storeANewClothes(id,shelf,position,amount);
    }

    //订单退货-查询退货信息
    @RequestMapping("/staff/back_get")
    public String queryBackInfo(@RequestParam(value="orderId") String orderId,@RequestParam(value="clothesId") String clothesId){
        return gson.toJson(staffService.queryBackInfo(orderId,clothesId));
    }

    //订单退货-退货完成
    @RequestMapping("/staff/back_set")
    public void backOver(@RequestParam(value="orderId") String orderId,@RequestParam(value="clothesId") String clothesId){
        staffService.backOver(orderId,clothesId);
    }

    /*
    管理人员服务区
     */

    //登录
    @RequestMapping("/administrator/login")
    public boolean administratorLogin(@RequestParam(value="id") String id,@RequestParam(value="password") String password){
        return administratorService.administratorLogin(id,password);
    }

    //盘点管理-查询职工信息
    @RequestMapping("/administrator/query_staff")
    public String queryStaff(@RequestParam(value="id") String id){
        return gson.toJson(administratorService.queryStaff(id));
    }

    //盘点管理-分配盘点任务
    @RequestMapping("/administrator/inventory_assign")
    public void assignInventoryTask(@RequestParam(value="staffs") String staffsStr){
        Staff[] staffs=gson.fromJson(staffsStr,Staff[].class);
        administratorService.assignInventoryTask(staffs);
    }

    //盘点管理-查看已分配盘点任务的员工
    @RequestMapping("/administrator/inventory_staff")
    public String queryInventoryStaff(){
        return gson.toJson(administratorService.queryInventoryStaff());
    }

    //盘点管理-查看盘点任务摘要
    @RequestMapping("/administrator/inventory_summary")
    public String queryInventoryTaskSummary(@RequestParam(value="staffId") String staffId){
        return gson.toJson(administratorService.queryInventoryTaskSummary(staffId));
    }

    //盘点管理-查看预计盘点数量
    @RequestMapping("/administrator/inventory_amount")
    public int queryInventoryAmount(@RequestParam(value="staffId") String staffId){
        return administratorService.queryInventoryAmount(staffId);
    }

    //盘点管理-查看盘点进度
    @RequestMapping("/administrator/inventory_rate")
    public double queryInventoryRate(String staffId){
        return administratorService.queryInventoryRate(staffId);
    }

}