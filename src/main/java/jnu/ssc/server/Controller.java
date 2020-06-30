package jnu.ssc.server;

import com.google.gson.Gson;
import jnu.ssc.server.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Controller {
    private Gson gson=new Gson();

    private StaffService staffService;
    @Autowired
    public void setStaffService(StaffService staffService){
        this.staffService=staffService;
    }

    @RequestMapping("/hello")
    public String hello(){
        return "Welcome to warehouse management system.";
    }

    @RequestMapping("/staff/query")
    public String queryClothesInfo(@RequestParam(value="id") String id){
        return gson.toJson(staffService.queryClothesInfo(id));
    }

    @RequestMapping("/staff/pick_task")
    public String assignPickTask(){
        return gson.toJson(staffService.assignPickTask());
    }

    @RequestMapping("/staff/pick_over")
    public void pickOver(@RequestParam(value="orderId") String orderId,@RequestParam(value="clothesId") String clothesId){
        staffService.pickOver(orderId,clothesId);
    }

    @RequestMapping("/staff/inventory_get")
    public String assignInventoryTask(){
        return gson.toJson(staffService.assignInventoryTask());
    }

    @RequestMapping("/staff/inventory_set")
    public void InventoryResultUpdate(@RequestParam(value="id") String id,@RequestParam(value="amount") int amount){
        staffService.inventoryResultUpdate(id,amount);
    }

}