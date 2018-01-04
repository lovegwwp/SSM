package com.jyss.mst.entity;

public class PageFooter {
  private String city;
  private int num;
  
  public String getCity() {
    return this.city;
  }
  
  public void setCity(String city) {
    this.city = city;
  }
  
  public int getNum() {
    return this.num;
  }
  
  public void setNum(int num) {
    this.num = num;
  }
  
  public PageFooter() {}
  
  public PageFooter(String city, int num)
  {
    this.city = city;
    this.num = num;
  }
}
