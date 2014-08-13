package zx.soft.negative.sentiment.core;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class NegativeSentimentTest {

	private static NegativeSentiment negativeSentiment;

	@BeforeClass
	public static void prepare() {
		negativeSentiment = new NegativeSentiment();
	}

	@AfterClass
	public static void cleanup() {
		negativeSentiment.cleanup();
	}

	@Test
	public void testGetSentences() {
		String text = "事实胜于雄辩。美方站不住脚的“搭便车论”实在荒唐...无异于，放大自己制造战乱却不思悔过的不义。。。。如果对别人的，，义举不服气，那就不要挑起战乱给别人“制造方便”嘛！";
		List<String> sentences = negativeSentiment.getSentences(text);
		assertEquals("事实胜于雄辩", sentences.get(0));
		assertEquals("美方站不住脚的“搭便车论”实在荒唐", sentences.get(1));
		assertEquals("无异于", sentences.get(2));
		assertEquals("放大自己制造战乱却不思悔过的不义", sentences.get(3));
		assertEquals("如果对别人的", sentences.get(4));
		assertEquals("义举不服气", sentences.get(5));
		assertEquals("那就不要挑起战乱给别人“制造方便”嘛！", sentences.get(6));
	}

}
