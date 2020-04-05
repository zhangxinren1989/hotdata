
package disappear.programmer.service.hotdata.schedule.job.hotwords;

import org.quartz.Job;

public abstract class HotWordsJob implements Job
{
	public String cron;
	public String id;
}

