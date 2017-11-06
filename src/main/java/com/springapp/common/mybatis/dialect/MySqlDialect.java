/**
 * wusc.edu.pay.common.core.mybatis.dialect.MySqlDialect.java
 */
package com.springapp.common.mybatis.dialect;

/**
 * 
 * 
 * <ul>
 * <li>Title: MySQL数据库分页适配器</li>
 * </ul>
 * 
 */
public class MySqlDialect extends Dialect {
	public boolean supportsLimitOffset() {
		return true;
	}

	public boolean supportsLimit() {
		return true;
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {

		if (offset > 0) {
			sql += " limit " + offsetPlaceholder + "," + limitPlaceholder;
		} else {
			sql += " limit " + limitPlaceholder;
		}

		return sql;
	}
}
