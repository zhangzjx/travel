package com.zhang.dao;

import com.zhang.domain.Admin;
import com.zhang.utils.JdbcUtils;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author prayers
 * @date 2020/2/18 18:52
 */
public class AdminDao {

	public static final String 管理员 = "管理员";

	/**注册*/
	public static void regist(Admin admin){
		String sql = "insert into t_customer values(null,?,?,null,null,?,?,null,null,null)";
		Object []params={
				admin.getAdmin_name(),
				admin.getAdmin_password(),
		};
		JdbcUtils.update(sql,params);
	}
	/** 验证账号唯一性**/
	public Admin validateByName(String name){
		/**
		 * 1.通过用户名查找用户是否存在
		 * 2.存在的话，给user对象赋值
		 * 3.不存在，user=null
		 */
		Admin admin = null;
		String sql ="select * from t_customer where username=?";
		List<Map<String,Object>> list = JdbcUtils.find(sql,name);
		if(list.size()>0){
			admin = new Admin();
			Map<String,Object> record = list.get(0);
			admin.setAdmin_name((String)record.get("username"));
		}
		return admin;
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
		String sql = "update t_admin set avatar=? where admin_id=?";
		Object[] params ={
				photo.getPhotoName(),
				photo.getaId(),
		};
		JdbcUtils.insert(sql, params);
	}
	/**获得个人信息**/
	public Map<String,Object> getUserInf(int admin_id) {
		String sql = "select * from t_admin where admin_id=?";
		List<Map<String, Object>> list=JdbcUtils.find(sql, admin_id);
		return list.get(0);
	}
	/**修改个人信息**/
	public Admin changeMyInf(Admin admin) {
		String sql="update t_customer set customerName=?,sex=?,phone=?,email=? where uid=?";
		Object []params={
				admin.getAdmin_name(),
				admin.getAdmin_id()
		};
		JdbcUtils.update(sql, params);
		return admin;
	}
	/**修改昵称**/
	public Admin changeNc(Admin admin) {
		String sql="update t_customer set customerName=? where uid=?";
		Object []params={
				admin.getAdmin_name(),
				admin.getAdmin_id()
		};
		JdbcUtils.update(sql, params);
		return admin;
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
	/*****查询7日内每天订单数量
	 * @return******/
	public LinkedHashMap<String, Integer> echartsCountOrderAt(int days) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
		for (int i = 0; i < days; i++){
			c.setTime(new Date());
			c.add(Calendar.DATE, - i);
			Date d = c.getTime();
			String day_value = format.format(d);
			String sql = "select count(*) from t_orderinf where ordertime like \"%"+day_value+"%\" ";
			int num =  ((Number) JdbcUtils.selectScalar(sql, (Object[]) null)).intValue();
			map.put(day_value, num);
			//System.out.println(map);
		}
		return map;
	}
	/**一个月订单总数**/
	public int monthCountOrderAt() {
		int i = 30;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, - i);
		Date d = c.getTime();
		String start_day = format.format(d);
		String sql = "SELECT count(*) FROM t_orderinf where ordertime > '"+start_day+"' ";
		//String sql = "SELECT count(*) FROM t_orderinf where ordertime Between \"%"+start_day+"%\" AND '2020-04-01' ";
		int num = ((Number) JdbcUtils.selectScalar(sql, (Object[]) null)).intValue();
		return num;
	}
	/**一周订单总数**/
	public int weekCountOrderAt() {
	 	int i = 6;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, - i);
		Date d = c.getTime();
		String start_day = format.format(d);
		String sql = "SELECT count(*) FROM t_orderinf where ordertime > '"+start_day+"' ";
		int num = ((Number) JdbcUtils.selectScalar(sql, (Object[]) null)).intValue();
		return num;
	}
	/**本日订单数**/
	public int dayCountOrderAt() {
		int i = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, - i);
		Date d = c.getTime();
		String start_day = format.format(d);
		String sql = "select count(*) from t_orderinf where ordertime > '"+start_day+"' ";
		int num = ((Number) JdbcUtils.selectScalar(sql, (Object[]) null)).intValue();
		return num;
	}
	/**本日销售额**/
	public  List<Map<String, Object>> dayCountMoneyAt() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, - 0);
		Date d = c.getTime();
		String start_day = format.format(d);
		String sql = "select totalprice,emp from t_orderinf where ordertime > '"+start_day+"' ";
		return JdbcUtils.find(sql);
	}
	/**查询昨天销售总额**/
	public  List<Map<String, Object>> lastCountMoneyAt() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, - 1);
		Date d = c.getTime();
		String start_day = format.format(d);
		Calendar e = Calendar.getInstance();
		e.setTime(new Date());
		e.add(Calendar.DATE, -0);
		Date date = e.getTime();
		String end_day = format.format(date);
		String sql = "select totalprice,emp from t_orderinf where ordertime > '"+start_day+"' and ordertime < '"+end_day+"' ";
		return JdbcUtils.find(sql);
	}
	/**近七天销售总额**/
	public  List<Map<String, Object>> weekCountMoneyAt() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, - 7);
		Date d = c.getTime();
		String start_day = format.format(d);
		String sql = "select totalprice,emp from t_orderinf where ordertime > '"+start_day+"' ";
		return JdbcUtils.find(sql);
	}
	/**获取景点评论信息**/
	/**查询所有酒店信息并分页*/
	/**搜索结果总记录数*/
	public  int findCountComment(String skey, String svalue) {
		StringBuilder sql=new StringBuilder("select count(*) from t_scenicspot_comment");
		if(skey!=null&&skey.length()>0&&svalue!=null&&svalue.length()>0){
			//'%123%'
			sql.append(" where "+skey+" like \"%"+svalue+"%\" ");
		}
		return ((Number) JdbcUtils.selectScalar(sql.toString(), (Object[]) null)).intValue();
	}
	public  List<Map<String, Object>> getAllComment(int startIndex, int pageSize,
													 String skey, String svalue) {

		StringBuilder sql=new StringBuilder("select a.*,b.spName,c.customerName,c.jf from t_scenicspot_comment a,t_scenicspot b,t_customer c" +
				" where a.user_id=c.uid and a.scenicspot_id=b.spId ");
		if(skey!=null&&skey.length()>0&&svalue!=null&&svalue.length()>0){
			sql.append(" and a."+skey+" like \"%"+svalue+"%\" limit ?,?");
		}else{
			sql.append(" limit ?,?");
		}

		return JdbcUtils.find(sql.toString(), startIndex, pageSize);
	}
	/**获得一条评论信息**/
	public Map<String, Object> getOneComment(int comment_id) {
		String sql = "select a.*,b.spName,c.customerName,c.jf from t_scenicspot_comment a,t_scenicspot b,t_customer c " +
				" where a.user_id=c.uid and a.scenicspot_id=b.spId and comment_id=?";
		List<Map<String, Object>> list=JdbcUtils.find(sql, comment_id);
		return list.get(0);
	}
	/**删除一条评论信息*/
	public void delete(int comment_id){
		String sql = "delete from t_scenicspot_comment where comment_id=?";
		JdbcUtils.update(sql,comment_id);
		//return attractions;
	}
	/**删除多条数据*/
	public  void delMore(String[] ids) {
		String sql = "delete from t_scenicspot_comment where comment_id=?";
		for (int i = 0; i < ids.length; i++) {
			System.out.println("删除数据的id为" + ids[i]);
			JdbcUtils.update(sql, ids[i]);
		}
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
