package com.zhang.dao;


import com.zhang.domain.Admin;
import com.zhang.utils.JdbcUtils;

import java.util.List;
import java.util.Map;

/**
 * @author prayers
 * @date 2020/2/18 18:52
 */
public class AdminDao {

	public static final String 管理员 = "管理员";


	/**通过用户名在数据库中查找User对象*/
	public Admin findByusername(String role, String username){
		/*
		 * 1.通过用户名查找用户是否存在
		 * 2.存在的话，给user对象赋值
		 * 3.不存在，user=null
		 */
		Admin user = null;
		String sql="";
		if(管理员.equals(role)){
			sql="select * from admin where username=?";
		}
		List<Map<String,Object>> list = JdbcUtils.find(sql,username);
		if(list.size()>0){
			user = new  Admin();
			Map<String,Object> record = list.get(0);
			user.setUsername((String)record.get("username"));
			user.setPassword((String)record.get("password"));
			user.setRole((String)record.get("role"));
		}
		return user;
	}
	/**添加头像信息*/
	public static void addAdminImg(Photo photo) {
		String sql = "update t_admin set avatar=? where aId=?";
		Object[] params ={

				photo.getPhotoName(),

		};
		JdbcUtils.insert(sql, params);
	}
}
