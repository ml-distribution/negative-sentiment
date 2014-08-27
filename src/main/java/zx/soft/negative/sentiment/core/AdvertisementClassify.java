package zx.soft.negative.sentiment.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.negative.sentiment.utils.ReadFileUtils;

public class AdvertisementClassify extends ClassifySentiment {

	private static Logger logger = LoggerFactory.getLogger(AdvertisementClassify.class);

	public AdvertisementClassify() {
		super();
		logger.info("Initing negative-words Starting ...");
		// advertisement
		emotionDictionary.setCatewords(ReadFileUtils.getFileToList(BASE_DIR
				+ ReadFileUtils.getFileNameByPrefix(BASE_DIR, "advertisement-words")));
		logger.info("Initing negative-words Finishing ...");
	}

	@Override
	public float getSentenceScore(String sentence) {
		List<String> words = analyzerTool.analyzerTextToList(sentence);
		float advScore = 0.0f;
		float baseScore = 0.0f;
		int wordIndex = 0;
		int sentimentWordsIndex = 0;
		for (String word : words) {
			//			System.out.println(word);
			if (emotionDictionary.getCatewords().contains(word)) {
				//				System.out.println("advertisement: " + word);
				baseScore = 1;
				for (int i = sentimentWordsIndex; i <= wordIndex; i++) {
					baseScore *= sentimentLevel(words.get(i));
				}
				if (Float.compare(baseScore, 0.0f) == 1) {
					advScore += baseScore;
				}
				sentimentWordsIndex = wordIndex + 1;
			} else if ((word.equals("!") || word.equals("！"))) {
				if (Float.compare(advScore, 0.0f) == 1) {
					advScore += 2;
				}
			}
			wordIndex++;
		}

		return advScore;
	}

	/**
	 * 这里不考虑程度副词
	 */
	@Override
	public float sentimentLevel(String degreeWord) {
		return 1;
	}

}
