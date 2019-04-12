package com.whfstudio.rediscli;

import com.whfstudio.rediscli.redis.RedisClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisCliApplication implements CommandLineRunner {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RedisCliApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String HOST = System.getProperty("host", "127.0.0.1");
		Integer PORT = Integer.parseInt(System.getProperty("port", "6379"));
		RedisClient.redisCli(HOST,PORT);
	}
}
// java -jar -Dhost=172.16.15.68 -Dport=9221 redis-cli-0.0.1-SNAPSHOT.jar