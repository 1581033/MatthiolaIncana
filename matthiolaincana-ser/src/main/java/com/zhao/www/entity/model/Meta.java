package com.zhao.www.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Matthiola incana
 * @create 2021/11/10 20:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meta implements Serializable {
    private String icon;
    private String title;
    private String show;
}
