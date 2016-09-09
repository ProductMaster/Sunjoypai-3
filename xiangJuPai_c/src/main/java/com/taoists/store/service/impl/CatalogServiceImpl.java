package com.taoists.store.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.taoists.common.Const;
import com.taoists.common.orm.dao.HibernateDaoSupport;
import com.taoists.store.entity.Catalog;
import com.taoists.store.entity.StoreCase;
import com.taoists.store.service.CatalogService;


/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015年7月17日 下午5:57:11 
 * 类说明 
 */
@Service("catalogService")
public class CatalogServiceImpl extends HibernateDaoSupport<Catalog> implements CatalogService {

    // 
    public Catalog getCatalogByCatalogNo(String catalogNo) {
        
        DetachedCriteria detachedCriteria = createDetachedCriteria();
        detachedCriteria.add(Restrictions.eq("catalogNo", catalogNo.trim()));
        List<Catalog> list = detachedCriteria.getExecutableCriteria(getSession()).list();
        if(list.size()>0) return list.get(0);
        return null;
    }
    
	/**根据店铺Id获得商品列表
	 * status 商品状态 1=上架 0=下架*/
    public List<Catalog> listCatalogByStoreId(long storeId, int status){
        
        DetachedCriteria detachedCriteria = createDetachedCriteria();
        detachedCriteria.add(Restrictions.eq("storeId", storeId));
        detachedCriteria.add(Restrictions.eq("saleStatus", status));
        detachedCriteria.add(Restrictions.eq("isShow", Const.YES));
        return detachedCriteria.getExecutableCriteria(getSession()).list();        
     }
}
