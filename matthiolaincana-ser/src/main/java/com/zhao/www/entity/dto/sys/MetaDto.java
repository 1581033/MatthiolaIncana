package com.zhao.www.entity.dto.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Matthiola incana
 * @create 2021/11/12 17:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaDto  implements Serializable {

    private String title;
    private String icon;
    private String keepAlive;
    private String hiddenHeaderContent;
    private String target;
    private String permission;

}
