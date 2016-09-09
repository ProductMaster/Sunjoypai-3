package com.taoists.common.orm.interceptor;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.joda.time.DateTime;

import com.taoists.common.orm.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-30
 */
@SuppressWarnings("serial")
public class EntityInterceptor extends EmptyInterceptor {

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames,
			Type[] types) {
		if (entity instanceof BaseEntity) {
			BaseEntity BaseEntity = (BaseEntity) entity;
			boolean modified = false;
			for (int i = 0; i < propertyNames.length; i++) {
				if ("lastModifyDate".equals(propertyNames[i])) {
					// DateTime lastModifyDateTime = new DateTime();
					long  lastModifyDateTime = System.currentTimeMillis();
					currentState[i] = lastModifyDateTime;
					BaseEntity.setLastModifyDate(lastModifyDateTime);
					modified = true;
				}
			}
			return modified;
		}
		return false;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof BaseEntity) {
			BaseEntity baseEntity = (BaseEntity) entity;
			boolean modified = false;
			for (int i = 0; i < propertyNames.length; i++) {
				if ("createDate".equals(propertyNames[i])) {
					long createDateTime = System.currentTimeMillis();
				/*	DateTime createDateTime = new DateTime();*/
					state[i] = createDateTime;
					baseEntity.setCreateDate(createDateTime);
					modified = true;
				} else if ("lastModifyDate".equals(propertyNames[i])) {
					//DateTime lastModifyDateTime = new DateTime();
					long  lastModifyDateTime = System.currentTimeMillis();
					state[i] = lastModifyDateTime;
					baseEntity.setLastModifyDate(lastModifyDateTime);
					modified = true;
				}
			}
			return modified;
		}
		return false;
	}

}
