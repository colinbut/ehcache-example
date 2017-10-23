/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;


public class EhcacheExample {

    public static void main(String[] args) {

        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
            .withCache("preConfigured",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(100))
            .build()).build(true);

        Cache<Long, String> preConfigured = cacheManager.getCache("preConfigured", Long.class, String.class);

        Cache<Long, String> myCache = cacheManager.createCache("myCache",
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(100)).build());

        long key = 1L;
        myCache.put(key, "da One");

        String value = myCache.get(1L);

        System.out.println("The value of " + key + " is " + value);

        cacheManager.close();

    }
}
