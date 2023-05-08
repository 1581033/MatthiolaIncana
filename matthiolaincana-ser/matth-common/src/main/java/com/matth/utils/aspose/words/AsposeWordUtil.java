package com.matth.utils.aspose.words;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.LoadOptions;
import com.aspose.words.SaveOptions;
import com.matth.utils.aspose.Aspose;
import com.matth.utils.aspose.AsposeUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author Matthiola incana
 * @create 2022/6/22 15:28
 */
public class AsposeWordUtil<T> implements Aspose<T> {

    private final Document document;

    private DocumentBuilder documentBuilder;

    static {
        AsposeUtil.wordsLicaspose();
    }

    public AsposeWordUtil() throws Exception {
        this.document = new Document();
    }

    public AsposeWordUtil(InputStream inputStream) throws Exception {
        LoadOptions loadOptions = new LoadOptions();
        this.document = new Document(inputStream, loadOptions);
    }

    public AsposeWordUtil(String path) throws Exception {
        LoadOptions loadOptions = new LoadOptions();
        this.document = new Document(path,loadOptions);
    }

    public Document getDocument(){
        return document;
    }

    public DocumentBuilder getDocumentBuilder() throws Exception {
        return documentBuilder == null ? new DocumentBuilder(document) : documentBuilder;
    }

    public void importMap(Map<String,String> map) throws Exception {
        String[][] strings = new String[2][map.size()];
        int i = 0;
        for(Map.Entry<String,String> item : map.entrySet()) {
            strings[0][i] = item.getKey();
            strings[1][i] = item.getValue();
            i++;
        }
        document.getMailMerge().execute(strings[0],strings[1]);
    }

    public void importEntity(List<T> list) throws Exception {
        document.getMailMerge().execute(new EntityIMailMergeDataSource<>(list));
    }

    public void importEntity(List<T> list, String tabelName) throws Exception {
        document.getMailMerge().executeWithRegions(new EntityIMailMergeDataSource<>(list, tabelName));
    }

    public void save(String path) throws Exception {
        this.document.save(path);
    }

    public void save(String fileName, SaveOptions saveOptions) throws Exception {
        this.document.save(fileName,saveOptions);
    }

    public Class<T> getEntityClass() {
        return null;
    }

    public OutputStream save(OutputStream stream) throws Exception {
        this.document.save(stream,document.getOriginalLoadFormat());
        return stream;
    }

}
