import com.sun.management.OperatingSystemMXBean;
import com.zhao.www.Application;
import com.zhao.www.entity.model.sys.SysMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.management.ManagementFactory;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
public class ApplicationTest1 {

	/*@Autowired
	private RedisTemplate<String, Object> redisTemplate;*/

	@Test
	public void contextLoads() throws SchedulerException {
		int kb = 1024;
		System.out.println(Runtime.getRuntime().totalMemory() / kb);
		System.out.println(Runtime.getRuntime().freeMemory() / kb);
		System.out.println(Runtime.getRuntime().maxMemory() / kb);
		System.out.println(System.getProperty("os.name"));
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		System.out.println(osmxb.getTotalPhysicalMemorySize() / kb);
		System.out.println(osmxb.getFreePhysicalMemorySize() / kb);
		System.out.println();
	}


	private void listToTree(List<SysMenu> list, SysMenu sysMenu , String parentId){
		for (SysMenu menu : list) {
			if (menu.getParentId().equals(parentId)){
				listToTree(list,menu,menu.getId());
				sysMenu.getChildren().add(menu);
			}
		}
	}

}
