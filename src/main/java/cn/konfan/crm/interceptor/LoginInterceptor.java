package cn.konfan.crm.interceptor;

import cn.konfan.crm.exception.InterceptorException;
import cn.konfan.crm.exception.LoginException;
import cn.konfan.crm.settings.domain.User;
import cn.konfan.crm.settings.service.UserService;
import cn.konfan.crm.utils.AutoLogin;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author MrLv
 * @date 2021/11/29
 * @apiNote
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 控制器执行前的拦截操作
     *
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            if (AutoLogin.login(request)) {
                return true;
            }
            throw new InterceptorException();
        }

        return true;
    }

    /**
     * 控制器执行后的操作
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 页面加载完成前的操作
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
