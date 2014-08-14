package zx.soft.negative.sentiment.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.negative.sentiment.analyzer.AnalyzerTool;
import zx.soft.negative.sentiment.domain.EmotionDictionary;
import zx.soft.negative.sentiment.utils.ReadFileUtils;

public class NegativeSentiment {

	private static Logger logger = LoggerFactory.getLogger(NegativeSentiment.class);

	private final EmotionDictionary emotionDictionary;

	private static final String BASE_DIR = "emotion_dict/";

	private static final String PUNCTUALS = ",.!?;…~……，。！？；...～...... ";
	private static final String PUNCTUALS_UNUSED = "[,.?;…~……，。？；...～...... ]";

	// 注意：分词器要使用完整的分词器，不要去除停用词。
	private final AnalyzerTool analyzerTool;

	public NegativeSentiment() {
		logger.info("Initing dicts Starting ...");
		analyzerTool = new AnalyzerTool();
		emotionDictionary = new EmotionDictionary();
		init();
		logger.info("Initing dicts Finishing ...");
	}

	/**
	 * 初始化
	 */
	private void init() {
		// insufficiently
		emotionDictionary.setInsufficiently(ReadFileUtils.getFileToList(BASE_DIR + "insufficiently"));
		// inverse
		emotionDictionary.setInverse(ReadFileUtils.getFileToList(BASE_DIR + "inverse"));
		// ish
		emotionDictionary.setIsh(ReadFileUtils.getFileToList(BASE_DIR + "ish"));
		// more
		emotionDictionary.setMore(ReadFileUtils.getFileToList(BASE_DIR + "more"));
		// most
		emotionDictionary.setMost(ReadFileUtils.getFileToList(BASE_DIR + "most"));
		// negwords
		emotionDictionary.setNegwords(ReadFileUtils.getFileToList(BASE_DIR + "negwords"));
		// over
		emotionDictionary.setOver(ReadFileUtils.getFileToList(BASE_DIR + "over"));
		// very
		emotionDictionary.setVery(ReadFileUtils.getFileToList(BASE_DIR + "very"));
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
	public float getSentenceScore(String sentence) {
		List<String> words = analyzerTool.analyzerTextToList(sentence);
		float negScore = 0.0f;
		float baseScore = 0.0f;
		int wordIndex = 0;
		int sentimentWordsIndex = 0;
		for (String word : words) {
			//			System.out.println(word);
			if (emotionDictionary.getNegwords().contains(word)) {
				//				System.out.println("negword: " + word);
				baseScore = 1;
				for (int i = sentimentWordsIndex; i <= wordIndex; i++) {
					baseScore *= sentimentLevel(words.get(i));
				}
				if (Float.compare(baseScore, 0.0f) == 1) {
					negScore += baseScore;
				}
				sentimentWordsIndex = wordIndex + 1;
			} else if ((word.equals("!") || word.equals("！"))) {
				if (Float.compare(negScore, 0.0f) == 1) {
					negScore += 2;
				}
			}
			wordIndex++;
		}

		return negScore;
	}

	/**
	 * 计算程度副词权值
	 */
	public float sentimentLevel(String degreeWord) {
		if (emotionDictionary.getMost().contains(degreeWord)) {
			return 2.0f;
		} else if (emotionDictionary.getVery().contains(degreeWord)) {
			return 1.5f;
		} else if (emotionDictionary.getMore().contains(degreeWord)) {
			return 1.25f;
		} else if (emotionDictionary.getIsh().contains(degreeWord)) {
			return 0.5f;
		} else if (emotionDictionary.getInsufficiently().contains(degreeWord)) {
			return 0.25f;
		} else if (emotionDictionary.getInverse().contains(degreeWord)) {
			return -1f;
		} else {
			return 1f;
		}
	}

	public void cleanup() {
		analyzerTool.close();
	}

}
