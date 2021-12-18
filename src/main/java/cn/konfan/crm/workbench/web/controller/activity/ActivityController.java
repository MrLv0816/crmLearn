package cn.konfan.crm.workbench.web.controller.activity;

import cn.konfan.crm.entity.PageResult;
import cn.konfan.crm.entity.Result;
import cn.konfan.crm.exception.AjaxRequestException;
import cn.konfan.crm.settings.domain.User;
import cn.konfan.crm.workbench.domain.Activity;
import cn.konfan.crm.workbench.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MrLv
 * @date 2021/12/6
 * @apiNote
 */
@Service
@RequestMapping("/workbench/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;


    /**
     * 通用查询接口
     *
     * @param id    根据id精准查询
     * @param page  分页
     * @param limit 记录数
     * @return 请求状态 (data封装 市场活动数据列表)
     */
    @RequestMapping("/data.do")
    @ResponseBody
    public Map<String, Object> getAll(String id, Integer page, Integer limit) {

        //分页查询
        PageHelper.startPage(
                Math.max(1, page == null ? 1 : page),
                Math.min(limit == null ? 15 : limit, 30));

        List<Activity> activityList = activityService.findAll(id);
        PageInfo<Activity> pageInfo = new PageInfo<>(activityList);


        return PageResult.ok(0, "", pageInfo);
    }


    /**
     * 市场活动 详细信息页面
     *
     * @param id    市场活动数据唯一标识
     * @param model 用于写入session
     * @return 详细信息页面及数据
     * @throws AjaxRequestException 查询数据库异常
     */
    @RequestMapping("/toDetail.do")
    public String toDetail(String id, Model model) throws AjaxRequestException {

        Activity activity = activityService.findById(id);
        if (activity == null) {
            throw new AjaxRequestException("获取数据异常...");
        }
        model.addAttribute("activity", activity);

        return "workbench/activity/detail";
    }

    /**
     * 新增 市场活动数据 请求
     *
     * @param activity 市场活动对象
     * @return 请求状态
     */
    @RequestMapping("/create.do")
    @ResponseBody
    public Result createActivity(Activity activity) {

        int i = activityService.addActivity(activity);
        if (i > 0) {
            return Result.ok(0);
        }
        return Result.ok(1, "添加失败");
    }


    /**
     * 修改 市场活动数据 请求
     *
     * @param activity 市场活动对象
     * @param session  用于取出用户数据
     * @return 请求状态
     */
    @RequestMapping("/update.do")
    @ResponseBody
    public Result updateActivity(Activity activity, HttpSession session) {
        Object editUser = session.getAttribute("user");
        int i = activityService.updateActivity(activity, (User) editUser);
        if (i == 1) {
            return Result.ok(0);
        }
        return Result.ok(1, "修改失败");
    }


    /**
     * 批量删除 市场活动数据 请求
     *
     * @param codes 市场活动数据唯一标识
     * @return 请求状态
     */
    @RequestMapping("/delete.do")
    @ResponseBody
    public Result deleteActivity(String[] codes) {
        int i = activityService.removes(codes);

        if (i != codes.length) {
            return Result.ok(1, "删除失败");
        }


        return Result.ok(0);
    }


    @RequestMapping("/search.do")
    @ResponseBody
    public PageResult search(Integer page, Integer limit, String name, String owner, String startDate, String endDate) {
        PageHelper.startPage(Math.max(page == null ? 1 : page, 1), Math.min(limit == null ? 15 : limit, 60));
        List<Activity> list = activityService.search(name, owner, startDate, endDate);
        if (list == null){
            return PageResult.err(1,"查询数据库异常...");
        }
        PageInfo<Activity> pageInfo = new PageInfo<>(list);
        return PageResult.ok(0,"",pageInfo);
    }

}


