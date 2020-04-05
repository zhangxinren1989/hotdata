
package disappear.programmer.service.hotdata.schedule.job.hotwords;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import disappear.programmer.service.hotdata.Application;
import disappear.programmer.service.hotdata.handler.hotwords.HotWordsTypeEnum;
import disappear.programmer.service.hotdata.handler.job.JobHandler;

@Component("realTimeHotWordsJob")
public class RealTimeHotWordsJob extends HotWordsJob
{
	{
//		cron = "0 * * * * ?";
		// 每5小时执行一次定时任务
		cron = "0 */15 * * * ?";
		id = "real_time_hot_words";
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		((JobHandler)Application.getBean("jobHandler")).handle(HotWordsTypeEnum.REAL_TIME);
	}

}

