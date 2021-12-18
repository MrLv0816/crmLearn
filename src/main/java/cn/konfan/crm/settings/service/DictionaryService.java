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

    public List<DictionaryValue> findAllDictionaryValue();


    //=Type======================================================================


    public int findTypeCodeCount(String code);

    public int addDictionaryType(DictionaryType dictionary);

    public int updateDictionaryType(DictionaryType dictionary);

    public int deleteDictionaryTypes(String[] code);

    public DictionaryType findDictionaryTypeByCode(String code);

    public List<DictionaryType> findAllDictionaryType();
}
