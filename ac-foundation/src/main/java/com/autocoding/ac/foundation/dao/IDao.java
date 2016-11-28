package com.autocoding.ac.foundation.dao;

import com.autocoding.ac.foundation.data.ACPageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public abstract interface IDao<Data> {

	public abstract boolean save(Data paramData);

	public abstract boolean batchAdd(List<Data> paramList);

	public abstract boolean update(Data paramData);

	public abstract boolean updateKV(Map<String, Object> paramMap, Object[] paramArrayOfObject);

	public abstract boolean batchUpdateKV(List<Map<String, Object>> paramList, List<Object[]> paramList1);

	public abstract boolean batchUpdate(List<Data> paramList);

	public abstract boolean delete(Object[] paramArrayOfObject);

	public abstract boolean batchDelete(List<Object[]> paramList);

	public abstract boolean isExist(Object[] paramArrayOfObject);

	//public abstract boolean isUsable(Object[] paramArrayOfObject);

	public abstract Data getSingle(Object[] paramArrayOfObject);

	public abstract List<Data> getAll();

	public abstract List<Data> getAll(Map<String, Object> keyMap);

	public abstract int getAllCount();

	public abstract List<Data> getAll(ACPageInfo paramCIPPageInfo);

	public abstract List<Data> getByCondition(Object[] paramArrayOfObject);

	public abstract List<Data> getByCondition(Map<String, Object> paramMap);

	public abstract int getByConditionCount(Object[] paramArrayOfObject);

	public abstract List<Data> getByCondition(ACPageInfo paramCIPPageInfo, Object[] paramArrayOfObject);
}
