package cn.konfan.crm.settings.web.controller;

import cn.konfan.crm.entity.Result;
import cn.konfan.crm.settings.domain.User;
import cn.konfan.crm.settings.service.UserService;
import cn.konfan.crm.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author MrLv
 * @date 2021/12/1
 * @apiNote
 */
@Controller
@RequestMapping(value = "/settings/user")
public class UserController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/toLogin.do")
    public String toLogin(){
        return "login";
    }


    @RequestMapping(value = "/login.do")
    @ResponseBody
    public Result login(@RequestParam("loginAct") String loginAct, @RequestParam("loginPwd") String loginPwd, HttpServletRequest request) {

        String md5Pwd = MD5Util.getMD5(loginPwd);
        String ip = request.getRemoteAddr();

        //User user = userService.findUserByLoginActAndLoginPwd(loginAct, md5Pwd);
        Map<String, Object> resultMap = userService.findMapByLoginActAndLoginPwd(loginAct, md5Pwd, ip);
        User user = (User) resultMap.get("data");

        if (user == null) {
            return Result.ok(resultMap);
        }

        request.getSession().setAttribute("user", user);


        resultMap.put("data",null);
        return Result.ok(resultMap);

    }
}
