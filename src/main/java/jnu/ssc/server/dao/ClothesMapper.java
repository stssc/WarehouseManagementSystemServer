package jnu.ssc.server.dao;

import jnu.ssc.server.domain.Clothes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ClothesMapper {

    @Select("select * from clothes where id=#{id}")
    Clothes getClothesById(@Param("id") String id);

    @Select("select * from clothes")
    Clothes[] getAllClothes();

    @Update("update clothes set amount=#{amount} where id=#{id}")
    void updateAmountById(@Param("id") String id,@Param("amount") int amount);

}