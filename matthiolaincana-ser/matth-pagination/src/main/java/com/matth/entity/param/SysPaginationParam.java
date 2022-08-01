package com.matth.entity.param;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author Matthiola incana
 * @create 2022/7/14 16:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysPaginationParam extends BaseParam {

    private Map<String,Object> map;

    private String routeName;

    private String tableName;

}
