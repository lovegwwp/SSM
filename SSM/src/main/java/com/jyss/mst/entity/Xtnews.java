package com.jyss.mst.entity;

import org.springframework.web.multipart.MultipartFile;

public class Xtnews
{
  private int id;
  private String title;
  private String pic;
  private long byteSize;
  private MultipartFile myFile;
  private String contents;
  private String ps1;
  private String ps2;
  private int type;
  private java.util.Date createdAt;
  private java.util.Date lastModifyTime;
  private String cjsj;
  private String xgsj;
  private String keytype;
  
  public int getId()
  {
    return this.id;
  }
  
  public String getTitle() {
    return this.title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getKeytype() {
    return this.keytype;
  }
  
  public void setKeytype(String keytype) {
    this.keytype = keytype;
  }
  
  public String getPic() {
    return this.pic;
  }
  
  public void setPic(String pic) {
    this.pic = pic;
  }
  
  public String getPs1() {
    return this.ps1;
  }
  
  public void setPs1(String ps1) {
    this.ps1 = ps1;
  }
  
  public String getPs2() {
    return this.ps2;
  }
  
  public void setPs2(String ps2) {
    this.ps2 = ps2;
  }
  
  public void setId(int id) {
    this.id = id;
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
