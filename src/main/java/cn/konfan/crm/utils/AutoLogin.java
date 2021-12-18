package cn.konfan.crm.utils;

import cn.konfan.crm.exception.InterceptorException;
import cn.konfan.crm.settings.domain.User;
import cn.konfan.crm.settings.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author MrLv
 * @date 2021/12/7
 * @apiNote
 */
public class AutoLogin {

    /**
     * 通过 Cookie 进行用户的自动登录
     * @param request   获取cookie
     * @return  是否登录成功
     */
    public static boolean login(HttpServletRequest request) {


        //获取用户 Cookie 并且不能为 null
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length < 1) {
            return false;
        }


        //遍历所有 Cookie 取出用户名与密码
        String act = null;
        String pwd = null;
        for (Cookie cookie : cookies) {
            if ("act".equals(cookie.getName())) {
                act = cookie.getValue();
            }
            if ("pwd".equals(cookie.getName())) {
                pwd = cookie.getValue();
            }
        }

        //任意一项为空则 登录失败
        if (act == null || pwd == null) {
            return false;
        }

        //登录操作
        ApplicationContext context = (ApplicationContext) request.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        UserService userService = (UserService) context.getBean("userServiceImpl");
        Map<String, Object> loginMap = userService.findMapByLoginActAndLoginPwd(act, pwd, request.getRemoteAddr());
        User autoUser = (User) loginMap.get("data");


        //数据库匹配失败
        if (autoUser == null) {
            return false;
        }


        request.getSession().setAttribute("user", autoUser);
        return true;
    }
}
