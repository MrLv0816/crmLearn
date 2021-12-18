package cn.konfan.crm.settings.dao;

import cn.konfan.crm.settings.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MrLv
 * @date 2021/12/1
 * @apiNote
 */
public interface UserDao {

    /**
     * 根据 loginAct 与 loginPwd 查询
     * @param loginAct  用户名
     * @param loginPwd  用户密码
     * @return  用户对象
     */
    User findUserByLoginActAndLoginPwd(@Param("loginAct")String loginAct, @Param("loginPwd")String loginPwd);


    /**
     *根据 id 获取 用户名称(name)
     * @param id 用户唯一标识
     * @return  用户名称
     */
    String findNameById(String id);


    /**
     * 根据 id 查询
     * @param id 用户唯一标识
     * @return  用户对象
     */
    User findById (String id);

    /**
     * 查询全部用户
     * @return  用户对象 List
     */
    List<User> findAll();

    
}
