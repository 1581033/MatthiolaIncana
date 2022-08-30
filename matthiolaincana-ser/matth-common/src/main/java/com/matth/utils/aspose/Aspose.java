package com.matth.utils.aspose;

import com.aspose.cells.SaveOptions;

import java.io.OutputStream;

/**
 * @author Matthiola incana
 * @create 2022/8/16 10:11
 */
public interface Aspose<T> {

    Class<T> getEntityClass();

    OutputStream save(OutputStream stream) throws Exception;

    void save(String path) throws Exception;

}
