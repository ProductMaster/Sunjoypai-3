package com.taoists.common.paging;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

@Service("pagingDao")
public class PagingDao  extends JdbcDaoSupport{
	
	private final Log log = LogFactory.getLog(PagingDao.class);	
	
	public int countPaging(Paging paging){
		log.debug("Exec paging count ===> "+paging.getCountSqlClause());
		return getJdbcTemplate().queryForInt(paging.getCountSqlClause());
	}
	
	public List listPaging(Paging paging){
		log.debug("Exec paging SQL ===> "+paging.getSqlClause());
		return getJdbcTemplate().query(paging.getSqlClause(), paging.getRowMapper());
	}


	public List jdbcPaging(Paging paging){
		if(paging.getPageSize()==0){
			//检索全部数据			log.debug("Exec paging SQL ===> "+paging.getSqlClause());
			if(paging.getRowMapper() == null){
				return getJdbcTemplate().queryForList(paging.getSqlClause());
			}
			return getJdbcTemplate().query(paging.getSqlClause(), paging.getRowMapper());
		}else{
			//分页检索			log.debug("Exec paging SQL ===> "+paging.getMySQLLimit());
			if(paging.getRowMapper() == null){
				return getJdbcTemplate().queryForList(paging.getMySQLLimit());
			}
			return getJdbcTemplate().query(paging.getMySQLLimit(), paging.getRowMapper());
			
		}
	}
	
	public Object[] jdbcUniqueResult(String sql){
		Map map = getJdbcTemplate().queryForMap(sql);
		Object[] obj = new Object[map.size()];
		int i = 0;
		for(Object o : map.keySet()){
			obj[i] = map.get(o);
			i++;
		}
		return obj;
		
	}
	
	//检索全部数据
	public List jdbcList(Paging paging){
		return getJdbcTemplate().query(paging.getSqlClause(), paging.getRowMapper());
	}
}
