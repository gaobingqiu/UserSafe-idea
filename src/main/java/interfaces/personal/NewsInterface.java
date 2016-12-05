package interfaces.personal;

import interfaces.entity.BaseResponse;
import interfaces.entity.News;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.base.JsonUtil;
import com.http.HttpRequest;

@Controller
@RequestMapping("/newsInterface")
public class NewsInterface {
	String url;
	// 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	String param;
	String httpUrl;
	private final String KeyId = "bc22ec037f0ddacb73c341fcb187a432";


	@RequestMapping(value = "/getNews", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void getGlobalNews(HttpServletRequest request,
							  HttpServletResponse response, String type, int pageIndex)
			throws ServletException, IOException {
		switch (type) {
			case "全球":
				httpUrl = "http://apis.baidu.com/txapi/world/world";
				break;
			case "体育":
				httpUrl = "http://api.tianapi.com/tiyu/";
				break;
			default:
				httpUrl = "http://api.tianapi.com/keji/";
				break;
		}
		String httpArg = "num=10&page=" + pageIndex + "&key=" + KeyId;
		String jsonResult = HttpRequest.request(httpUrl, httpArg);
		jsonResult = jsonResult.replaceAll("\\\\", "");
		int length = jsonResult.length();
		jsonResult = jsonResult.substring(39, length - 3);
		List<News> list = new ArrayList<News>();
		list = JSON.parseArray(jsonResult, News.class);
		BaseResponse<List<News>> baseResponse = new BaseResponse<List<News>>();
		baseResponse.setCode(0);
		baseResponse.setData(list);
		PrintWriter out = response.getWriter();
		out.print(JsonUtil.createJsonString(baseResponse));
		out.flush();
		out.close();
	}
}
