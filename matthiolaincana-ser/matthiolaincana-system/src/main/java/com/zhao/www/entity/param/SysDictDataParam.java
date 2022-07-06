package com.zhao.www.entity.param;

import com.zhao.www.base.entity.param.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author Matthiola incana
 * @create 2022/2/7 10:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictDataParam extends BaseParam {

    @NotBlank(message = "",groups = { inquire.class,save.class })
    private String id;

    private String typeId;

    private String value;

    private String code;

    private String sort;

    private String remark;

    private String status;

    private String cssClass;

    private String listClass;

}
