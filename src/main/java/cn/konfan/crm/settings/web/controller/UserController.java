package cn.konfan.crm.settings.web.controller;

import cn.konfan.crm.entity.Result;
import cn.konfan.crm.exception.AjaxRequestException;
import cn.konfan.crm.settings.domain.User;
import cn.konfan.crm.settings.service.UserService;
import cn.konfan.crm.utils.AutoLogin;
import cn.konfan.crm.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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

    /**
     * 用户登录页面请求
     *
     * @param request 用于验证登录
     * @return login.jsp
     */
    @RequestMapping(value = "/toLogin.do")
    public String toLogin(HttpServletRequest request) {

        //根据 Cookie 进行自动登录
        if (request.getSession().getAttribute("user") == null) {
            AutoLogin.login(request);
        }
        //用户已经登陆,将用户重定向至主页面
        if (request.getSession().getAttribute("user") != null) {
            return "redirect:/workbench/toIndex.do";
        }
        return "login";
    }

    /**
     * 用户登录验证请求
     *
     * @param loginAct  用户名
     * @param loginPwd  用户密码
     * @param autoLogin 是否开启十天免登陆
     * @param request   设置 session
     * @param response  设置 Cookie
     * @return 请求状态
     */
    @RequestMapping(value = "/login.do")
    @ResponseBody
    public Result login(@RequestParam("loginAct") String loginAct, @RequestParam("loginPwd") String loginPwd, @RequestParam(defaultValue = "0") Integer autoLogin, HttpServletRequest request, HttpServletResponse response) {

        //明文转md5
        String md5Pwd = MD5Util.getMD5(loginPwd);
        String ip = request.getRemoteAddr();


        //进行登陆验证
        Result result = userService.findMapByLoginActAndLoginPwd(loginAct, md5Pwd, ip);
        User user = (User) result.get("data");


        //验证失败,直接返回状态
        if (user == null) {
            return result;
        }


        //将用户对象 写入session
        request.getSession().setAttribute("user", user);
        //十天免登陆 设置 Cookie
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


        return result;
    }


    /**
     * 查询全部用户 (抹除重要数据)
     *
     * @return 请求状态 (封装用户数据)
     * @throws AjaxRequestException 查询失败
     */
    @RequestMapping("/data.do")
    @ResponseBody
    public Result getUserData() throws AjaxRequestException {
        List<User> userList = userService.findAll();
        if (userList == null) {
            throw new AjaxRequestException("查询数据库异常...");
        }
        return Result.ok(0, "", userList);
    }
}
