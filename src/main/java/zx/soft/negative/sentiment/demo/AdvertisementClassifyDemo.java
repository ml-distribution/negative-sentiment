package zx.soft.negative.sentiment.demo;

import zx.soft.negative.sentiment.core.AdvertisementClassify;

public class AdvertisementClassifyDemo {

	public static void main(String[] args) {

		AdvertisementClassify advClassify = new AdvertisementClassify();
		String text = "2014雪花勇闯天涯“挑战未登峰”选拔赛现正全国火热进行中，雪花啤酒邀您共赴征程。以“挑战未登峰”为主题的2014勇闯天涯活动"
				+ "，将要对从来无人登顶的山峰进行挑战性开拓性的攀登！勇闯从未止步，勇闯因你而非凡！征程即刻开启，活动详情可关注@雪花啤酒勇闯" //
				+ "天涯。 |雪花勇闯天涯挑战未登峰邀您共赴征程！";
		System.out.println(advClassify.getTextScore(text));
		advClassify.cleanup();

	}

}
