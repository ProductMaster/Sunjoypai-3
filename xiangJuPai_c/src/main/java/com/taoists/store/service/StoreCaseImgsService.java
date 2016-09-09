package com.taoists.store.service;

import java.util.List;

import com.taoists.common.orm.dao.BaseDao;
import com.taoists.store.entity.StoreCaseImgs;

public interface StoreCaseImgsService extends BaseDao<StoreCaseImgs>{

	//根据caseId获取图片列表
	public List<StoreCaseImgs> findByCaseId(long caseId);
}
