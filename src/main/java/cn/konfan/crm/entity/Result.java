package cn.konfan.crm.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MrLv
 * @date 2021/12/1
 * @apiNote
 */
public class Result extends HashMap {
    public static Result ok() {
        Result result = new Result();
        result.put("code",0);
        result.put("msg","操作成功");
        result.put("data",null);
        return result;
    }
    public static Result ok(Integer code) {
        Result result = new Result();
        result.put("code",code);
        result.put("msg","操作成功");
        return result;
    }
    public static Result ok(Integer code,String msg) {
        Result result = new Result();
        result.put("code",code);
        result.put("msg",msg);
        return result;
    }
    public static <T>Result ok(Integer code,String msg ,T data) {
        Result result = new Result();
        result.put("code",code);
        result.put("msg",msg);
        if (data !=null){
            result.put("data",data);
        }
        return result;
    }
    public static Result ok(Map resultMap) {
        Result result = new Result();
        result.putAll(resultMap);
        return result;
    }
}
