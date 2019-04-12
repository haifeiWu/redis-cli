package com.whfstudio.rediscli;

import com.taobao.middleware.cli.CLI;
import com.taobao.middleware.cli.CommandLine;
import com.taobao.middleware.cli.UsageMessageFormatter;
import com.taobao.middleware.cli.annotations.CLIConfigurator;
import com.whfstudio.rediscli.redis.RedisClient;
import com.whfstudio.rediscli.util.UsageRender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class RedisCliApplication implements CommandLineRunner {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RedisCliApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		CLI cli = CLIConfigurator.define(RedisCliApplication.class);
		CommandLine commandLine = cli.parse(Arrays.asList(args));

		try {
			CLIConfigurator.inject(commandLine, RedisCliApplication.class);
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println(usage(cli));
			System.exit(1);
		}
		String HOST = System.getProperty("host", "127.0.0.1");
		Integer PORT = Integer.parseInt(System.getProperty("port", "6379"));
		RedisClient.redisCli(HOST,PORT);
	}

	private static String usage(CLI cli) {
		StringBuilder usageStringBuilder = new StringBuilder();
		UsageMessageFormatter usageMessageFormatter = new UsageMessageFormatter();
		usageMessageFormatter.setOptionComparator(null);
		cli.usage(usageStringBuilder, usageMessageFormatter);
		return UsageRender.render(usageStringBuilder.toString());
	}
}