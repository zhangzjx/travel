package com.zhang.service;

import com.zhang.dao.FpDao;
import com.zhang.dao.Page;
import com.zhang.domain.Invoice;

import java.util.List;
import java.util.Map;

public class FpService {
    FpDao fpDao = new FpDao();
    public void add(Invoice user) {
            fpDao.add(user);
    }

    public Page findAll(int currentPage) {
        int totalSize = fpDao.findCountCart();
        Page page = new Page(currentPage,totalSize);
        List<Map<String,Object>> list = fpDao.findAll(page.getStartIndex(),page.getPageSize());
        page.setList(list);
        System.out.println("service"+page.getList());
        System.out.println("页码"+page.getCurrentPage());
        return page;
    }

    public List<Map<String, Object>> findFp() {
        return fpDao.findFp();
    }

}
