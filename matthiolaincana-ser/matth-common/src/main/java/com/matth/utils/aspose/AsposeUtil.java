package com.matth.utils.aspose;


import com.aspose.cells.License;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class AsposeUtil {

    private static final String LICENSE = "license/aspose.cells.xml";

    private static final InputStream STREAM = AsposeUtil.class.getClassLoader().getResourceAsStream(LICENSE);

    public static void cellsLicaspose() {
        License license = new License();
        try {
            license.setLicense(STREAM);
        } catch (Exception e) {
            System.out.println("Aspose注册异常" + e.getMessage());
        }
    }

    public static void wordsLicaspose() {
        com.aspose.words.License license = new com.aspose.words.License();
        try {
            license.setLicense(STREAM);
        } catch (Exception e) {
            System.out.println("Aspose注册异常" + e.getMessage());
        }
    }

}
