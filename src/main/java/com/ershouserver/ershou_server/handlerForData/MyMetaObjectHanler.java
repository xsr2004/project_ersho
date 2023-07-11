package com.ershouserver.ershou_server.handlerForData;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHanler implements MetaObjectHandler {
    /**
     * 插入时的填充策略
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
//        插入时若原价为空则插入9999.99
        if(metaObject.getValue("oldprice")==null){//新版本的函数好像改了，我找不到，只能手动get判断空了
           this.setFieldValByName("oldprice",new Double(9999.99),metaObject);
        }
        if(metaObject.getValue("price")==null){//新版本的函数好像改了，我找不到，只能手动get判断空了
            this.setFieldValByName("price",new Double(0.00),metaObject);
        }
        this.setFieldValByName("addtim",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
