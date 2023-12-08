import cn.hutool.core.convert.NumberChineseFormatter;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ZipUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.aspose.words.*;
import com.matth.config.FtpServerConfig;
import com.matth.entity.model.SysMenu;
import com.matth.utils.aspose.cells.AsposeCellUtil;
import com.matth.utils.aspose.words.AsposeWordUtil;
import com.matth.utils.aspose.words.EntityIMailMergeDataSource;
import com.matth.utils.aspose.words.ImageFilenameCallback;
import com.matth.utils.aspose.words.MapMailMergeDataSource;
import com.matth.utils.jwt.JwtTokenUtil;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	private final static String MOUDEL = "sys";

	private final static String stationId = "1ENQ3747OO1L0100007F1564CAFA5C5E";

	private final static String bussId = "1ERD9IA7CORP0100007F7494C5C8C0F9";

	private final static String comBussId = "1EQ9MDT7I85N0100007F68338BCC22F3";

	private final static String CACHESESSIONID = "1G79AR4KM6NJ0100007F14747DA0BF24";

	//1G79AR4KM6NJ0100007F14747DA0BF24
	//@Autowired
	private FtpServerConfig ftpServerConfig;

	@Test
	public void a(){
		//System.out.println(ftpServerConfig.getHost());
	}

	public static void main(String[] args) throws Exception {
		System.out.println(NumberChineseFormatter.formatThousand(12,true));
		System.out.println(String.format("\\\"input59311\\\":\\\"%s\\\"","sadsadsa"));
		String buildUrl = UrlBuilder.create()
				.setHost("10.130.1.78/tchpki")
				.addPath("tchpki_verifycert.jsp")
				.addQuery("appid", "123")
				.addQuery("appkey", "123")
				.build();
		System.out.println(buildUrl);

		String data = "\r\n\r\n\r\n\r\n \r\n\r\n\r\n\r\n[{}]";
		List<Map> maps = JSON.parseArray(data, Map.class);
		System.out.println(maps);

		/*AsposeWordUtil<Object> wordUtil = new AsposeWordUtil<>("C:\\Users\\十六夜\\Desktop\\zxd.docx");
		wordUtil.save("C:\\Users\\十六夜\\Desktop\\123.docx");*/

		/*HashMap<Object, Object> objectHashMap = new HashMap<>();
		HashMap<Object, Object> hashMap = new HashMap<>();
		hashMap.put("asdas","sadasd");
		objectHashMap.put("1",hashMap);
		System.out.println(JSONObject.toJSONString(objectHashMap));
		System.out.println(JSONArray.parseArray("[{\"asd\":\"asd\"}]"));
		System.out.println("[{\"asd\":\"asd\"}]");

		ArrayList<Object> objects = new ArrayList<>();
		objects.add(hashMap);
		System.out.println(JSONArray.parseArray("a"));*/

		/*File file = new File("C:\\Users\\十六夜\\Desktop\\123");
		//FileCopyUtils.copy(file,new File("C:\\Users\\十六夜\\Desktop\\1234"));
		FileUtil.copyFilesFromDir(new File("C:\\Users\\十六夜\\Desktop\\123"),new File("C:\\Users\\十六夜\\Desktop\\1234"),false);

		System.out.println(file.isDirectory());*/
		/*AsposeWordUtil<Object> wordUtil = new AsposeWordUtil<>("C:\\Users\\十六夜\\Desktop\\zxd.docx");
		DocumentBuilder documentBuilder = wordUtil.getDocumentBuilder();
		documentBuilder.moveToMergeField("sss");
		documentBuilder.getFont().setName("Wingdings 2");
		documentBuilder.write("\uF052");*/
		/*documentBuilder.insertField("sss","123");
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("sss","123");
		wordUtil.getDocument().getMailMerge().setFieldMergingCallback(new ImageFilenameCallback(hashMap));
		wordUtil.importMap(hashMap);*/
		//wordUtil.save("C:\\Users\\十六夜\\Desktop\\123.docx");

		/*FileWriter fileWriter = new FileWriter("C:\\Users\\十六夜\\Desktop\\" + "123" + "/" + "123.txt");
		HashMap<Object, Object> hashMap = new HashMap<>();
		hashMap.put("121","asd");
		hashMap.put("sjlx","123");
		fileWriter.write(JSON.toJSONString(hashMap));
		FileWriter fileWriter1 = new FileWriter("C:\\Users\\十六夜\\Desktop\\" + "123" + "/" + "124.txt");
		fileWriter1.write("asdsadsadas");*/
		//ZipUtil.zip("C:\\Users\\十六夜\\Desktop\\" + "123");
		//System.out.println(String.format("http://%s:%d%s", "124.223.52.171", 15822, "/a/a/g"));
		/*SysMenu sysMenu = new SysMenu();
		ArrayList<SysMenu> list = new ArrayList<>();
		TreeNodeConfig config = new TreeNodeConfig();
		config.setDeep(6);
		List<Tree<String>> build = TreeUtil.build(list, "0", config, (object, tree) -> {
			tree.setId(object.getId());//必填属性
			tree.setParentId(object.getParentId());//必填属性
			tree.setName(object.getName());
			// 扩展属性 ...
			//tree.putExtra("children",object.getChildren());
		});*/


		/*String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0NjE4NWI3OC1kZDMxLTQ1ODQtODEzZS1iOWExMjVhM2QyYTgiLCJpYXQiOjE2NTc3MDU5NzAsIm5iZiI6MTY1NzcwNTk3MCwiaXNzIjoiV1VZVVdFSV9CQUNLX0FQSSIsImF1ZCI6ImFkbWluaXN0cmF0b3IiLCJleHAiOjE2NTc3MTMxNzB9.WHtVouY1xvRKQ1-SZpxqumakpgbvL69NEzgG7LJ3wlg";
		String key = "984BE02DCA0E48418AAB9FB1C8083A0F";
		String tokenUserName = JwtTokenUtil.getTokenUserName(token,key);
		System.out.println(tokenUserName);
		System.out.println(IdUtil.fastSimpleUUID().toUpperCase());*/
		/*ApplicationTests applicationTests = new ApplicationTests();
		String selectData = applicationTests.getSelectData();
		System.out.println(selectData);*/
//		if (!StringUtils.hasLength(selectData)){
//			System.out.println("已被抢空！");
//			return;
//		}
//		OkHttpClient client = new OkHttpClient();
//		MediaType mediaType = MediaType.parse("");
//		StringBuilder stringBuilder = new StringBuilder();
//		stringBuilder
//				.append("comBussId=").append(comBussId)
//				.append("&comBussName=").append(URLEncoder.encode("摩托车注册、转移或转入（汽油车、电动车）"))
//				.append("&comBussName=").append("4102")
//				.append("&stationId=").append(stationId)
//				.append("&stationName=").append(URLEncoder.encode("晋江市车辆管理所业务大厅"))
//				.append("&dotAddress=").append(URLEncoder.encode("晋江市福兴东路罗山段1号"))
//				.append("&isBookable=").append("1")
//				.append("&canBookCurrent=").append("1")
//				.append("&buss_status=").append("on")
//				.append("&bookingDate=").append(LocalDate.now().plusDays(1))
//				.append("&bookingTimeStr=").append(selectData)
//				.append("&bookingTimeInfo=").append(selectData)
//				.append("&licenseNo=").append("350582198401071044")
//				.append("&phone=").append("13545501419")
//				.append("&frameNo=").append("294822205016814")
//				.append("&bussId=").append(bussId)
//				.append("&bussName=").append("摩托车注册、转移及转入（汽油车、电动车）")
//				.append("&isBookable=").append("true")
//				.append("&bussCode=").append("174");
//		RequestBody requestBody = RequestBody.create(mediaType,stringBuilder.toString());
//		Request request  = new Request.Builder()
//				.url("https://jja.degal.cn/kmarch-web/comBooking/sendBooking")
//				.addHeader("Accept","*/*")
//				.addHeader("Accept-Encoding","gzip, deflate, br")
//				.addHeader("Accept-Language","zh,zh-CN;q=0.9,en;q=0.8")
//				.addHeader("Connection","keep-alive")
//				.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
//				.addHeader("Cookie","CACHESESSIONID=" + CACHESESSIONID)
//				.addHeader("Host","jja.degal.cn")
//				.addHeader("Origin","https://jja.degal.cn")
//				.addHeader("Referer","https://jja.degal.cn/kmarch-web//comBooking/bookingIndex/" + comBussId)
//				.addHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1")
//				.post(requestBody)
//				.build();
//		Call call = client.newCall(request);
//
//		String string1 = call.execute().body().string();
//		System.out.println("onResponse: " + string1);

	}

	public String getSelectData() throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request  = new Request.Builder()
				.url("https://jja.degal.cn/kmarch-web/comBooking/queryBookTime?stationId="+ stationId +"&bussId="+ bussId +"&bookingDate="+ LocalDate.now().plusDays(1) +"&isComprehensive=true&comBussId=" + comBussId)
				.addHeader("Accept","*/*")
				.addHeader("Accept-Encoding","gzip, deflate, br")
				.addHeader("Accept-Language","zh,zh-CN;q=0.9,en;q=0.8")
				.addHeader("Connection","keep-alive")
				.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
				.addHeader("Cookie","CACHESESSIONID=" + CACHESESSIONID)
				.addHeader("Host","jja.degal.cn")
				.addHeader("Origin","https://jja.degal.cn")
				.addHeader("Referer","https://jja.degal.cn/kmarch-web//comBooking/bookingIndex/" + comBussId)
				.addHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1")
				.build();
		Call call = client.newCall(request);
		String string1 = call.execute().body().string();
		System.out.println(string1);
		HashMap hashMap = JSONObject.parseObject(string1, HashMap.class);
		System.out.println(hashMap);
		List<SelectData> selectData = JSON.parseArray(hashMap.get("result").toString(), SelectData.class);
		List<String> strings = Arrays.asList();
		for (SelectData selectDatum : selectData) {
			if ("0".equals(selectDatum.getStatus()) && !strings.contains(selectDatum.getLabel()) && !"10:00:46".equals(selectDatum.getLabel())){
				System.out.println(selectDatum.getLabel());
				return URLEncoder.encode(selectDatum.getLabel());
			}
		}
		return "";
	}

}
