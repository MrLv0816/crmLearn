package cn.konfan.crm.settings.web.controller.Dictionary;

import cn.konfan.crm.entity.Result;
import cn.konfan.crm.exception.TraditionRequestException;
import cn.konfan.crm.settings.domain.DictionaryType;
import cn.konfan.crm.settings.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class DictionaryTypeController {

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 数据字典表 主页面
     *
     * @return /index.jsp
     */
    @RequestMapping("/toIndex.do")
    public String toIndex() {
        return "settings/dictionary/index";
    }

//============================================================================================


    /**
     *数据字典表 字典类型 主页面请求
     *
     * @param model 获取数据放到作用域中
     * @return /type/index.jsp
     */
    @RequestMapping("/type/toIndex.do")
    public String toIndex(Model model) {

        //获取 DictionaryType
        List<DictionaryType> dictionaryTypes = dictionaryService.findAllDictionaryType();

        //将 DictionaryType 放置到 用户 session 中
        model.addAttribute("dictionaryTypeList", dictionaryTypes);


        return "settings/dictionary/type/index";
    }


    /**
     * 数据字典表 字典类型 编辑页面
     *
     * @param code  数据唯一标识
     * @param model 获取数据放到作用域中
     * @return /tupe/edit.jsp
     * @throws TraditionRequestException 查询数据异常
     */
    @RequestMapping("/type/toEdit.do")
    public String toEdit(String code, Model model) throws TraditionRequestException {

        //请求数据有误
        if (code == null) {
            throw new TraditionRequestException("提交数据有误!");
        }

        //查询 DictionaryType
        DictionaryType dictionaryType = dictionaryService.findDictionaryTypeByCode(code);
        if (dictionaryType == null) {
            throw new TraditionRequestException("数据查询异常...");
        }

        //将 DictionaryType 放置到用户 session 中
        model.addAttribute("dictionaryType", dictionaryType);
        return "settings/dictionary/type/edit";
    }

    /**
     *数据字典表 字典类型 修改页面
     *
     * @param code        唯一标识
     * @param name        修改的数据
     * @param description 修改的数据
     * @return 修改状态
     */
    @RequestMapping("/type/edit.do")
    @ResponseBody
    public Result Edit(String code, String name, String description) {

        //数据库操作 修改 DictionaryType
        int i = dictionaryService.updateDictionaryType(new DictionaryType(code, name, description));

        //返回状态
        if (i > 0) {
            return Result.ok(0, "编辑成功");
        } else {
            return Result.ok(1, "修改失败原因未知...");
        }
    }




    /**
     * 添加页面
     *
     * @return save.jsp
     */
    @RequestMapping("/type/toSave.do")
    public String toTypeSave() {
        return "settings/dictionary/type/save";
    }

    /**
     * 添加 DictionaryType
     *
     * @param code        DictionaryType Code
     * @param name        DictionaryType name
     * @param description DictionaryType description
     * @return 请求状态
     */
    @RequestMapping("/type/save.do")
    @ResponseBody
    public Result TypeSave(String code, String name, String description) {

        //数据库操作 通过code 查询是否存在
        int typeCodeCount = dictionaryService.findTypeCodeCount(code);
        if (typeCodeCount == 1) {
            return Result.ok(1, "该编码已存在");
        }


        //数据库操作 新增 DictionaryType
        int i = dictionaryService.addDictionaryType(new DictionaryType(code, name, description));
        if (i > 0) {
            return Result.ok(0, "添加成功");
        } else {
            return Result.ok(2, "添加失败,原因未知...");
        }

    }
    /**
     * 检查 code 是否存在
     * @param code  DictionaryType的唯一标识
     * @return  code是否存在
     */
    @RequestMapping("/type/checkCode.do")
    @ResponseBody
    public Result checkCode(String code) {
        int count = dictionaryService.findTypeCodeCount(code);
        if (count == 0) {
            return Result.ok(0, "编码不存在");
        } else {
            return Result.ok(1, "编码已存在");
        }
    }
    /**
     * 删除请求
     * @param codes 通过 DictionaryType 的唯一标识 进行批量删除
     * @return  状态
     */
    @RequestMapping("/type/delete.do")
    @ResponseBody
    public Result TypeDelete(String[] codes) {
        int i = dictionaryService.deleteDictionaryTypes(codes);
        if (i > 0) {
            return Result.ok(0, "删除成功");
        } else {
            return Result.ok(1, "删除失败");
        }
    }

}
