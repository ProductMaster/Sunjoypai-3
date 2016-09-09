package com.taoists.common.orm.dao;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("jdbcGenericDao")
@SuppressWarnings({"rawtypes", "unchecked"})
public class JdbcGenericDao extends JdbcDaoSupport {
	private final static Log log = LogFactory.getLog(JdbcGenericDao.class);
	
	public List queryForList(String sql){
		return this.getJdbcTemplate().queryForList(sql);
	}
	public Map queryForMap(String sql){
		return this.getJdbcTemplate().queryForMap(sql);
	}
	public int queryForInt(String sql){
		return this.getJdbcTemplate().queryForInt(sql);
	}
	public long queryForLong(String sql){
		return this.getJdbcTemplate().queryForLong(sql);
	}
	public Object queryForString(String sql){
		return this.getJdbcTemplate().queryForObject(sql, String.class);
	}
	
	/**普通查询**/
	public List query(String sql ,RowMapper rowMapper){
		return this.getJdbcTemplate().query(sql, rowMapper);
	}
	/**
	 * 分页查询函数，使用sql.
	 *
	 * @param pageNo 页号
	 */
	public List pagingQuery(String sql, int pageNo, int pageSize, RowMapper rowMapper ) {
				
		//实际查询返回分页对象
		 sql  = sql +" limit "+pageSize+" offset "+ pageSize * (pageNo-1);
		 List list = getJdbcTemplate().query(sql, rowMapper);
		 return list;
	}
	/**得到分页sql**/
	public String getPagingSql(String sql,int pageNo,int pageSize){
		return  sql +" limit "+pageSize+" offset "+ pageSize * (pageNo-1);
		
	}
	
	/**查询有几条数据**/
	public int pagingCount(String sql) {
		String countQueryString = " select count (*) " + removeSelect(removeOrders(removeGroups(sql)));
		log.debug("IN jdbcGenericDao ---> countQueryString:"+countQueryString);
		int count = getJdbcTemplate().queryForInt(countQueryString);
		return count;		
	}
	/**
	 * 去除sql的select 子句，未考虑union情况,，用于pagedQuery.
	 */
	public static String removeSelect(String sql) {
		Assert.hasText(sql);
		int beginPos = sql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + sql + " must has a keyword 'from'");
		return sql.substring(beginPos);
	}

	/**
	 * 去除sql的orderby 子句，用于pagedQuery.
	 */
	public static String removeOrders(String sql) {
		Assert.hasText(sql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	/**
	 * 去除sql的groupby 子句，用于pagedQuery.
	 */
	public static String removeGroups(String sql) {
		Assert.hasText(sql);
		Pattern p = Pattern.compile("group\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	@Resource(name = "dataSource")
	public void setSuperDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	

}
