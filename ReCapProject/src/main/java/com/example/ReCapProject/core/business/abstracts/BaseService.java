package com.example.ReCapProject.core.business.abstracts;

import com.example.ReCapProject.core.utilities.results.Result;

public interface BaseService<T, id> {
	Result add(T entity);
	Result update(T entity);
	Result delete(id entity);
}
