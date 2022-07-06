package com.matth.utils.aspose.words;

import com.aspose.words.Document;
import com.matth.utils.aspose.AsposeUtil;

import java.io.InputStream;

/**
 * @author Matthiola incana
 * @create 2022/6/22 15:28
 */
public class AsposeWordUtil<T> {

    private final Document document;

    static {
        AsposeUtil.wordsLicaspose();
    }

    public AsposeWordUtil() throws Exception {
        this.document = new Document();
    }

    public AsposeWordUtil(InputStream inputStream) throws Exception {
        this.document = new Document(inputStream);
    }

    public Document getDocument(){
        return document;
    }

    public void save(String path) throws Exception {
        this.document.save(path);
    }

}
