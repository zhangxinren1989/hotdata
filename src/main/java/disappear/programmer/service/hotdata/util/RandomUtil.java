
package disappear.programmer.service.hotdata.util;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class RandomUtil
{
	static SecureRandom srand = null;
	static {
		try
		{
			srand = SecureRandom.getInstance("SHA1PRNG", "SUN");
			srand.nextInt();
		}
		catch(NoSuchAlgorithmException | NoSuchProviderException e)
		{
			e.printStackTrace();
			srand = new SecureRandom();
		}
	}
	
	public static int randomInt(int bound) {
		return srand.nextInt(bound);
	}
	
	public static int randomInt() {
		return randomInt(Integer.MAX_VALUE);
	}
	
	public static long randomLong() {
		return srand.nextLong();
	}
}

