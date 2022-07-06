package com.matth.entity.dto.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Matthiola incana
 * @create 2021/11/12 17:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto implements Serializable {

    private String action;
    private String describe;
    private String defaultCheck;

}
