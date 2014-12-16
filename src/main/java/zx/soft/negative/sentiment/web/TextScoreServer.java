package zx.soft.negative.sentiment.web;

import java.util.Properties;

import org.restlet.Component;
import org.restlet.data.Protocol;

import zx.soft.utils.config.ConfigUtil;
import zx.soft.utils.jackson.ReplaceConvert;

/**
 * 與请数据评分服务：包括广告信息评分、负面信息评分
 * 
 * POST: http://localhost:XXXX/sentiment/score
 *        传入参数：TextScorePost对象，包含type和text两个参数。
 * 
 * @author wanggang
 *
 */
public class TextScoreServer {

	private final Component component;
	private final TextScoreApplication naiveBayesApplication;

	private final int PORT;

	public TextScoreServer() {
		Properties props = ConfigUtil.getProps("web-server.properties");
		PORT = Integer.parseInt(props.getProperty("api.port"));
		component = new Component();
		naiveBayesApplication = new TextScoreApplication();
	}

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		TextScoreServer server = new TextScoreServer();
		server.start();

	}

	public void start() {
		component.getServers().add(Protocol.HTTP, PORT);
		try {
			component.getDefaultHost().attach("/sentiment", naiveBayesApplication);
			ReplaceConvert.configureJacksonConverter();
			component.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void stop() {
		try {
			component.stop();
			naiveBayesApplication.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
