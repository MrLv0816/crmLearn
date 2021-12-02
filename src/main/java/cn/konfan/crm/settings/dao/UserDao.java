package cn.konfan.crm.settings.dao;

import cn.konfan.crm.settings.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author MrLv
 * @date 2021/12/1
 * @apiNote
 */
public interface UserDao {
    User findUserByLoginActAndLoginPwd(@Param("loginAct")String loginAct, @Param("loginPwd")String loginPwd);
}
