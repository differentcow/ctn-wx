package com.ctn.service;

import com.ctn.entity.query.GenericQueryParam;

import java.util.List;

public interface BaseService<T> {
	public int insert(T obj);
	
	public int insertAll(List<T> obj);
	
	public int update(T obj);
	
	public int update(T obj, GenericQueryParam param);

	public int updateAll(List<T> obj);
	
	public int delete(String id);
	
	public int delete(GenericQueryParam param);

	public T findOne(String id);
	
	public T findOne(GenericQueryParam param);
	
	public T findOne(GenericQueryParam param, String[] fields);

	public T findOne(String id, String[] fields);

	public List<T> find(GenericQueryParam param);

	public List<T> find(GenericQueryParam param, String[] fields);
	
	public List<T> findAll(GenericQueryParam param);
	
	public List<T> findAll(GenericQueryParam param, String[] fields);

	public int count(GenericQueryParam param);
	
	public T findOne(Object id);

    public int update(T obj, List<String> filter, GenericQueryParam param);

    public int update(T obj, List<String> filter);
}
