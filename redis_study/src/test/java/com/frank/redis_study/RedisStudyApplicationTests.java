package com.frank.redis_study;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

}
