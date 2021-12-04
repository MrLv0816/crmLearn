package cn.konfan.crm.settings.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MrLv
 * @date 2021/12/3
 * @apiNote
 */
@Controller
@RequestMapping("/settings")
public class SettingsIndexController {

    @RequestMapping("/toIndex.do")
    public String toIndex() {
        return "settings/index";
    }
}
