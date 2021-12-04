package cn.konfan.crm.settings.web.controller;

import cn.konfan.crm.settings.domain.DictionaryType;
import cn.konfan.crm.settings.domain.DictionaryValue;
import cn.konfan.crm.settings.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author MrLv
 * @date 2021/12/3
 * @apiNote
 */
@Controller
@RequestMapping("/settings/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping("/toIndex.do")
    public String toIndex() {
        return "settings/dictionary/index";
    }




    @RequestMapping("/type/toIndex.do")
    public String toTypeIndex(Model model){
        List<DictionaryType> dictionaryTypes = dictionaryService.findAllDictionaryType();
        model.addAttribute("dictionaryTypeList",dictionaryTypes);
        return "settings/dictionary/type/index";
    }

    @RequestMapping("/type/toEdit.do")
    public String toTypeEdit(){
        return "settings/dictionary/type/edit";
    }

    @RequestMapping("/type/toSave.do")
    public String toTypeSave(){
        return "settings/dictionary/type/save";
    }





    @RequestMapping("/value/toIndex.do")
    public String toValueIndex(Model model){
        List<DictionaryValue> dictionaryValues = dictionaryService.findAllDictionaryValue();
        model.addAttribute("dictionaryValueList",dictionaryValues);
        return "settings/dictionary/value/index";
    }

    @RequestMapping("/value/toEdit.do")
    public String toValueEdit(){
        return "settings/dictionary/type/edit";
    }

    @RequestMapping("/value/toSave.do")
    public String toValueSave(){
        return "settings/dictionary/value/save";
    }

}
