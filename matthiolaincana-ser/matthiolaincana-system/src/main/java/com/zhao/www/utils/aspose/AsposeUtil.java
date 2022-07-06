package com.zhao.www.utils.aspose;


import com.aspose.cells.License;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class AsposeUtil {

    private static final String LICENSE = "license/aspose.cells.xml";

    public static void cellsLicaspose() {
        License license = new License();
        ClassPathResource classPathResourceCell = new ClassPathResource(LICENSE);
        try {
            license.setLicense(classPathResourceCell.getInputStream());
        } catch (IOException e) {
            System.out.println("Aspose注册异常" + e.getMessage());
        }
    }

    public static void wordsLicaspose() {
        com.aspose.words.License license = new com.aspose.words.License();
        ClassPathResource classPathResourceCell = new ClassPathResource(LICENSE);
        try {
            license.setLicense(classPathResourceCell.getInputStream());
        } catch (Exception e) {
            System.out.println("Aspose注册异常" + e.getMessage());
        }
    }

}
