package zx.soft.negative.sentiment.web;

import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.negative.sentiment.domain.TextScorePost;
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

	@Override
	public void doInit() {
		application = (TextScoreApplication) getApplication();
	}

	@Post("json")
	public Object returnTextScore(TextScorePost textScorePost) {
		logger.info("Request Url: " + URLCodecUtils.decoder(getReference().toString(), "utf-8") + ".");
		return application.getTextScore(textScorePost.getText(), textScorePost.getType()) + "";
	}

}
