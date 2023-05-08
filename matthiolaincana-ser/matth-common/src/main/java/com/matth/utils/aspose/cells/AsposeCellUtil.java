package com.matth.utils.aspose.cells;

import com.aspose.cells.*;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matth.utils.aspose.Aspose;
import com.matth.utils.aspose.AsposeUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Matthiola incana
 * @create 2022/4/19 18:19
 */
public class AsposeCellUtil<T> implements Aspose<T> {

    private final Workbook workbook;
    private WorkbookDesigner workbookDesigner;

    static {
        AsposeUtil.cellsLicaspose();
    }

    public AsposeCellUtil(){
        this.workbook = new Workbook();
    }

    public AsposeCellUtil(InputStream inputStream) throws Exception {
        LoadOptions loadOptions = new LoadOptions();
        loadOptions.setMemorySetting(MemorySetting.MEMORY_PREFERENCE);
        this.workbook = new Workbook(inputStream,loadOptions);
    }

    public AsposeCellUtil(String path) throws Exception {
        LoadOptions loadOptions = new LoadOptions();
        loadOptions.setMemorySetting(MemorySetting.MEMORY_PREFERENCE);
        this.workbook = new Workbook(path,loadOptions);
    }

    protected Class<T> currentModelClass() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        ParameterizedType superclass =(ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /*public Class<T> getEntityClass() {
        return this.tClass;
    }*/

    public Workbook getWorkbook(){
        return workbook;
    }

    public WorkbookDesigner getWorkbookDesigner(){
        if (workbookDesigner == null){
            workbookDesigner = new WorkbookDesigner(workbook);
        }
        return workbookDesigner;
    }

    public void setDataSource(ICellsDataTable iCellsDataTable) throws Exception {
        WorkbookDesigner designer = getWorkbookDesigner();
        designer.setDataSource("E",iCellsDataTable);
        designer.process(true);
    }

    /*public void importTabel(Class<T> clazz, int firstRow, int firstColumn, ICellsDataTable iCellsDataTable) throws Exception {
        importArray(clazz,firstRow,firstColumn);
        setDataSource(iCellsDataTable);
    }*/

    public void importTabel(String[][] strings, int firstRow, int firstColumn, ICellsDataTable iCellsDataTable) throws Exception {
        importArray(strings,firstRow,firstColumn);
        setDataSource(iCellsDataTable);
    }

    public void importArray(String[][] strings, int firstRow, int firstColumn) throws Exception {
        Cells cells = this.workbook.getWorksheets().get(0).getCells();
        cells.importArray(strings,firstRow,firstColumn);
    }

    /*public void importArray(Class<T> clazz, int firstRow, int firstColumn)  throws Exception {
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(s -> s.getAnnotation(AntTabel.class) != null)
                .sorted(Comparator.comparing(s -> s.getAnnotation(AntTabel.class).title()))
                .collect(Collectors.toList());
        String[][] strings = new String[2][fields.size()];
        for (int i = 0; i < fields.size(); i++) {
            strings[0][i] = fields.get(i).getAnnotation(AntTabel.class).title();
            strings[1][i] = "&=[E]." + fields.get(i).getName();
        }
        importArray(strings,firstRow,firstColumn);
    }*/

    /*public List<T> exportArray(Class<T> clazz, int firstRow, int firstColumn, int endRow) throws Exception {
        Object[][] objects = exportObject(firstRow, firstColumn, endRow);
        List<T> list = new ArrayList<>();
        for (int i = 1; i < objects.length; i++) {
            T instance = clazz.newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                AntTabel annotation = field.getDeclaredAnnotation(AntTabel.class);
                if (annotation != null) {
                    for (int i1 = 0; i1 < objects[0].length; i1++) {
                        if (annotation.title().equals(objects[0][i1]) && objects[i][i1] != null){
                            invoke(field,instance,objects[i][i1]);
                            break;
                        }
                    }
                }
            }
            list.add(instance);
        }
        return list;
    }*/

    public Object[][] exportObject(int firstRow, int firstColumn, int endRow) throws Exception {
        Cells cells = this.workbook.getWorksheets().get(0).getCells();
        int totalColumns = cells.getMaxColumn() - firstColumn + 1;
        int totalRows = cells.getRows().getCount() - firstRow - endRow;
        return exportObject(firstRow, firstColumn, totalRows, totalColumns);
    }

    public Object[][] exportObject(int firstRow, int firstColumn, int endRow, int totalColumn) throws Exception {
        Cells cells = this.workbook.getWorksheets().get(0).getCells();
        return cells.exportArray(firstRow, firstColumn, endRow, totalColumn);
    }

    public void save(String path) throws Exception {
        this.workbook.save(path);
    }

    public void save(String fileName, SaveOptions saveOptions){
        try {
            this.workbook.save(fileName,saveOptions);
        } catch (Exception e) {
            System.out.println("保存错误" + e.getMessage());
        }
    }

    public OutputStream save(OutputStream stream){
        try {
            this.workbook.save(stream, workbook.getFileFormat());
        } catch (Exception e) {
            System.out.println("保存错误" + e.getMessage());
        }
        return stream;
    }

    private void invoke(Field field,T instance, Object o) throws Exception {
        String fieldName = field.getName();
        String name = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        Method method = instance.getClass().getMethod(name, field.getType());
        switch (field.getType().getSimpleName()){
            case "String":
                o = String.valueOf(o);
                break;
            case "Integer":
                o = Integer.parseInt(o.toString());
                break;
            default:
                break;
        }
        method.invoke(instance, o);
    }

}
