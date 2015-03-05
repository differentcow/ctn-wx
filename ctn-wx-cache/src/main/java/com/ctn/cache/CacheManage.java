package com.ctn.cache;

public interface CacheManage {
	void save(String key,Object value);
	Object get(String key);
}
