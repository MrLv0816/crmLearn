package cn.konfan.crm.workbench.service;

import cn.konfan.crm.settings.domain.User;
import cn.konfan.crm.workbench.domain.Activity;

import java.util.List;

/**
 * @author MrLv
 * @date 2021/12/6
 * @apiNote
 */
public interface ActivityService {
    public List<Activity> findAll(String id);

    Activity findById(String id);

    int addActivity(Activity activity);

    int updateActivity(Activity activity, User editUser);

    int removes(String[] codes);

    List<Activity> search(String name, String owner, String startDate, String endDate);
}
