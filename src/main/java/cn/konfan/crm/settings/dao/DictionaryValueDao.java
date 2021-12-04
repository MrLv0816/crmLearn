package cn.konfan.crm.settings.dao;

import cn.konfan.crm.settings.domain.DictionaryValue;

import java.util.List;

/**
 * @author MrLv
 * @date 2021/12/3
 * @apiNote
 */
public interface DictionaryValueDao {
    public List<DictionaryValue> findAllDictionaryVaule();
}
