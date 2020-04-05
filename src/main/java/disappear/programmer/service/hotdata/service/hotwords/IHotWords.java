
package disappear.programmer.service.hotdata.service.hotwords;

import disappear.programmer.service.hotdata.handler.result.Result;

public interface IHotWords
{
	public Result queryDailyHotWords(String from, String date);
	
	public Result queryRealTimeHotWords(String from);
	
	public Result queryWeeklyHotWords(String from, String weekEnd);
	
	public Result queryMonthlyHotWords(int month, int year, String from);
	
	public Result queryAnnualHotWords(int year, String from);
}

