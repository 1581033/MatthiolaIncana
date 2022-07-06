package com.zhao.www.utils.aspose.cells;

import com.aspose.cells.ICellsDataTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Matthiola incana
 * @create 2022/4/19 17:11
 */
public class EntityDataTable<T> implements ICellsDataTable {

    private List<T> list;

    private Integer index;

    private String[] columns;

    public EntityDataTable(){
        this.list = new ArrayList<>();
    }

    public EntityDataTable(List<T> list){
        this.list = list;
        this.columns = getColumns();
    }

    public void add(T t){
        this.list.add(t);
    }

    public void addAll(List<T> list){
        this.list.addAll(list);
    }

    @Override
    public String[] getColumns() {
        return Arrays.stream(this.list.get(0).getClass().getDeclaredFields()).map(Field::getName).toArray(String[]::new);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public void beforeFirst() {
        index = -1;
    }

    @Override
    public Object get(int i) {
        return get(columns[i]);
    }

    @Override
    public Object get(String s) {
        T t = list.get(index);
        try {
            Field field = t.getClass().getDeclaredField(s);
            field.setAccessible(true);
            return field.get(t);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }

    @Override
    public boolean next() {
        index += 1;
        return index < getCount();
    }
}
