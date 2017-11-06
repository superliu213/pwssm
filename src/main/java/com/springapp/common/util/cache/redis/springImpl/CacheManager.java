package com.springapp.common.util.cache.redis.springImpl;

import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

/**
 * 继承了 spring 内置的 AbstractCacheManager 管理 RedisCache 类缓存管理
 *
 */
public class CacheManager<T extends Object> extends AbstractCacheManager {

	private Collection<? extends RedisCache> caches;

	public void setCaches(Collection<? extends RedisCache> caches) {
		this.caches = caches;
	}

	@Override
	protected Collection<? extends Cache> loadCaches() {
		return this.caches;
	}
}
