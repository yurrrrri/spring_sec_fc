package com.sp.fc.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@EnableCaching
@Configuration
public class CacheConfig {
//
//    @Bean
//    public EhCacheCacheManager cacheManager() {
//        EhCacheCacheManager cacheManager = new EhCacheCacheManager();
//        cacheManager.setCacheManager(ehcacheFactoryBean().getObject());
//        return cacheManager;
//    }
//
//    @Bean
//    public EhCacheManagerFactoryBean ehcacheFactoryBean() {
//        return new EhCacheManagerFactoryBean();
//    }
}
