package com.casestudy.EmployeeConsumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizePolicy;

@Configuration
public class CacheConfiguration {

	 @Bean
	public Config configure() {
		return new Config().setInstanceName("hazel-instance")
				.addMapConfig(new MapConfig().setName("employee-cache").setTimeToLiveSeconds(2000)
						.setEvictionConfig(new EvictionConfig().setSize(200)
								.setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE).setEvictionPolicy(EvictionPolicy.LRU)));
	}
}
