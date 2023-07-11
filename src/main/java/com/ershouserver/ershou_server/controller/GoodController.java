package com.ershouserver.ershou_server.controller;

import com.ershouserver.ershou_server.common.Result;
import com.ershouserver.ershou_server.entity.Good;
import com.ershouserver.ershou_server.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;

/**
 * 请求“商品页面”
 */
@RestController
@RequestMapping("/SearchMall")
public class GoodController {

    @Autowired
    private GoodService goodService;

    /** getSomeData
    *   返回首页数据推荐
    *
    * @return com.ershouserver.ershou_server.common.Result
    * @throws Exception
    * @author
    * @date 2023/4/20
    */

    @GetMapping
    public Result getSomeData(){
        List<Good> goods = goodService.getRandomGoods();
        return Result.success(goods);
    }

    @GetMapping("/{value}")
    public Result HandlerSearch(@PathVariable("value") String SearchValue,
                                @RequestParam Integer timeValue,
                                @RequestParam Integer zongheValue,
                                @RequestParam Boolean switch1,
                                @RequestParam String radio){
        List<Good> goods = goodService.QuerySearch(SearchValue,timeValue,
                zongheValue,
                switch1,radio);
        return Result.success(goods);
    }
    @GetMapping("/DetailCard")
    public Result HandlerDetailCardById(@RequestParam String id){
        Long aLong = new Long(id);
        System.out.println(aLong);
        Good good = goodService.QueryByID(aLong);
        return Result.success(good);
    }
}
