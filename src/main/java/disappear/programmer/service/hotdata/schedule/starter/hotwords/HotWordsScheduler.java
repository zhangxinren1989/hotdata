
package disappear.programmer.service.hotdata.schedule.starter.hotwords;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import disappear.programmer.service.hotdata.Application;
import disappear.programmer.service.hotdata.schedule.job.hotwords.DailyHotWordsJob;
import disappear.programmer.service.hotdata.schedule.job.hotwords.HotWordsJob;
import disappear.programmer.service.hotdata.schedule.job.hotwords.RealTimeHotWordsJob;
import disappear.programmer.service.hotdata.schedule.job.hotwords.WeeklyHotWordsJob;

@Component
public class HotWordsScheduler
{
	@Autowired
    SchedulerFactoryBean schedulerFactoryBean;
	
	@Autowired
	@Qualifier("dailyHotWordsJob")
	DailyHotWordsJob dailyHotWordsJob;
	
	@Autowired
	@Qualifier("realTimeHotWordsJob")
	RealTimeHotWordsJob realTimeHotWordsJob;

	@Autowired
	@Qualifier("weeklyHotWordsJob")
	WeeklyHotWordsJob weeklyHotWordsJob;


    Scheduler scheduler;
    
   // @PostConstruct
    public void scheduleJobs() throws SchedulerException {
        scheduler = schedulerFactoryBean.getScheduler();
        startJobs();
    }
    
    //@PreDestroy:https://www.cnblogs.com/libin6505/p/10571523.html
    public void stopJobs() {
    	
    }
    
    private void startJobs() {
    	startJob(DailyHotWordsJob.class);
    	startJob(RealTimeHotWordsJob.class);
    	startJob(WeeklyHotWordsJob.class);
    	//startJob(MonthlyHotWordsJob.class);
    	//startJob(AnnualHotWordsJob.class);
    }                          
    
    public void startJob(Class<? extends HotWordsJob> hotWordsJob) {
		try
		{
			HotWordsJob intance = (HotWordsJob)Application.getBean(hotWordsJob);
			String id = intance.id;
			String cron = intance.cron;
			
			JobDetail jobDetail = JobBuilder.newJob(hotWordsJob).withIdentity("job-" + id, "group-" + id).build();
	        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
	        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger-" + id, "group-" + id).withSchedule(scheduleBuilder).build();
	        scheduler.scheduleJob(jobDetail, cronTrigger);
		}
		catch(IllegalArgumentException | SecurityException | SchedulerException e)
		{
			
			e.printStackTrace();
			
		}
    }
    
    public void modifyJob(String cron, String id) {
    	TriggerKey triggerKey = TriggerKey.triggerKey("trigger-" + id, "group-" + id);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        CronTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity("trigger-" + id, "group-" + id).withSchedule(scheduleBuilder).build();
        try
		{
			scheduler.rescheduleJob(triggerKey, newTrigger);
		}
		catch(SchedulerException e)
		{
			e.printStackTrace();
			
		}
    }
    
    public String getJobStatus(String id) {
    	TriggerKey triggerKey = TriggerKey.triggerKey("trigger-" + id, "group-" + id);
    	TriggerState state = TriggerState.ERROR;
		try
		{
			state = scheduler.getTriggerState(triggerKey);
		}
		catch(SchedulerException e)
		{
			e.printStackTrace();
		}
        return state.name();
    }
    
    public void pauseJob(String id) {
    	try
		{
			scheduler.pauseJob(JobKey.jobKey("job-" + id, "group-" + id));
		}
		catch(SchedulerException e)
		{
			e.printStackTrace();
		}
    }
    
    public void resumeJob(String id) {
    	try
		{
			scheduler.resumeJob(JobKey.jobKey("job-" + id, "group-" + id));
		}
		catch(SchedulerException e)
		{
			e.printStackTrace();
			
		}
    }
}

