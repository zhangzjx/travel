package com.zhang.dao;

import com.zhang.domain.Invoice;
import com.zhang.utils.JdbcUtils;
import java.util.List;
import java.util.Map;

public class FpDao {
    public void add(Invoice user) {
        String sql = "insert into invoice values(null,?,?,?,?,?,?,?,?,null)";
        Object []params={
                user.getDDBH(),
                user.getKPLX(),
                user.getSPMC(),
                user.getFPTT(),
                user.getNsrsbh(),
                user.getSKY(),
                user.getFKFDH(),
                user.getFKFDZ(),
                //user.getTime(),
        };
        JdbcUtils.update(sql,params);
    }


    public int findCountCart() {
        String sql = "select count(*) from invoice";
        return ((Number) JdbcUtils.selectScalar(sql, (Object[]) null)).intValue();
    }
    //public static List<Map<String, Object>> findAll(int startIndex, int pageSize) {
    public static List<Map<String, Object>> findAll(int startIndex, int pageSize) {
        String sql = "select * from invoice limit ?,?";
        String sql2 = "select ddbh,fkf from invoice";
        System.out.println("--------sql执行---------");
        return JdbcUtils.find(sql, startIndex, pageSize);

        //return JdbcUtils.find(sql2);
    }

    public List<Map<String, Object>> findFp() {
        String sql = "select * from invoice";
        return JdbcUtils.find(sql);

    }



    //public Invoice findFp() {
     //   String sql = "select * from invoice ";
        /**
        List<Map<String,Object>> list = JdbcUtils.find(sql);
        if(list.size()>0){
            invoice= new Invoice();
            Map<String,Object> record = list.get(0);
        }
         */
     //   return (Invoice) JdbcUtils.find(sql);
    //}
}
