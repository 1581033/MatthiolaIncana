package com.matth.utils.aspose.words;

import com.aspose.words.IMailMergeDataSource;
import com.aspose.words.ref.Ref;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/7/26 14:42
 */
public class EntityIMailMergeDataSource<T> implements IMailMergeDataSource {

    private final List<T> list;

    private Integer index = -1;

    private static final String TABLE_NAME = "E";

    private final String tableName;

    public EntityIMailMergeDataSource(){
        this(new ArrayList<>());
    }

    public EntityIMailMergeDataSource(List<T> list){
        this(list,TABLE_NAME);
    }

    public EntityIMailMergeDataSource(List<T> list, String tableName){
        this.list = list;
        this.tableName = tableName;
    }

    @Override
    public String getTableName() throws Exception {
        return tableName;
    }

    @Override
    public boolean moveNext() throws Exception {
        index += 1;
        return index < list.size();
    }

    @Override
    public boolean getValue(String s, Ref<Object> ref) throws Exception {
        T t = list.get(index);
        try {
            Field field = t.getClass().getDeclaredField(s);
            field.setAccessible(true);
            ref.set(field.get(t));
            return true;
        }catch (Exception e){
            ref.set(null);
            return false;
        }
    }

    @Override
    public IMailMergeDataSource getChildDataSource(String s) throws Exception {
        return null;
    }

}
