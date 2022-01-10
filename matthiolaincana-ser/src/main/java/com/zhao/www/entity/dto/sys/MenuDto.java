package com.zhao.www.entity.dto.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/12 16:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto implements Serializable {

    private String permissionId;
    private String permissionName;
    private String id;
    private String path;
    private String name;
    private String component;
    private String redirect;
    private String parentId;
    private String hidden;
    private List<String> actionList;
    private MetaDto meta;
    private List<PermissionDto> actionEntitySet;

}
