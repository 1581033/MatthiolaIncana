package com.matth.entity.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2023/2/22 15:04
 */
@Data
public class Options implements Serializable {

    private String label;

    private String value;

    private String parentId;

    private List<Options> children;

}
