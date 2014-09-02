package zx.soft.negative.sentiment.domain;


/**
 * 文本评分输入数据
 * 
 * @author wanggang
 *
 */
public class TextScorePost {

	/*
	 * 评分类型，adv-广告评分，neg-负面信息评分
	 */
	private String type;
	// 待评分文本
	private String text;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
