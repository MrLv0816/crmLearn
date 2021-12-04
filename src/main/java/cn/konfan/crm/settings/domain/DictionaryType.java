package cn.konfan.crm.settings.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author MrLv
 * @date 2021/12/3
 * @apiNote
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DictionaryType {

    private String code;
    private String name;
    private String description;

}
