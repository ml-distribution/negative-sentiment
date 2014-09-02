package zx.soft.negative.sentiment.web;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.negative.sentiment.utils.URLCodecUtils;

/**
 * 情感分类资源类
 * 
 * @author wanggang
 *
 */
public class TextScoreResource extends ServerResource {

	private static Logger logger = LoggerFactory.getLogger(TextScoreResource.class);

	private TextScoreApplication application;

	private String text = "";
	private String type = "";

	@Override
	public void doInit() {
		text = (String) getRequest().getAttributes().get("text");
		type = (String) getRequest().getAttributes().get("type");
		application = (TextScoreApplication) getApplication();
	}

	@Get("txt")
	public Object returnTextScore() {
		logger.info("Request Url: " + URLCodecUtils.decoder(getReference().toString(), "utf-8") + ".");
		text = URLCodecUtils.decoder(text, "utf-8");
		if (text == null || text.length() == 0 || type == null || type.length() == 0) {
			return "0.0";
		}
		return application.getTextScore(text, type) + "";
	}

}
