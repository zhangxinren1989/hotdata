
package disappear.programmer.service.hotdata.schedule.job.hotwords;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import disappear.programmer.service.hotdata.Application;
import disappear.programmer.service.hotdata.handler.hotwords.BaiduHotWordsHandler;
import disappear.programmer.service.hotdata.handler.hotwords.HotWordsTypeEnum;
import disappear.programmer.service.hotdata.handler.job.JobHandler;

@Component("dailyHotWordsJob")
public class DailyHotWordsJob extends HotWordsJob
{
	{
//		cron = "0 0 */8 * * ?";
		cron = "0 0 */8 * * ?";
		id = "daily_hot_words";
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		((JobHandler)Application.getBean("jobHandler")).handle(HotWordsTypeEnum.DAILY);
	}

}

