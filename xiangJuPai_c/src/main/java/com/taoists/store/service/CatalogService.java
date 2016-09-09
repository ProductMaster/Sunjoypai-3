package com.taoists.store.service;

import java.util.List;

import com.taoists.common.orm.dao.BaseDao;
import com.taoists.store.entity.Catalog;


/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015年7月17日 下午5:55:38 
 * 类说明 
 */
public interface CatalogService extends BaseDao<Catalog> {
    //
    public Catalog getCatalogByCatalogNo(String catalogNo);
	/**根据店铺Id获得商品列表
	 * status 商品状态 1=上架 0=下架*/
    public List<Catalog> listCatalogByStoreId(long storeId, int status);
}
