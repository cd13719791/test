package com.moyou.moyouRms.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import com.moyou.moyouRms.util.ReflectUtil;

/**
 * @ClassName: MySqlDataInterceptor
 * @Description: 分页拦截器
 * @author zkd
 *
 */
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
@SuppressWarnings("rawtypes")
public class MySqlDataInterceptor implements Interceptor {
	/**
	 * 拦截后要执行的方法
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler handler = (RoutingStatementHandler) invocation
				.getTarget();
		StatementHandler delegate = (StatementHandler) ReflectUtil
				.getFieldValue(handler, "delegate");
		BoundSql boundSql = delegate.getBoundSql();
		Object params = boundSql.getParameterObject();

		// 这里我们简单的通过传入的是Page对象就认定它是需要进行分页操作的。
		PageBean page = null;
		if (params instanceof PageBean) {
			page = (PageBean) params;
		} else if (params instanceof MapperMethod.ParamMap) {
			MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) params;
			for (Object key : paramMap.keySet()) {
				if (paramMap.get(key) instanceof PageBean) {
					page = (PageBean) paramMap.get(key);
					break;
				}
			}
		}
		if (page != null) {
			// 通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
			MappedStatement mappedStatement = (MappedStatement) ReflectUtil
					.getFieldValue(delegate, "mappedStatement");
			// 拦截到的prepare方法参数是一个Connection对象
			Connection connection = (Connection) invocation.getArgs()[0];
			// 获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句
			String sql = boundSql.getSql();
			// 给当前的page参数对象设置总记录数
			this.setTotalRecord(page, params, mappedStatement, connection);
			// 获取分页Sql语句
			String pageSql = this.getPageSql(page, sql);
			// 利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
			ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
		}
		return invocation.proceed();
	}

	/**
	 * 给当前的参数对象page设置总记录数
	 * 
	 * @param page
	 *            Mapper映射语句对应的参数对象
	 * @param mappedStatement
	 *            Mapper映射语句
	 * @param connection
	 *            当前的数据库连接
	 */
	private void setTotalRecord(PageBean page, Object params,
			MappedStatement mappedStatement, Connection connection) {
		// 获取对应的BoundSql，这个BoundSql其实跟我们利用StatementHandler获取到的BoundSql是同一个对象。
		// delegate里面的boundSql也是通过mappedStatement.getBoundSql(paramObj)方法获取到的。
		BoundSql boundSql = mappedStatement.getBoundSql(params);
		// 获取到我们自己写在Mapper映射语句中对应的Sql语句
		String sql = boundSql.getSql();
		// 通过查询Sql语句获取到对应的计算总记录数的sql语句
		String countSql = this.getCountSql(sql);
		// 通过BoundSql获取对应的参数映射
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		// 利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。
		BoundSql countBoundSql = new BoundSql(
				mappedStatement.getConfiguration(), countSql,
				parameterMappings, params);
		// 通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象
		ParameterHandler parameterHandler = new DefaultParameterHandler(
				mappedStatement, params, countBoundSql);
		// 通过connection建立一个countSql对应的PreparedStatement对象。
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(countSql);
			// 通过parameterHandler给PreparedStatement对象设置参数
			parameterHandler.setParameters(pstmt);
			// 之后就是执行获取总记录数的Sql语句和获取结果了。
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int totalRecord = rs.getInt(1);
				// 给当前的参数page对象设置总记录数
				page.setTotalRecord(totalRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 拦截器对应的封装原始对象的方法
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * 设置注册拦截器时设定的属性
	 */
	public void setProperties(Properties properties) {
	}

	/**
	 * 根据page对象获取对应的分页查询Sql语句，这里只做了两种数据库类型，Mysql和Oracle 其它的数据库都 没有进行分页
	 *
	 * @param page
	 *            分页对象
	 * @param sql
	 *            原sql语句
	 * @return
	 */
	private String getPageSql(PageBean page, String sql) {
		StringBuffer sqlBuffer = new StringBuffer(sql);
		return getMysqlPageSql(page, sqlBuffer);
	}

	/**
	 * 获取Mysql数据库的分页查询语句
	 * 
	 * @param page
	 *            分页对象
	 * @param sqlBuffer
	 *            包含原sql语句的StringBuffer对象
	 * @return Mysql数据库分页语句
	 */
	private String getMysqlPageSql(PageBean page, StringBuffer sqlBuffer) {
		// 计算第一条记录的位置，Mysql中记录的位置是从0开始的。
		int offset = (page.getPageNumber() - 1) * page.getPageSize();
		sqlBuffer.append(" LIMIT ").append(offset).append(",")
				.append(page.getPageSize());
		return sqlBuffer.toString();
	}

	/**
	 * 根据原Sql语句获取对应的查询总记录数的Sql语句
	 * 
	 * @param sql
	 * @return
	 */
	private String getCountSql(String sql) {
		return "select count(*) from (" + sql + ") as countRecord";
	}

	/**
	 * 给当前的参数对象page设置总记录数
	 *
	 * @param page
	 *            Mapper映射语句对应的参数对象
	 * @param mappedStatement
	 *            Mapper映射语句
	 * @param connection
	 *            当前的数据库连接
	 */
	// private void setTotalRecord(PageBean page, MappedStatement
	// mappedStatement, BoundSql boundSql,
	// Connection connection) {
	// String sql = boundSql.getSql();
	// String countSql = this.getCountSql(sql);
	// List<ParameterMapping> parameterMappings =
	// boundSql.getParameterMappings();
	// BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(),
	// countSql, parameterMappings, page);
	// ParameterHandler parameterHandler = new
	// DefaultParameterHandler(mappedStatement, page, countBoundSql);
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// try {
	// pstmt = connection.prepareStatement(countSql);
	// parameterHandler.setParameters(pstmt);
	// rs = pstmt.executeQuery();
	// if (rs.next()) {
	// int totalRecord = rs.getInt(1);
	// page.setTotal(totalRecord);
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// if (rs != null)
	// rs.close();
	// if (pstmt != null)
	// pstmt.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	//
	// /**
	// * 根据原Sql语句获取对应的查询总记录数的Sql语句
	// *
	// * @param sql
	// * @return
	// */
	// private String getCountSql(String sql) {
	// return "select count(*) from(" + sql + ") e";
	// }
}
