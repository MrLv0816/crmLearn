package cn.konfan.crm.workbench.domain;

import cn.konfan.crm.settings.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author MrLv
 * @date 2021/12/6
 * @apiNote
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Activity {
    private String id;
    private User user;
    private String owner;
    private String name;
    private String startDate;
    private String endDate;
    private String cost;
    private String description;
    private String createTime;
    private String createBy;
    private String editTime;
    private String editBy;
    private String isDelete;
}
