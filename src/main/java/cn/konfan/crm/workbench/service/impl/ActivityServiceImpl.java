package cn.konfan.crm.workbench.service.impl;

import cn.konfan.crm.settings.domain.User;
import cn.konfan.crm.settings.service.UserService;
import cn.konfan.crm.utils.UUIDUtil;
import cn.konfan.crm.workbench.dao.ActivityDao;
import cn.konfan.crm.workbench.domain.Activity;
import cn.konfan.crm.workbench.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author MrLv
 * @date 2021/12/6
 * @apiNote
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private UserService userService;


    /**
     * 查询全部 市场活动 数据
     *
     * @param id 市场活动唯一id (可选)
     * @return 市场活动列表
     */
    @Override
    public List<Activity> findAll(String id) {
        return activityDao.findAll(id);
    }

    /**
     * 根据 id 查询市场活动
     *
     * @param id 市场活动唯一标识
     * @return 市场活动
     */
    @Override
    public Activity findById(String id) {
        return activityDao.findById(id);
    }


    /**
     * 增加市场活动数据
     *
     * @param activity 市场活动对象
     * @return 数据库受影响记录数
     */
    @Override
    public int addActivity(Activity activity) {
        //补全必要数据
        String uuid = UUIDUtil.getUUID();
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        String uname = userService.findNameById(activity.getOwner());
        activity.setId(uuid);
        activity.setCreateTime(date);
        activity.setEditTime(date);
        activity.setCreateBy(uname);
        activity.setEditBy(uname);
        activity.setIsDelete("0");

        return activityDao.insert(activity);
    }

    /**
     * 修改 市场活动数据
     *
     * @param activity 市场活动对象
     * @param editUser 修改的用户
     * @return 数据库受影响记录数
     */
    @Override
    public int updateActivity(Activity activity, User editUser) {
        //补全必要数据-修改人信息
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        activity.setEditTime(date);
        activity.setEditBy(editUser.getName());

        return activityDao.update(activity);
    }


    /**
     * 批量删除 市场活动数据
     *
     * @param codes 数据唯一标识数组
     * @return 数据库受影响记录数
     */
    @Override
    public int removes(String[] codes) {
        return activityDao.delete(codes);
    }

    @Override
    public List<Activity> search(String name, String owner, String startDate, String endDate) {



        return activityDao.search(name, owner, startDate, endDate);

    }


}
