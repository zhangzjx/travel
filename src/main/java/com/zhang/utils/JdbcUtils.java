package com.zhang.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author prayers
 * @date 2019/10/28 18:52
 */
public class JdbcUtils {

	private static String driver="com.mysql.cj.jdbc.Driver";
	//北京时间东八区serverTimezone=GMT%2B8
	private static String url="jdbc:mysql://localhost:3306/shop?&serverTimezone=GMT%2B8&useSSL=false";
	private static String name="root";;
	private static String password="zhangjiaxin";


	//只会在类加载后执行一次的代码
	static{
		//Properties prop = new Properties();
		/*InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("Config.properties");*/
		try {

			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动没有安装");

		} catch (Exception e) {
			System.out.println("出现异常，数据库连接失败");
			throw new ExceptionInInitializerError(e);
		}

	}
	/**加载驱动，获取数据库的连接*/
	private static Connection getConnection() throws SQLException {
		//1.2获取数据库的连接
		return DriverManager.getConnection(url, name, password);
	}
	/**设置参数*/
	private static void setParams(PreparedStatement pre, Object... params)
			throws SQLException {
		if(params!=null){
			for(int i=0;i<params.length;i++){
				pre.setObject(i+1,params[i]);
			}
		}
	}
	/** 关闭数据库的连接*/
	private static void release(ResultSet rs, Statement stmt, Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	/**增操作，返回自增的主键
	 * Object ...params表示可变参数
	 */
	public static Object insert(String sql, Object...params){
		/*
		 * 1.获取数据库连接
		 * 2.访问数据库 （增删改）
		 *   2.2获取Statement对象
		 *   2.3.调用Statement中的执行方法
		 * 3.关闭数据库的连接
		 */
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		Object key = null;
		try{
			// 1.获取数据库连接
			//1.1加载驱动
			conn = getConnection();
			//2.访问数据库
			//2.1创建Statement对象
			pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParams(pre, params);
			//2.3访问数据库
			pre.executeUpdate();
			//获取自增的主键
			rs = pre.getGeneratedKeys();
			if(rs.next()){
				key = rs.getObject(1);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			release(rs,pre,conn);
		}
		return key;
	}

	/**用于返回单值操作*/
	public static <T> T selectScalar(String sql, Object...params){
		Connection conn=null;
		PreparedStatement pre=null;
		ResultSet rs=null;
		T result=null;
		try {
			conn=getConnection();
			pre=conn.prepareStatement(sql);
			setParams(pre,params);
			rs=pre.executeQuery();
			if(rs.next()){
				result=(T) rs.getObject(1);
			}
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			release(rs,pre,conn);
		}
	}
	/**增删改操作
	 * Object ...params表示可变参数
	 */
	public static void update(String sql, Object...params){

		Connection conn = null;
		PreparedStatement pre = null;
		try{
			conn = getConnection();
			pre = conn.prepareStatement(sql);
			setParams(pre, params);
			pre.executeUpdate();
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			release(null, pre, conn);
		}
	}
	/**查询数据
	 * @return*/
	public static List<Map<String, Object>> find(String sql, Object...params){

		List<Map<String, Object>> list;
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pre = conn.prepareStatement(sql);
			setParams(pre, params);
			rs = pre.executeQuery();
			//将rs转换成列表
			list = rsToList(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			release(rs, pre, conn);
		}
		return list;
	}

	/**ResultSet结果集转换成List*/
	private static List<Map<String, Object>> rsToList(ResultSet rs) throws SQLException {
		List<Map<String, Object>> list= new ArrayList<>();
		//获取表结构
		ResultSetMetaData rsmd=rs.getMetaData();
		while(rs.next()){
			Map<String, Object> map= new HashMap<>(16);
			for(int i=1;i<rsmd.getColumnCount();i++){
				//用getColumnName查出的是原本表中的字段名，
				//用getColumnLabel查出的是自己重新定义的字段名
				map.put(rsmd.getColumnName(i), rs.getObject(i));
				//map.put(rsmd.getColumnLabel(i), rs.getObject(i));
			}
			list.add(map);
		}
		return list;
	}


}
