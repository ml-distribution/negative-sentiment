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

	@Test
	public void testGetTextScore() {
		String text = "四川在线—绵阳频道讯 （周兰兰 记者 付江）8月7日，绵阳涪城区法院对5名“90后”被告人犯抢劫罪一案进行了公开宣判。"
				+ "5名被告人分别被处以有期徒刑10至13年，并处罚金1万元至1.5万元不等，剥夺政治权利1至2年。该案的处理秉承了该院对于刑事"
				+ "暴力犯罪案件长期以来的严打高压态势。经审理查明，被告人小平、小涛、小春、小虎、小洋5人经事先预谋，在2013年12月24日"
				+ "至2014年1月1日不到10天的时间内，驾驶一辆红色“英伦”轿车，先后7次在绵阳城区、江油、三台、成都等地采取蒙面、持刀拦路"
				+ "、语言威胁、搜身、殴打等方式，抢劫过路行人手机、钱包等贵重物品。2014年1月10日，被告人小平、小涛被公安机关抓获归案"
				+ "，小涛在被公安机关抓获后协助公安机关抓获同案被告人小虎、小春。2014年1月11日，被告人小洋被公安机关抓获归案。审理期"
				+ "间，5被告亲属分别代被告人退赔赃款及赔偿款人民币各1万元。并与受害人之一雷某达成民事赔偿协议并已履行，取得被害人谅解"
				+ "。法院认为，五名被告以非法占有为目的，采取暴力、胁迫方式，强行劫取公私财物，其行为触犯了国家刑律，均已构成抢劫罪，"
				+ "且属“多次抢劫”。应依法惩处。被告人小涛协助公安机关抓获同案被告人，属立功，可以从轻或者减轻处罚。五名被告当庭自愿认"
				+ "罪，且积极退赃款，并主动赔偿给被害人造成的损失，取得被害人谅解，可酌情从轻处罚。最终，依照《中华人民共和国刑法》第"
				+ "二百六十三条“以暴力、胁迫或者其他方法抢劫公私财物的，处三年以上十年以下有期徒刑，并处罚金；有‘多次抢劫或者抢劫数额"
				+ "巨大的’等情形之一的，处十年以上有期徒刑、无期徒刑或者死刑，并处罚金或者没收财产”之规定，法院作出前诉判决。据该案的"
				+ "主审法官介绍，该案的5名被告文化程度都不高，均是小学文化，从小未受到良好的教育，加之好逸恶劳，梦想不劳而获，几个同龄"
				+ "人一拍即合，才会犯下这样的罪行。不仅给受害人身心造成了严重伤害，也让自己的家人伤心、失望，庭审中，5名当事人都表示追"
				+ "悔莫及。奉劝那些期望通过“特殊途径”致富的人及时悬崖勒马，勤劳致富方能长久。";
		assertEquals(81.0f, negativeSentiment.getTextScore(text), 0.0f);
	}

}
