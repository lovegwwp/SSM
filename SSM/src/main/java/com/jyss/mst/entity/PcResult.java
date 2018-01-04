package com.jyss.mst.entity;

import java.util.Date;

public class PcResult {
  private int id;
  private int pId;
  private int dId;
  private String title;
  private String pcTime;
  private String pcInfo;
  private Date createdAt;
  
  public int getId() {
    return this.id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public int getpId() {
    return this.pId;
  }
  
  public void setpId(int pId) {
    this.pId = pId;
  }
  
  public int getdId() {
    return this.dId;
  }
  
  public void setdId(int dId) {
    this.dId = dId;
  }
  
  public String getTitle() {
    return this.title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getPcTime() {
    return this.pcTime;
  }
  
  public void setPcTime(String pcTime) {
    this.pcTime = pcTime;
  }
  
  public String getPcInfo() {
    return this.pcInfo;
  }
  
  public void setPcInfo(String pcInfo) {
    this.pcInfo = pcInfo;
  }
  
  public Date getCreatedAt() {
    return this.createdAt;
  }
  
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
