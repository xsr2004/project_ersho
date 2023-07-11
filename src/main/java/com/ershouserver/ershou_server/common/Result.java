package com.ershouserver.ershou_server.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ershouserver.ershou_server.common.Constants.CODE_200;
import static com.ershouserver.ershou_server.common.Constants.CODE_500;


/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String code;
    private String msg;
    private Object data;
//    封装success，error有参无参
    public static Result success(){
        return new Result(CODE_200,"成功",null);
    }
    public static Result success(Object data){
        return new Result(CODE_200,"成功",data);
    }
    public static Result error(String code,String msg){
        return new Result(code,msg,null);
    }
    public static Result error(){
        return new Result(CODE_500,"服务器错误(默认)",null);
    }
    public static Result error(String msg){
        return new Result(CODE_500,msg,null);
    }

}

