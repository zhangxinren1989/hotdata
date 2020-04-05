
package disappear.programmer.service.hotdata.handler.result;

import org.springframework.stereotype.Component;

import disappear.programmer.service.hotdata.handler.Handler;

@Component("resultHandler")
public class ResultHandler implements Handler
{
	@Override
	public Result handle(Enum status, Object data) {
		return new Result((Status)status, data);
	}
}

