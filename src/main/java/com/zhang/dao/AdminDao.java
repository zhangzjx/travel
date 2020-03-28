package com.zhang.dao;


import com.zhang.domain.Admin;
import com.zhang.domain.User;
import com.zhang.utils.JdbcUtils;

import java.util.List;
import java.util.Map;

/**
 * @author prayers
 * @date 2020/2/18 18:52
 */
public class AdminDao {

	public static final String 管理员 = "管理员";

	/**注册*/
	public static void regist(User user){
		String sql = "insert into t_customer values(null,?,?,null,null,?,?,null,null,null)";
		Object []params={
				user.getName(),
				user.getPassword(),
				user.getPhone(),
				user.getEmail()

		};
		JdbcUtils.update(sql,params);
	}
	/** 验证账号唯一性**/
	public User validateByName(String name){
		/**
		 * 1.通过用户名查找用户是否存在
		 * 2.存在的话，给user对象赋值
		 * 3.不存在，user=null
		 */
		User user = null;
		String sql ="select * from t_customer where username=?";
		List<Map<String,Object>> list = JdbcUtils.find(sql,name);
		if(list.size()>0){
			user = new User();
			Map<String,Object> record = list.get(0);
			user.setName((String)record.get("username"));
		}
		return user;
	}
	/**登录**/
	public Admin login(String username,String password){
		Admin admin = null;
		String sql ="select * from t_admin where username=? and password=?";
		List<Map<String,Object>> list = JdbcUtils.find(sql,username,password);
		if(list.size()>0){
			admin = new Admin();
			/**登录成功后获得以下数据*/
			Map<String,Object> record = list.get(0);
			admin.setAdmin_id((Integer)record.get("admin_id"));
			admin.setAdmin_name((String)record.get("username"));
			//admin.setAdmin_password((String)record.get("password"));

		}
		return admin;

	}
	/**更新登陆时间*/
	public void updateLastLoginTime(Admin admin) {
		String sql = "update t_admin set entry_time=? where admin_id=?";
		Object []params={
				admin.getEntry_time(),
				admin.getAdmin_id()
		};
		JdbcUtils.update(sql,params);
	}
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
			user.setAdmin_name((String)record.get("username"));
			user.setAdmin_password((String)record.get("password"));
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
	/**获得个人信息**/
	public Map<String,Object> getUserInf(int uId) {
		String sql = "select * from t_customer where uId=?";
		List<Map<String, Object>> list=JdbcUtils.find(sql, uId);
		return list.get(0);
	}
	/**修改个人信息**/
	public User changeMyInf(User user) {
		String sql="update t_customer set customerName=?,sex=?,phone=?,email=? where uid=?";
		Object []params={
				user.getName(),
				user.getSex(),
				user.getPhone(),
				user.getEmail(),
				user.getUid()
		};
		JdbcUtils.update(sql, params);
		return user;
	}
	/**修改昵称**/
	public User changeNc(User user) {
		String sql="update t_customer set customerName=? where uid=?";
		Object []params={
				user.getName(),
				user.getUid()
		};
		JdbcUtils.update(sql, params);
		return user;
	}
	/** 验证账号密码**/
	public Admin validateByPw(String admin_id,String oldPassword) {

		Admin admin  = null;
		String sql ="select * from t_admin where admin_id=? and password=?";
		List<Map<String,Object>> list = JdbcUtils.find(sql,admin_id,oldPassword);
		if(list.size()>0){
			admin  = new Admin();
			Map<String,Object> record = list.get(0);
			admin.setAdmin_id((Integer)record.get("admin_id"));
		}
		return admin ;
	}
	/*****修改密码******/
	public void changePw(Admin m) {
		String sql="update t_admin set password=? where admin_id=?";
		System.out.println("dao");
		Object []params={
				m.getAdmin_password(),
				m.getAdmin_id(),
		};
		JdbcUtils.update(sql, params);
	}

	/**
	public User login(User user) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=? and user_password=?", user.getUser_code(),user.getUser_password());//查询数据库，验证用户是否存在
		if (list.size()>0) {
			//查到用户
			return list.get(0);
		} else {
			//查不到用户信息
			return null;
		}
	}
 */
}
