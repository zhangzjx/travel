package com.zhang.dao;

/**
 * @author prayers
 * @date 2019/11/4 13:01
 */
import java.util.List;
import java.util.Map;

public class PageTwo {
    /**每页要显示的条目数*/
    private int pageSize=2;
    /**条目总数，从数据库中计算得SELECT COUNT(*) FROM category*/
    private int totalSize;
    /**当前页，默认为1，由页面传递*/
    private int currentPage;
    /**总共有多少页，(totalSize%pageSize == 0)?(totalSize/pageSize):(totalSize/pageSize+1)*/
    private int totalPage;
    /**存放找到的结果集*/
    private List<Map<String,Object>> list;
    /**要读取记录的起始值,(current-1)*pageSize*/
    private int startIndex;
    private int num=6;
    /**页码列表开始位置，start = current-num/2+1*/
    private int start;
    /**页码列表结束位置，end = start + num - 1*/
    private int end;

    public PageTwo(int currentPage, int totalSize){
        this.totalSize=totalSize;
        //设置当前页
        setCurrentPage(currentPage);
    }

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public void setCurrentPage(int currentPage) {
        if(currentPage<1){
            currentPage=1;
        }
        int totalPage=getTotalPage();
        if(currentPage>totalPage){
            currentPage=totalPage;
        }
        this.currentPage = currentPage;
    }
    public int getCurrentPage() {

        return currentPage;
    }

    public List<Map<String,Object>> getList() {
        return list;
    }
    public void setList(List<Map<String,Object>> list) {
        this.list = list;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public int getTotalSize() {
        return totalSize;
    }
    public int getTotalPage() {
        return (totalSize%pageSize == 0)?(totalSize/pageSize):(totalSize/pageSize+1);
    }
    public int getStartIndex() {
        int startIndex=(currentPage-1)*pageSize;
        if(startIndex<0){
            startIndex=0;
        }
        return startIndex;
    }
    public int getStart() {
        int start=currentPage-num/2+1;
        if(start<1){
            start=1;
        }
        return start;
    }
    public int getEnd() {
        int end=getStart()+num-1;
        int totalPage=getTotalPage();
        if(end>totalPage){
            end=totalPage;
        }
        return end;
    }

}

