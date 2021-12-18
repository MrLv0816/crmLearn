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
        return dictionaryTypeDao.findAll();
    }

    @Override
    public List<DictionaryValue> findAllDictionaryValue() {
        return dictionaryVauleDao.findAll();
    }

    @Override
    public int findTypeCodeCount(String code) {
        return dictionaryTypeDao.findCodeCount(code);
    }

    @Override
    public int addDictionaryType(DictionaryType dictionary) {
        return dictionaryTypeDao.insert(dictionary);
    }

    @Override
    public DictionaryType findDictionaryTypeByCode(String code) {
        return dictionaryTypeDao.findByCode(code);
    }

    @Override
    public int updateDictionaryType(DictionaryType dictionary) {
        return dictionaryTypeDao.update(dictionary);
    }

    @Override
    public int deleteDictionaryTypes(String[] code) {
        return dictionaryTypeDao.deleteByCode(code);
    }
}
