package jnu.ssc.server.dao;

import jnu.ssc.server.domain.Clothes;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ClothesMapper {

    @Select("select * from clothes where id=#{id}")
    Clothes getClothesById(@Param("id") String id);

    @Select("select * from clothes")
    Clothes[] getAllClothes();

    @Update("update clothes set amount=#{amount} where id=#{id}")
    void updateAmountById(@Param("id") String id,@Param("amount") int amount);

    @Insert("insert into clothes values(#{id},#{shelf},#{position},#{amount})")
    void insertClothes(@Param("id") String id,@Param("shelf") String shelf,@Param("position") int position,@Param("amount") int amount);

    @Select("select sum(amount) from clothes")
    int getAmountSum();

}