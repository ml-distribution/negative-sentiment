package zx.soft.negative.sentiment.core;

import java.util.ArrayList;
import java.util.List;

import zx.soft.negative.sentiment.analyzer.AnalyzerTool;
import zx.soft.negative.sentiment.domain.EmotionDictionary;
import zx.soft.utils.file.ReadFileUtils;

/**
 * 與请分类抽象类
 * 
 * @author wanggang
 *
 */
public abstract class ClassifySentiment {

	protected final EmotionDictionary emotionDictionary;

	protected static final String BASE_DIR = "emotion_dict/";

	private static final String PUNCTUALS = ",.!?;…~……，。！？；...～...... ";
	private static final String PUNCTUALS_UNUSED = "[,.?;…~……，。？；...～...... ]";

	// 注意：分词器要使用完整的分词器，不要去除停用词。
	protected final AnalyzerTool analyzerTool;

	public ClassifySentiment() {
		analyzerTool = new AnalyzerTool();
		emotionDictionary = new EmotionDictionary();
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		// insufficiently
		emotionDictionary.setInsufficiently(ReadFileUtils.getFileToListFromResources(BASE_DIR + "insufficiently"));
		// inverse
		emotionDictionary.setInverse(ReadFileUtils.getFileToListFromResources(BASE_DIR + "inverse"));
		// ish
		emotionDictionary.setIsh(ReadFileUtils.getFileToListFromResources(BASE_DIR + "ish"));
		// more
		emotionDictionary.setMore(ReadFileUtils.getFileToListFromResources(BASE_DIR + "more"));
		// most
		emotionDictionary.setMost(ReadFileUtils.getFileToListFromResources(BASE_DIR + "most"));
		// over
		emotionDictionary.setOver(ReadFileUtils.getFileToListFromResources(BASE_DIR + "over"));
		// very
		emotionDictionary.setVery(ReadFileUtils.getFileToListFromResources(BASE_DIR + "very"));
	}

	/**
	 * 分句：对输入的文本进行分句
	 */
	public List<String> getSentences(String text) {
		List<String> sentences = new ArrayList<>();
		int current = 0;
		int begin = 0;
		char guardWord = 0;
		for (char c : text.toCharArray()) {
			if (!PUNCTUALS.contains(c + "")) {
				if (current + 2 < text.length()) {
					guardWord = text.charAt(current + 2);
				}
			} else {
				if (PUNCTUALS.contains(guardWord + "")) {
					if (current + 2 < text.length()) {
						guardWord = text.charAt(current + 2);
					}
				} else {
					sentences.add(text.substring(begin, current + 1).trim().replaceAll(PUNCTUALS_UNUSED, ""));
					begin = current + 1;
				}
			}
			current += 1;
		}
		if (begin < text.length()) {
			sentences.add(text.substring(begin).trim().replaceAll(PUNCTUALS_UNUSED, ""));
		}

		return sentences;
	}

	/**
	 * 计算每个文本的得分
	 */
	public float getTextScore(String text) {
		float score = 0.0f;
		List<String> sentences = getSentences(text);
		for (String sentence : sentences) {
			score += getSentenceScore(sentence);
		}

		return score;
	}

	/**
	 * 计算每句话的得分
	 */
	public abstract float getSentenceScore(String sentence);

	/**
	 * 计算程度副词权值
	 */
	public abstract float sentimentLevel(String degreeWord);

	public void cleanup() {
		analyzerTool.close();
	}

}
