package cn.konfan.crm.workbench.dao;

import cn.konfan.crm.workbench.domain.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MrLv
 * @date 2021/12/6
 * @apiNote
 */
public interface ActivityDao {

    /**
     * 通用查询
     * @param id    市场活动唯一标识(可选)
     * @return  市场活动对象列表
     */
    public List<Activity> findAll(@Param("id") String id);


    /**
     *根据 id 查询 市场活动
     * @param id    市场活动唯一标识
     * @return  市场活动对象
     */
    public Activity findById(String id);

    /**
     * 增加
     * @param activity  市场活动对象
     * @return  数据库受影响记录数
     */
    public int insert(Activity activity);

    /**
     * 修改
     * @param activity  市场活动对象
     * @return  数据库受影响记录数
     */
    int update(Activity activity);

    /**
     * 批量删除
     * @param codes 市场活动唯一标识
     * @return  数据库受影响记录数
     */
    int delete(String[] codes);


    /**
     * 模糊查询
     * @param name 名称
     * @param owner 所属人
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    List<Activity> search(@Param("name") String name, @Param("owner")String owner,@Param("startDate") String startDate, @Param("endDate")String endDate);

}
