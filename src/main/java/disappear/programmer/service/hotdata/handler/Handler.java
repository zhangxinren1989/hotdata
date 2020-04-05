
package disappear.programmer.service.hotdata.handler;

import java.util.Date;

public interface Handler
{
	default Object handle(Enum type, Date date) {return null;};
	
	default Object handle(Enum source, Enum type, Date date) {return null;};

	default void handle(Enum type) {};
	
	default Object handle(Enum e, Object data) {return null;}

}

