package cn.konfan.crm.settings.dao;

import cn.konfan.crm.settings.domain.DictionaryType;

import java.util.List;

/**
 * @author MrLv
 * @date 2021/12/3
 * @apiNote
 */
public interface DictionaryTypeDao {

    /**
     * 查询全部
     * @return  DictionaryTypeList
     */
    public List<DictionaryType> findAll();


    /**
     * 查询 DictionaryType 中 code 记录数
     * @param code  被查询的
     * @return  数量
     */
    public int findCodeCount(String code);

    /**
     * 新增
     * @param dictionary  对象
     * @return  数据库受影响记录数
     */
    public int insert(DictionaryType dictionary);


    /**
     * 根据 code 获取 DictionaryType
     * @param code 根据 code 查询
     * @return  DictionaryType
     */
    public DictionaryType findByCode(String code);

    /**
     * 修改
     * @param dictionary    修改的 dictionary
     * @return  数据库受影响的记录数
     */
    public int update (DictionaryType dictionary);


    /**
     * 批量删除
     * @param codes 数据库中唯一标识
     * @return  数据库受影响记录数
     */
    public int deleteByCode(String[] codes);

}
