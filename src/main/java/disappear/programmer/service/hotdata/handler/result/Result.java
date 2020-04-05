
package disappear.programmer.service.hotdata.handler.result;

import lombok.Data;

@Data
public class Result{
	private Object data;
	private int code;
	private String message;
	
	public Result(Status status, Object data) {
		this.data = data;
		code = status.code;
		message = status.message;
	}
}