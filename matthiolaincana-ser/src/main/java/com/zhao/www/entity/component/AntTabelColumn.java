package com.zhao.www.entity.component;

import com.zhao.www.annotation.component.AntTabel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.naming.ldap.PagedResultsControl;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Matthiola incana
 * @create 2021/11/16 17:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntTabelColumn implements Serializable {

    private String align;

    private Boolean ellipsis;

    private Integer colSpan;

    private String dataIndex;

    private String title;

    private Integer width;

    private Boolean resizable;

    private Integer sort;

    public AntTabelColumn(String dataIndex,String title,Integer sort) {
        this("center",false,1,dataIndex,title,200,true,sort);
    }

    public static <T> List<AntTabelColumn> getTableLabel(Class<T> clazz){
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> !ObjectUtils.isEmpty(field.getAnnotation(AntTabel.class)))
                .map(field -> {
                    AntTabel annotation = field.getDeclaredAnnotation(AntTabel.class);
                    return new AntTabelColumn(field.getName(),annotation.title(),annotation.sort());
                })
                .sorted(Comparator.comparing(AntTabelColumn::getSort))
                .collect(Collectors.toList());
    }

}
