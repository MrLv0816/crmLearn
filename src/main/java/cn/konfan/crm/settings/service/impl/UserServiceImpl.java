package cn.konfan.crm.settings.service.impl;

import cn.konfan.crm.settings.dao.UserDao;
import cn.konfan.crm.settings.domain.User;
import cn.konfan.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

    @Override
    public User findUserByLoginActAndLoginPwd(String loginAct, String loginPwd) {
        User user = userDao.findUserByLoginActAndLoginPwd(loginAct, loginPwd);

        return user;
    }

    @Override
    public Map<String, Object> findMapByLoginActAndLoginPwd(String loginAct, String loginPwd, String ip) {

        Map<String, Object> resultMap = new HashMap<>();
        User user = userDao.findUserByLoginActAndLoginPwd(loginAct, loginPwd);

        //验证用户名密码
        if (user == null) {
            resultMap.put("code", 1);
            resultMap.put("msg", "用户名或密码错误");
            resultMap.put("data", null);
            return resultMap;
        }


        //验证账户过期时间
        //如果为空 代表永不过期
        String expireTime = user.getExpireTime();
        if (expireTime != null) {
            String now = new SimpleDateFormat("yyy-MM-dd hh:mm:ss").format(new Date());
            if (expireTime.compareTo(now) < 0) {
                //小于 0 代表账户过期
                resultMap.put("code", 2);
                resultMap.put("msg", "账户已过期,请联系管理员");
                resultMap.put("data", null);
                return resultMap;
            }
        }

        //验证账户是否为 锁定 状态
        if (user.getLockState().equals("1")) {
            resultMap.put("code", 3);
            resultMap.put("msg", "账户已锁定,请联系管理员");
            resultMap.put("data", null);
            return resultMap;
        }

        //验证ip是否受限
        String allowlps = user.getAllowIps();
        if (allowlps != null) {
            if (!allowlps.contains(ip)) {
                resultMap.put("code", 4);
                resultMap.put("msg", "Ip地址受限,请联系管理员");
                resultMap.put("data", null);
                return resultMap;
            }
        }

        resultMap.put("code", 0);
        resultMap.put("msg", "登陆成功");
        resultMap.put("data", user);
        return resultMap;
    }
}
