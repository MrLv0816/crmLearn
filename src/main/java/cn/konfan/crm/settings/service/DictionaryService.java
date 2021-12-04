package cn.konfan.crm.settings.service;

import cn.konfan.crm.settings.domain.DictionaryType;
import cn.konfan.crm.settings.domain.DictionaryValue;

import java.util.List;

/**
 * @author MrLv
 * @date 2021/12/3
 * @apiNote
 */
public interface DictionaryService {
    public List<DictionaryType> findAllDictionaryType();

    public List<DictionaryValue> findAllDictionaryValue();
}
