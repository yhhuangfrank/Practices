package com.frank.redis_study;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.TimeoutUtils;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

//@SpringBootTest
class RedisStudyApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testGuavaBloomFilter() {
		// 創建 guava bloom filter
		BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 100);
		// 判斷元素是否有可能存在
		System.out.println(bloomFilter.mightContain(1));
		System.out.println(bloomFilter.mightContain(2));

		System.out.println("將元素加入 bloomfilter");

		// 將元素加入 bloomfilter
		bloomFilter.put(1);
		bloomFilter.put(2);
		System.out.println(bloomFilter.mightContain(1));
		System.out.println(bloomFilter.mightContain(2));
	}

	@Test
	void test1() {
		long millis = TimeoutUtils.toMillis(86400L, TimeUnit.SECONDS);
		System.out.println(millis);
	}

	@Test
	void testSplit() {
		Integer[] integers = splitRedEnvelopes(100, 10);
		System.out.println(Arrays.toString(integers));
		System.out.println(Arrays.stream(integers).reduce(Integer::sum).get());
	}

	private Integer[] splitRedEnvelopes(int total, int numOfEnvelopes) {
		Integer[] res = new Integer[numOfEnvelopes];
		int current = total;
		int remained = numOfEnvelopes;
		for (int i = 0; i < numOfEnvelopes; i++) {
			if (i == numOfEnvelopes - 1) {
				res[i] = current; // 最後一個紅包不需要隨機數
				break;
			}
			int max = (current / remained) * 2; // 計算獲取上限
			int num = ThreadLocalRandom.current().nextInt(1, max + 1);
			res[i] = num;
			current -= num;
			remained--;
		}
		return res;
	}
}
