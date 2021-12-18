package cn.konfan.crm.settings.service;

import cn.konfan.crm.entity.Result;
import cn.konfan.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author MrLv
 * @date 2021/12/1
 * @apiNote
 */
public interface UserService {
    @Deprecated
    User findUserByLoginActAndLoginPwd(String loginAct, String loginPwd);

    Result findMapByLoginActAndLoginPwd(String loginAct, String loginPwd, String ip);

    List<User> findAll();

    String findNameById(String id);

}
