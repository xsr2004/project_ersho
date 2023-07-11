package com.ershouserver.ershou_server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ershouserver.ershou_server.common.Result;
import com.ershouserver.ershou_server.entity.Good;
import com.ershouserver.ershou_server.entity.User;
import com.ershouserver.ershou_server.mapper.UserMapper;
import com.ershouserver.ershou_server.service.GoodService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class ErshouServerApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodService GoodService;

    @Test
    void contextLoads() {

    }
    @Test
    void test2(){
        Good good = new Good();
        good.setName("二手货");
        good.setOwner(123);
        good.setDescription("这是一段描述~~~");
        good.setOldprice(new Double(999));
        good.setPrice(new Double(123));
//        good.setAddtime(new Date());
        Result result = GoodService.SaveForm(good);
        System.out.println(result);
    }
    @Test
    void test3(){
        User user = new User();
        user.setLogin("dhd");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }
    @Test
    void test4(){
        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        Page<Good> page = new Page<>(1, 5);
        Page<Good> goodPage = GoodService.getBaseMapper().selectPage(page, wrapper);
        System.out.println("page == result: " + (page == goodPage));
        System.out.println("size: " + goodPage.getSize());
        System.out.println("total: " + goodPage.getTotal());
        for(Good good : goodPage.getRecords()) {
            System.out.println(good);
        }
    }
    @Test
    void test5(){
        while(true){
            List<Good> goods = GoodService.getRandomGoods();
            if(goods.size()==0){
                break;
            }
            for(Good good:goods){
                System.out.println(good);
            }

        }

    }
    @Test
    void test6(){
        List<Good> goods = GoodService.getRandomGoods();
        for(Good good:goods){
            System.out.println(good);
        }
    }
    @Test
    void test7(){
        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("media")
                .like("name","二手");
        List<Good> goods = GoodService.getBaseMapper().selectList(wrapper);
        for(Good good : goods){
            System.out.println(good);
        }

    }
    @Test
    void test8(){
        Long aLong = new Long("1648291051096027138");

        Good good = GoodService.QueryByID(aLong);
        System.out.println(good);
    }
    @Test
    void test9(){
        Good good = GoodService.QueryByID(new Long("1657391553477988353"));
        System.out.println(good);

    }



}
