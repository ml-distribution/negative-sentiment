package zx.soft.negative.sentiment.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zx.soft.negative.sentiment.web.TextScoreServer;
import zx.soft.utils.driver.ProgramDriver;

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

		int exitCode = -1;
		ProgramDriver pgd = new ProgramDriver();
		try {
			pgd.addClass("textScoreServer", TextScoreServer.class, "與请信息评分接口");
			pgd.driver(args);
			// Success
			exitCode = 0;
		} catch (Throwable e) {
			logger.error("Exception:{}, StackTrace:{}", e.getMessage(), e.getStackTrace());
			throw new RuntimeException(e);
		}
		System.exit(exitCode);
	}

}
