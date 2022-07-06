import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.ftp.Ftp;
import cn.hutool.extra.ftp.FtpMode;
import com.aspose.words.Document;
import com.zhao.www.utils.aspose.AsposeUtil;
import com.zhao.www.utils.aspose.cells.AsposeCellUtil;
import com.zhao.www.utils.aspose.words.AsposeWordUtil;
import com.zhao.www.utils.javassist.ModifyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtField;
import org.apache.ibatis.javassist.CtMethod;
import org.junit.Test;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.concurrent.ThreadLocalRandom;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)*/
public class ApplicationTest {

	static ApplicationTest a = null;

	/*@Autowired
	private RedisTemplate<String, Object> redisTemplate;*/

	/*@Autowired
	private Scheduler schedule;*/

	public static void main(String[] args) throws Exception {
		/*Ftp ftp = new Ftp("39.101.210.249",9001,"fjqwys","1234QWERasdf.");
		ftp.setMode(FtpMode.Passive);
		String filePath =  "/" + new SimpleDateFormat("yyMMdd").format(new Date());
		boolean upload = ftp.upload("/test/myqzag/configForm/file" + filePath, FileUtil.file("C:\\Users\\十六夜\\Documents\\Work\\1321.wav"));
		System.out.println(upload);
		ftp.close();*/


		/*AsposeWordUtil<Object> wordUtil = new AsposeWordUtil<>();
		wordUtil.save("C:\\Users\\十六夜\\Desktop\\工作表.docx");

		AsposeCellUtil<Object> cellUtil = new AsposeCellUtil<>();
		cellUtil.save("C:\\Users\\十六夜\\Desktop\\工作表.docx");*/
		//String path = "C:\\Users\\十六夜\\Desktop\\aspose-words-22.6-jdk17.jar";
		//ModifyUtils.modify(path,"com.aspose.words.zzcf","zzaM","{this.zzYLF = new java.util.Date(Long.MAX_VALUE);this.zzWzN = com.aspose.words.zzYh2.zzXTF;zzMc = this;}");

		//ModifyUtils modifyUtils = new ModifyUtils(AsTs.class);
		//modifyUtils.modify("com.aspose.words.zzZIg","zzWUc");

		//ModifyUtils.modifyWordsJar();
		//ModifyUtils.modifyCellsJar();

		//modifyUtils.addField("private int age;");

		//modifyUtils.rest("C:\\CodeToTest\\MatthiolaIncana\\matthiolaincana-ser\\target\\matthiolaincana-1.0-0.jar");
		/*CtClass ctClass = ClassPool.getDefault().getCtClass("AsTs");
		CtField a2 = ctClass.getField("a");
		System.out.println(a2.getFieldInfo());*/

	}

	@Test
	public void contextLoads()  {
		/*GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = null;
		try {
			jobKeys = schedule.getJobKeys(matcher);
			for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggers = schedule.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					System.out.println(trigger.toString());
					*//*QuartzJobsVO job = new QuartzJobsVO();
					job.setJobDetailName(jobKey.getName());
					job.setGroupName(jobKey.getGroup());
					job.setJobCronExpression("触发器:" + trigger.getKey());
					Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
					job.setStatus(triggerState.name());
					if (trigger instanceof CronTrigger) {
						CronTrigger cronTrigger = (CronTrigger) trigger;
						String cronExpression = cronTrigger.getCronExpression();
						job.setJobCronExpression(cronExpression);
					}
					jobList.add(job);*//*
				}
			}

		} catch (SchedulerException e) {
			e.printStackTrace();
		}*/
		/*QuartzBean quartzBean = new QuartzBean();
		quartzBean.setJobClass("com.zhao.www.timer.QuartzTask");
		quartzBean.setId(UUID.randomUUID().toString());
		quartzBean.setCronExpression("* * * * * ? ");
		quartzBean.setJobName("第一个任务");
		*//*quartzService.addTask(quartzBean);*//*
		quartzService.pauseTask(quartzBean);*/

	}

	public static String getToken(String name){
		return Jwts.builder()
				.setId(String.valueOf(ThreadLocalRandom.current().nextInt()))
				.setSubject("com.java")
				.setIssuer(name)
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS512, "gl98khsdakjfkjas")
				.compact();
	}

	public static void parser(String token){
		Claims claims = (Claims) Jwts.parser()
				.setSigningKey("gl98khsdakjfkjas")
				.parse(token)
				.getBody();
		System.out.println(claims.getIssuer());

	}

}
