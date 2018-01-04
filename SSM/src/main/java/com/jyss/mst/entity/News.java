package com.jyss.mst.entity;

import org.springframework.web.multipart.MultipartFile;

public class News
{
  private int id;
  private String titles;
  private String pics;
  private String pics1;
  private long byteSize;
  private MultipartFile myFile;
  private String contents;
  private int type;
  private java.util.Date createdAt;
  private java.util.Date lastModifyTime;
  private String cjsj;
  private String xgsj;
  
  public int getId()
  {
    return this.id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getPics1() {
    return this.pics1;
  }
  
  public void setPics1(String pics1) {
    this.pics1 = pics1;
  }
  
  public String getTitles() {
    return this.titles;
  }
  
  public void setTitles(String titles) {
    this.titles = titles;
  }
  
  public String getPics() {
    return this.pics;
  }
  
  public void setPics(String pics) {
    this.pics = pics;
  }
  
  public long getByteSize() {
    return this.byteSize;
  }
  
  public void setByteSize(long byteSize) {
    this.byteSize = byteSize;
  }
  
  public MultipartFile getMyFile() {
    return this.myFile;
  }
  
  public void setMyFile(MultipartFile myFile) {
    this.myFile = myFile;
  }
  
  public String getContents() {
    return this.contents;
  }
  
  public void setContents(String contents) {
    this.contents = contents;
  }
  
  public int getType() {
    return this.type;
  }
  
  public void setType(int type) {
    this.type = type;
  }
  
  public java.util.Date getCreatedAt() {
    return this.createdAt;
  }
  
  public void setCreatedAt(java.util.Date createdAt) {
    this.createdAt = createdAt;
  }
  
  public java.util.Date getLastModifyTime() {
    return this.lastModifyTime;
  }
  
  public void setLastModifyTime(java.util.Date lastModifyTime) {
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
