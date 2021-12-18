package cn.konfan.crm.settings.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

/**
 * @author MrLv
 * @date 2021/12/1
 * @apiNote
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@JsonIgnoreProperties(value = {"loginAct","loginPwd","expireTime","lockState","deptno","allowIps","createTime","createBy","editTime","editBy"})
public class User implements Serializable {
    private String id;
    private String loginAct;
    private String name;
    private String loginPwd;
    private String email;
    private String expireTime;
    private String lockState;
    private String deptno;
    private String allowIps;
    private String createTime;
    private String createBy;
    private String editTime;
    private String editBy;
}
