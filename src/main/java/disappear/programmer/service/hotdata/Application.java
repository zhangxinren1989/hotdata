package disappear.programmer.service.hotdata;

import org.mybatis.spring.annotation.MapperScan;
import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import disappear.programmer.service.hotdata.schedule.starter.hotwords.HotWordsScheduler;

/*
 * 打jar包时要用到这里
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@MapperScan("disappear.programmer.service.hotdata.dao.mapper.hotwords")
public class Application {
	private static ApplicationContext context;
	
	public static void main(String[] args) {
		context = SpringApplication.run(Application.class, args);
		
		try
		{
			((HotWordsScheduler)Application.getBean(HotWordsScheduler.class)).scheduleJobs();
		}
		catch(SchedulerException e)
		{
			e.printStackTrace();
		}
	}

	//通过名字获取上下文中的bean
	  public static Object getBean(String name){
	    return context.getBean(name);
	  }
		 
	  //通过类型获取上下文中的bean
	  public static Object getBean(Class<?> requiredType){
	    return context.getBean(requiredType);
	  }
}
