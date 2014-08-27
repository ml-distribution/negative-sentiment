package zx.soft.negative.sentiment.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.negative.sentiment.utils.ReadFileUtils;

/**
 * 负面信息分类
 * 
 * @author wanggang
 *
 */
public class NegativeClassify extends ClassifySentiment {

	private static Logger logger = LoggerFactory.getLogger(NegativeClassify.class);

	public NegativeClassify() {
		super();
		logger.info("Initing negative-words Starting ...");
		// negwords
		emotionDictionary.setCatewords(ReadFileUtils.getFileToListFromResources(BASE_DIR
				+ ReadFileUtils.getFileNameByPrefix("src/main/resources/" + BASE_DIR, "negative-words")));
		logger.info("Initing negative-words Finishing ...");
	}

	@Override
	public float getSentenceScore(String sentence) {
		List<String> words = analyzerTool.analyzerTextToList(sentence);
		float negScore = 0.0f;
		float baseScore = 0.0f;
		int wordIndex = 0;
		int sentimentWordsIndex = 0;
		for (String word : words) {
			//			System.out.println(word);
			if (emotionDictionary.getCatewords().contains(word)) {
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

	@Override
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

}
