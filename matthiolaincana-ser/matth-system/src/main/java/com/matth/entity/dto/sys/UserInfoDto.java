package com.matth.entity.dto.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2021/11/12 16:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto implements Serializable {

    private String id;
    private String name;
    private String username;
    private String avatar;
    private String status;
    private String telephone;
    private List<RoleDto> role;

}
