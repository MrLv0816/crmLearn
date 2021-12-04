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
public class DictionaryValue {
    private String id;
    private String value;
    private String text;
    private String orderNo;
    private String typeCode;
}
