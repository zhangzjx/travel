package com.zhang.domain;

import java.util.*;

public class Invoice implements List<Invoice> {

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    private Date time;
    public String getDDBH() {
        return DDBH;
    }

    public void setDDBH(String DDBH) {
        this.DDBH = DDBH;

    }

    public String getKPLX() {
        return KPLX;
    }

    public void setKPLX(String KPLX) {
        this.KPLX = KPLX;

    }

    public String getSPMC() {
        return SPMC;
    }

    public void setSPMC(String SPMC) {
        this.SPMC = SPMC;

    }

    public String getFPTT() {
        return FPTT;
    }

    public void setFPTT(String FPTT) {
        this.FPTT = FPTT;

    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getSKY() {
        return SKY;
    }

    public void setSKY(String SKY) {
        this.SKY = SKY;

    }

    public String getFKFDH() {
        return FKFDH;
    }

    public void setFKFDH(String FKFDH) {
        this.FKFDH = FKFDH;

    }

    public String getFKFDZ() {
        return FKFDZ;
    }

    public void setFKFDZ(String FKFDZ) {
        this.FKFDZ = FKFDZ;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "DDBH='" + DDBH + '\'' +
                ", KPLX='" + KPLX + '\'' +
                ", SPMC='" + SPMC + '\'' +
                ", FPTT='" + FPTT + '\'' +
                ", nsrsbh='" + nsrsbh + '\'' +
                ", SKY='" + SKY + '\'' +
                ", FKFDH='" + FKFDH + '\'' +
                ", FKFDZ='" + FKFDZ + '\'' +
                '}';
    }

    private String DDBH; //订单编号
    private String KPLX; //发票类型
    private String SPMC; //商品内容
    private String FPTT; //发票抬头
    private String nsrsbh; //纳税人识别号
    private String SKY; //收票人
    private String FKFDH; //联系电话
    private String FKFDZ; //联系地址

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Invoice> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Invoice invoice) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Invoice> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Invoice> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Invoice get(int index) {
        return null;
    }

    @Override
    public Invoice set(int index, Invoice element) {
        return null;
    }

    @Override
    public void add(int index, Invoice element) {

    }

    @Override
    public Invoice remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Invoice> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Invoice> listIterator(int index) {
        return null;
    }

    @Override
    public List<Invoice> subList(int fromIndex, int toIndex) {
        return null;
    }
}
