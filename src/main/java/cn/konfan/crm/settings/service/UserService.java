package cn.konfan.crm.settings.service;

import cn.konfan.crm.settings.domain.User;

import java.util.Map;

/**
 * @author MrLv
 * @date 2021/12/1
 * @apiNote
 */
public interface UserService {
    @Deprecated
    User findUserByLoginActAndLoginPwd(String loginAct, String loginPwd);

    Map<String, Object> findMapByLoginActAndLoginPwd(String loginAct, String loginPwd,String ip);

}
