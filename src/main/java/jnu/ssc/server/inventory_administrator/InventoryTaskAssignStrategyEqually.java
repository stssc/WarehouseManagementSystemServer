package jnu.ssc.server.inventory_administrator;

import jnu.ssc.server.dao.ClothesMapper;
import jnu.ssc.server.dao.InventoryTaskMapper;
import jnu.ssc.server.domain.Clothes;
import jnu.ssc.server.domain.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class InventoryTaskAssignStrategyEqually implements InventoryTaskAssignStrategy {//尽可能平均分配盘点任务，实现负载均衡，并且盘点的货位连续，避免工作人员来回跑，麻烦

    private ClothesMapper clothesMapper;
    @Autowired
    public void setClothesMapper(ClothesMapper clothesMapper){
        this.clothesMapper=clothesMapper;
    }

    private InventoryTaskMapper inventoryTaskMapper;
    @Autowired
    public void setInventoryTaskMapper(InventoryTaskMapper inventoryTaskMapper){
        this.inventoryTaskMapper=inventoryTaskMapper;
    }

    @Override
    public void assignInventoryTask(Staff[] staffs) {
        int amountSum=clothesMapper.getAmountSum();
        int amountAve=amountSum/staffs.length;//每个人盘点的总数量尽量接近这个数，对于不能刚好分配满的，采取四舍五入策略
        Clothes[] allClothes=clothesMapper.getAllClothesOrderByPosition();//按序select，确保任务按序分配
        int i=0,j=0;
        while (j<staffs.length){
            int amountAssigned=0;
            while (i<allClothes.length){
                if (amountAssigned>0&&amountAssigned+allClothes[i].getAmount()>amountAve&&amountAve-amountAssigned<allClothes[i].getAmount()/2){//不再加任务的条件：加上之后超过人均任务量；但如果这个人还没分到任务（amountAssigned==0）或者加上之后虽然超过人均任务量，但加上这个任务的大部分（一半以上，四舍五入）是不会超出的，那就还是加上去吧，毕竟不可能绝对均分，只能尽量均分
                    j++;
                    break;
                }
                else{
                    inventoryTaskMapper.insertInventoryTask(staffs[j].getId(),allClothes[i].getShelf(),allClothes[i].getPosition());//分配任务（直接分到数据库，查从数据库查，连暂存都不用了）
                    amountAssigned+=allClothes[i].getAmount();
                    i++;
                }
            }
        }
    }
}