package cn.konfan.crm.settings.web.controller.Dictionary;

import cn.konfan.crm.entity.Result;
import cn.konfan.crm.settings.domain.DictionaryValue;
import cn.konfan.crm.settings.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author MrLv
 * @date 2021/12/6
 * @apiNote
 */
@Controller
@RequestMapping("/settings/dictionary")
public class DictionaryValueController {


    @Autowired
    private DictionaryService dictionaryService;


    /**
     * 数据字典表 字典值 主页面
     *
     * @return /value/index.jsp
     */
    @RequestMapping("/value/toIndex.do")
    public String toValueIndex() {
        return "settings/dictionary/value/index";
    }

    /**
     * 数据字典表 字典值 编辑页面
     *
     * @param code 字典名称
     * @return /value/edit.jsp
     */
    @RequestMapping("/value/toEdit.do")
    public String toValueEdit(String code) {
        return "settings/dictionary/value/edit";
    }

    /**
     * 数据字典表 字典值 新增页面
     *
     * @return /value/save.jsp
     */
    @RequestMapping("/value/toSave.do")
    public String toValueSave() {
        return "settings/dictionary/value/save";
    }


    /**
     * 获取全部 DictionaryValue
     *
     * @return  请求状态 (data 封装 DictionaryValue)
     */
    @RequestMapping("/value/data.do")
    @ResponseBody
    public Result getData() {
        List<DictionaryValue> list = dictionaryService.findAllDictionaryValue();
        return Result.ok(0, "", list);
    }


}
