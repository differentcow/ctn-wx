package com.ctn.entity.query;

import java.util.Map;

public interface QueryParam extends Map<QueryKey, Object> {
	QueryParam fill(QueryKey key, Object value);
}
