
package disappear.programmer.service.hotdata.controller.hotwords;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import disappear.programmer.service.hotdata.handler.result.Result;
import disappear.programmer.service.hotdata.service.hotwords.IHotWords;

@RestController
public class HotWordsController
{
	@Autowired
	IHotWords hotWords;
	
	@RequestMapping("/daily")
	public Result queryDailyHotWords(@RequestParam(value = "from", required = false) String from, @RequestParam(value = "date", required = false) String date, HttpServletRequest req, HttpServletResponse resp){
		return hotWords.queryDailyHotWords(from, date);
	}
	
	@RequestMapping("/realtime")
	public Result queryRealTimeHotWords(@RequestParam(value = "from", required = false) String from, HttpServletRequest req, HttpServletResponse resp){
		return hotWords.queryRealTimeHotWords(from);
	}
	
	@RequestMapping("/weekly")
	public Result queryWeeklyHotWords(@RequestParam(value = "from", required = false) String from, @RequestParam(value = "weekEnd", required = false) String weekEnd, HttpServletRequest req, HttpServletResponse resp){
		return hotWords.queryWeeklyHotWords(from, weekEnd);
	}
	
	@RequestMapping("/monthly")
	public Result queryMonthlyHotWords(@RequestParam(required=false) int month, @RequestParam(required=false) int year, 
			@RequestParam(required = false) String from, HttpServletRequest req, HttpServletResponse resp){
		return null;
	}
	
	@RequestMapping("/annual")
	public Result queryAnnualHotWords(@RequestParam(required=false) int year, @RequestParam(required = false) String from, 
			HttpServletRequest req, HttpServletResponse resp){
		return null;
	}
}

