package com.ershouserver.ershou_server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ershouserver.ershou_server.entity.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodMapper extends BaseMapper<Good> {
    /**
     * 首页刷新获取一部分random数据，默认写死8
     * @return List<Good>
     */
    @Select("select * from good\n" +
            "where id>=(SELECT floor(RAND()*(SELECT MAX(id) from good)))\n" +
            "order by id\n" +
            "LIMIT 5")
    List<Good> selectSomeGoods();
}
