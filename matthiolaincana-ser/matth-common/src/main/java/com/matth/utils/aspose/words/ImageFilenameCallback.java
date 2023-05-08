package com.matth.utils.aspose.words;

import cn.hutool.core.img.ImgUtil;
import com.aspose.words.*;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;

/**
 * @author Matthiola incana
 * @create 2022/8/11 14:44
 */
public class ImageFilenameCallback implements IFieldMergingCallback {

    private final Map<String, String> hashMap;

    public ImageFilenameCallback(Map<String,String> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void fieldMerging(FieldMergingArgs fieldMergingArgs) throws Exception {
        System.out.println("FieldMergingArgs ----------->" + fieldMergingArgs.getFieldName());
    }

    @Override
    public void imageFieldMerging(ImageFieldMergingArgs imageFieldMergingArgs) throws Exception {
        System.out.println("ImageFieldMergingArgs ----------->" + imageFieldMergingArgs.getFieldName());
        System.out.println(hashMap);
        if (imageFieldMergingArgs.getFieldName().equals("logo")) {
            imageFieldMergingArgs.setImageHeight(new MergeFieldImageDimension(80, MergeFieldImageDimensionUnit.POINT));
            imageFieldMergingArgs.setImageWidth(new MergeFieldImageDimension(80, MergeFieldImageDimensionUnit.POINT));
            byte[] bytes = new BASE64Decoder().decodeBuffer(imageFieldMergingArgs.getFieldValue().toString());
            System.out.println(imageFieldMergingArgs.getFieldValue().toString());
            System.out.println(Arrays.toString(bytes));
            imageFieldMergingArgs.setImageStream(new ByteArrayInputStream(bytes));
            //imageFieldMergingArgs.setImage(ImageIO.read(new File(hashMap.get(imageFieldMergingArgs.getFieldValue().toString()))));
            /*imageFieldMergingArgs.setImageHeight(new MergeFieldImageDimension(80, MergeFieldImageDimensionUnit.POINT));
            imageFieldMergingArgs.setImageWidth(new MergeFieldImageDimension(80, MergeFieldImageDimensionUnit.POINT));
            imageFieldMergingArgs.setImage(ImageIO.read(new File(hashMap.get(imageFieldMergingArgs.getFieldValue().toString()))));*/
        }
    }

}
