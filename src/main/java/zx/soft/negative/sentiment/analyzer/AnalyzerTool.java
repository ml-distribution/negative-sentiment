package zx.soft.negative.sentiment.analyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.pp.analyzer.ik.lucene.IKAnalyzer;

/**
 * 分词工具类
 * 
 * @author wanggang
 *
 */
public class AnalyzerTool {

	private static Logger logger = LoggerFactory.getLogger(AnalyzerTool.class);

	private final Analyzer analyzer;

	public AnalyzerTool() {
		analyzer = new IKAnalyzer(Version.LUCENE_48, true);
	}

	public HashMap<String, Integer> getWordAndCounts(String text) {
		HashMap<String, Integer> result = new HashMap<>();
		List<String> words = analyzerTextToList(text);
		for (String word : words) {
			if (result.get(word) == null) {
				result.put(word, 1);
			} else {
				result.put(word, result.get(word) + 1);
			}
		}
		return result;
	}

	/**
	 * 中文分词->获取文本的关键字向量
	 * @param text: 待分词文本
	 * @param splitToken：分割符
	 */
	public String analyzerTextToStr(String text, String splitToken) {
		StringBuffer result = new StringBuffer();
		List<String> temp = analyzerTextToList(text);
		for (String str : temp) {
			result.append(str).append(splitToken);
		}
		if (result.lastIndexOf(splitToken) > 0) {
			return result.toString().substring(0, result.lastIndexOf(splitToken));
		} else {
			return "";
		}
	}

	public String[] analyzerTextToArr(String text) {
		List<String> result = analyzerTextToList(text);
		return result.toArray(new String[result.size()]);
	}

	public List<String> analyzerTextToList(String text) {
		List<String> result = new ArrayList<>();
		try (TokenStream tokenStream = analyzer.tokenStream(null, new StringReader(text))) {
			CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
			tokenStream.reset();
			while (tokenStream.incrementToken()) {
				result.add(charTermAttribute.toString());
			}
			tokenStream.end();
		} catch (IOException e) {
			logger.error("IOException in AnalyzerTool: " + e);
			throw new RuntimeException(e);
		}
		return result;
	}

	public void close() {
		if (analyzer != null) {
			analyzer.close();
		}
	}

}
