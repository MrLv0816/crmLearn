package cn.konfan.crm.settings.service.impl;

import cn.konfan.crm.settings.dao.DictionaryTypeDao;
import cn.konfan.crm.settings.dao.DictionaryValueDao;
import cn.konfan.crm.settings.domain.DictionaryType;
import cn.konfan.crm.settings.domain.DictionaryValue;
import cn.konfan.crm.settings.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MrLv
 * @date 2021/12/3
 * @apiNote
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryTypeDao dictionaryTypeDao;
    @Autowired
    private DictionaryValueDao dictionaryVauleDao;

    @Override
    public List<DictionaryType> findAllDictionaryType() {
        return dictionaryTypeDao.findAllDictionaryType();
    }

    @Override
    public List<DictionaryValue> findAllDictionaryValue() {
        return dictionaryVauleDao.findAllDictionaryVaule();
    }
}
