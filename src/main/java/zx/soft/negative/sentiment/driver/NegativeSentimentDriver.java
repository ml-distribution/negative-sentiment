package zx.soft.negative.sentiment.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.negative.sentiment.web.TextScoreServer;

/**
 * 驱动类
 * 
 * @author wanggang
 *
 */
public class NegativeSentimentDriver {

	private static Logger logger = LoggerFactory.getLogger(NegativeSentimentDriver.class);

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		if (args.length == 0) {
			System.err.println("Usage: Driver <class-name>");
			System.exit(-1);
		}
		String[] leftArgs = new String[args.length - 1];
		System.arraycopy(args, 1, leftArgs, 0, leftArgs.length);

		switch (args[0]) {
		case "textScoreServer":
			logger.info("與请信息评分接口接口： ");
			TextScoreServer.main(leftArgs);
			break;
		default:
			return;
		}

	}

}
