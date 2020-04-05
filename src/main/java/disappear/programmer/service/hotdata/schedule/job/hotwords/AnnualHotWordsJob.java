
package disappear.programmer.service.hotdata.schedule.job.hotwords;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AnnualHotWordsJob extends HotWordsJob
{
	{
		cron = "";
		id = "annual_hot_words";
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		
	}

}

