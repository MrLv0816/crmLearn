package cn.konfan.crm.entity;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;

/**
 * @author MrLv
 * @date 2021/12/9
 * @apiNote
 */
public class PageResult extends HashMap<String,Object> {
    public static PageResult ok(Integer code, String msg, PageInfo pageInfo){
        PageResult pageResult = new PageResult();
        pageResult.put("code",code);
        pageResult.put("msg",msg);
        pageResult.put("data",pageInfo.getList());
        pageResult.put("pageSize", pageInfo.getPageSize());
        pageResult.put("pageNum", pageInfo.getPageNum());
        pageResult.put("totalCounts", pageInfo.getTotal());
        pageResult.put("totalPages", pageInfo.getPages());
        return pageResult;
    }
    public static PageResult err(Integer code, String msg){
        PageResult pageResult = new PageResult();
        pageResult.put("code",code);
        pageResult.put("msg",msg);
        return pageResult;
    }
}
