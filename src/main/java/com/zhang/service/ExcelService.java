package com.zhang.service;

import com.zhang.dao.ExcelDao;
import com.zhang.dao.Page;
import com.zhang.domain.Invoice;

import java.util.List;
import java.util.Map;

public class ExcelService {
    ExcelDao excelDao = new ExcelDao();
    public void add(Invoice user) {
            excelDao.add(user);
    }

    public Page findAll(int currentPage) {
        int totalSize = excelDao.findCountCart();
        Page page = new Page(currentPage,totalSize);
        List<Map<String,Object>> list = excelDao.findAll(page.getStartIndex(),page.getPageSize());
        page.setList(list);
        System.out.println("service"+page.getList());
        System.out.println("页码"+page.getCurrentPage());
        return page;
    }

    public List<Map<String, Object>> findFp() {
        return excelDao.findFp();
    }

}
