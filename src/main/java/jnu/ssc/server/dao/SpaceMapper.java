package jnu.ssc.server.dao;

import jnu.ssc.server.domain.Space;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SpaceMapper {

    @Select("select space.shelf,space.position from space where not exists (select * from  clothes where clothes.shelf=space.shelf and clothes.position=space.position) limit 1")
    Space getAnEmptySpace();

}