
package disappear.programmer.service.hotdata.handler.translate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;

import disappear.programmer.service.hotdata.util.CryptUtil;
import disappear.programmer.service.hotdata.util.HttpUtil;
import disappear.programmer.service.hotdata.util.RandomUtil;

@Component("baiduTranslator")
public class BaiduTranslator implements Translator
{
	private static String APPID = "20200206000380717";
	private static String SECRET = "mGXTNvIkuvWaDaLvApAw";
	private static String BASE_URL = "https://fanyi-api.baidu.com/api/trans/vip/translate";
	
	@Override
	public String translate(Language from, Language to, String src)
	{
		try
		{
			StringBuilder url = new StringBuilder();
			int salt = RandomUtil.randomInt();
			String encodeSrc = CryptUtil.md5EncryptString(APPID + src + salt + SECRET);
			url.append(BASE_URL).append("?from=").append(from.language).append("&to=")
				.append(to.language).append("&appid=").append(APPID).append("&q=")
				.append(URLEncoder.encode(src, "utf-8")).append("&salt=").append(salt)
				.append("&sign=").append(encodeSrc);
			return HttpUtil.requestGet(url.toString());
		}
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
			
		}
		
		return "";
	}

}

