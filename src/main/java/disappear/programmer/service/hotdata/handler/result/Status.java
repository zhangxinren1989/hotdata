
package disappear.programmer.service.hotdata.handler.result;

public enum Status{
	OK(0, "success"), FAILED(1, "failed");
	
	public int code;
	public String message;
	
	private Status(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
