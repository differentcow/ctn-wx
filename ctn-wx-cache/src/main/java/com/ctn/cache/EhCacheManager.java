/**
 * 
 */
package com.ctn.cache;




import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author TR
 *
 */
public abstract class EhCacheManager implements CacheManage {
	@Autowired
	protected CacheManager cacheManager;
	
	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public void save(String key, Object value) {
		Cache cache = cacheManager.getCache(getCacheName());
		Element el = new Element(key, value);
		cache.put(el);
	}

	protected abstract String getCacheName();

	@Override
	public Object get(String key) {
		Cache cache = cacheManager.getCache(getCacheName());
		Element el =  cache.get(key);
		if(el!=null){
			return el.getValue();
		}
		return null;
	}

}
