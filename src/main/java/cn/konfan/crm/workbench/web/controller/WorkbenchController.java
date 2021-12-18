package cn.konfan.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MrLv
 * @date 2021/12/3
 * @apiNote
 */
@Controller
@RequestMapping("/workbench")
public class WorkbenchController {



    @RequestMapping("/toIndex.do")
    public String toIndex() {
        return "workbench/index";
    }
    @RequestMapping("/main/toIndex.do")
    public String toMainIndex() {
        return "workbench/main/index";
    }


    @RequestMapping("/activity/toIndex.do")
    public String toActivityIndex() {
        return "workbench/activity/index";
    }



}