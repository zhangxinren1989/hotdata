
package disappear.programmer.service.hotdata.schedule.job.hotwords;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MonthlyHotWordsJob extends HotWordsJob
{
	{
		cron = "";
		id = "monthly_hot_words";
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException
	{

	}

}

