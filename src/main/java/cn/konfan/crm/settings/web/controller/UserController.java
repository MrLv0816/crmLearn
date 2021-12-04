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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String toLogin(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return "redirect:/workbench/toIndex.do";
        }
        return "login";
    }


    @RequestMapping(value = "/login.do")
    @ResponseBody
    public Result login(@RequestParam("loginAct") String loginAct, @RequestParam("loginPwd") String loginPwd, @RequestParam(defaultValue = "0") Integer autoLogin, HttpServletRequest request, HttpServletResponse response) {

        String md5Pwd = MD5Util.getMD5(loginPwd);
        String ip = request.getRemoteAddr();

        //User user = userService.findUserByLoginActAndLoginPwd(loginAct, md5Pwd);
        Map<String, Object> resultMap = userService.findMapByLoginActAndLoginPwd(loginAct, md5Pwd, ip);
        User user = (User) resultMap.get("data");

        if (user == null) {
            return Result.ok(resultMap);
        }

        request.getSession().setAttribute("user", user);
        System.out.println(autoLogin);
        if (autoLogin == 1) {
            Cookie act = new Cookie("act", loginAct);
            Cookie pwd = new Cookie("pwd", md5Pwd);
            act.setMaxAge(60 * 60 * 24 * 10);
            pwd.setMaxAge(60 * 60 * 24 * 10);
            act.setPath("/");
            pwd.setPath("/");
            response.addCookie(act);
            response.addCookie(pwd);
        }

        resultMap.put("data", null);
        return Result.ok(resultMap);

    }
}
