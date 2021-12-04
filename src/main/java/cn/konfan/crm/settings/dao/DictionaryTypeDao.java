package cn.konfan.crm.settings.dao;

import cn.konfan.crm.settings.domain.DictionaryType;

import java.util.List;

/**
 * @author MrLv
 * @date 2021/12/3
 * @apiNote
 */
public interface DictionaryTypeDao {
    public List<DictionaryType> findAllDictionaryType();
}
