
import com.matth.utils.javassist.ModifyUtils;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtField;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class ApplicationTests {

    public static void main(String[] args) throws Exception {
        ModifyUtils.modifyBrowserJar();
        /*ModifyUtils.modifyWordsJar();
        ModifyUtils.modifyCellsJar();*/

        /*modifyUtils.addField("private int age;");

        modifyUtils.rest("C:\\CodeToTest\\MatthiolaIncana\\matthiolaincana-ser\\target\\matthiolaincana-1.0-0.jar");
		CtClass ctClass = ClassPool.getDefault().getCtClass("AsTs");
		CtField a2 = ctClass.getField("a");
		System.out.println(a2.getFieldInfo());*/


        /*AsposeCellUtil<Object> cellUtil = new AsposeCellUtil<>("C:\\Users\\十六夜\\Desktop\\123.xlsx");
        Object[][] objects = cellUtil.exportObject(0, 0, 0);
        //HashMap<Object, Object> hashMap = new HashMap<>();
        System.out.println(Arrays.deepToString(objects));
        System.out.println(objects.length);
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 1; i < objects.length; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("select73812",objects[0][0]);
            hashMap.put("select22802","目标");
            hashMap.put("input28491",objects[i][0]);
            list.add(hashMap);
        }
        System.out.println(list);*/

        /*Aspose<String> cellUtil = new AsposeCellUtil<>();
        Class<String> entityClass = cellUtil.getEntityClass();
        System.out.println(entityClass.getSimpleName());*/
        /*String idcard = "420281200011274212";
        System.out.println(IdcardUtil.getAgeByIdCard(idcard));
        System.out.println(IdcardUtil.getProvinceByIdCard(idcard));
        System.out.println(IdcardUtil.getBirth(idcard));
        System.out.println(IdcardUtil.getCityCodeByIdCard(idcard));*/
        /*System.out.println(SystemUtil.getHostInfo());
        System.out.println(SystemUtil.getRuntimeInfo());*/
    }

}
