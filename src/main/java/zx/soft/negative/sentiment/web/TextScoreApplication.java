package zx.soft.negative.sentiment.web;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import zx.soft.negative.sentiment.core.AdvertisementClassify;
import zx.soft.negative.sentiment.core.NegativeClassify;

/**
 * 情感分类应用类
 * 
 * @author wanggang
 *
 */
public class TextScoreApplication extends Application {

	private final AdvertisementClassify advClassify;
	private final NegativeClassify negativeClassify;

	public TextScoreApplication() {
		advClassify = new AdvertisementClassify();
		negativeClassify = new NegativeClassify();
	}

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attach("/score", TextScoreResource.class);
		return router;
	}

	/**
	 * 评分类型
	 * @param text：待评分文本
	 * @param type：评分类型，adv-广告评分，neg-负面信息评分
	 * @return
	 */
	public float getTextScore(String text, String type) {
		if (text == null || text.length() == 0 || type == null || type.length() == 0) {
			return 0.0f;
		}
		if ("adv".equalsIgnoreCase(type)) {
			return advClassify.getTextScore(text);
		} else if ("neg".equalsIgnoreCase(type)) {
			return negativeClassify.getTextScore(text);
		} else { // 参数传入错误
			return 0.0f;
		}
	}

	public void close() {
		advClassify.cleanup();
		negativeClassify.cleanup();
	}

}
