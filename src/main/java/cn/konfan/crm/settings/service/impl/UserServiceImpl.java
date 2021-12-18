package cn.konfan.crm.settings.service.impl;

import cn.konfan.crm.entity.Result;
import cn.konfan.crm.settings.dao.UserDao;
import cn.konfan.crm.settings.domain.User;
import cn.konfan.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MrLv
 * @date 2021/12/1
 * @apiNote
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    /**
     * 用户登录操作
     * @param loginAct  用户名
     * @param loginPwd  密码
     * @return  用户对象
     */
    @Override
    @Deprecated
    public User findUserByLoginActAndLoginPwd(String loginAct, String loginPwd) {
        return userDao.findUserByLoginActAndLoginPwd(loginAct, loginPwd);
    }

    /**
     * 用户登录
     * @param loginAct  用户名
     * @param loginPwd  名密码
     * @param ip    访问ip
     * @return
     */
    @Override
    public Result findMapByLoginActAndLoginPwd(String loginAct, String loginPwd, String ip) {


        User user = userDao.findUserByLoginActAndLoginPwd(loginAct, loginPwd);

        //验证用户名密码
        if (user == null) {
            return Result.ok(1,"用户名或密码错误");
        }


        //验证账户过期时间
        //如果为空 代表永不过期
        String expireTime = user.getExpireTime();
        if (expireTime != null) {
            String now = new SimpleDateFormat("yyy-MM-dd hh:mm:ss").format(new Date());
            if (expireTime.compareTo(now) < 0) {
                //小于 0 代表账户过期
                return Result.ok(2,"账户已过期");
            }
        }

        //验证账户是否为 锁定 状态
        if (user.getLockState().equals("1")) {
            return Result.ok(3,"账户已锁定");
        }

        //验证ip是否受限
        String allowlps = user.getAllowIps();
        if (allowlps != null) {
            if (!allowlps.contains(ip)) {
                return Result.ok(4,"Ip地址受限");
            }
        }

        return Result.ok(0,"",user);
    }


    /**
     * 查询全部用户数据
     * @return  用户对象 <List>
     */
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }


    /**
     * 根据 id 查询用户名
     * @param id    用户唯一标识
     * @return  用户昵称
     */
    @Override
    public String findNameById(String id) {
        return userDao.findNameById(id);
    }
}
