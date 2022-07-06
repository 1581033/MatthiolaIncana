package com.matth.entity.dto.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/12 16:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto implements Serializable {

    private String id;
    private String name;
    private String describe;
    private String status;
    private String deleted;
    private List<MenuDto> permissions;

}
