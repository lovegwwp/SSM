package com.jyss.mst.entity;

import java.util.Date;

public class GoodCategory {
  private int id;
  private int fid;
  private String name;
  private int status;
  private String picture;
  private Date createdAt;
  private Date lastModifyTime;
  private String cjsj;
  private String xgsj;
  
  public int getId() {
    return this.id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public int getFid() {
    return this.fid;
  }
  
  public void setFid(int fid) {
    this.fid = fid;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getStatus() {
    return this.status;
  }
  
  public void setStatus(int status) {
    this.status = status;
  }
  
  public String getPicture() {
    return this.picture;
  }
  
  public void setPicture(String picture) {
    this.picture = picture;
  }
  
  public Date getCreatedAt() {
    return this.createdAt;
  }
  
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
  
  public Date getLastModifyTime() {
    return this.lastModifyTime;
  }
  
  public void setLastModifyTime(Date lastModifyTime) {
    this.lastModifyTime = lastModifyTime;
  }
  
  public String getCjsj() {
    return this.cjsj;
  }
  
  public void setCjsj(String cjsj) {
    this.cjsj = cjsj;
  }
  
  public String getXgsj() {
    return this.xgsj;
  }
  
  public void setXgsj(String xgsj) {
    this.xgsj = xgsj;
  }
}
