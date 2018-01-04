package com.jyss.mst.entity;

import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

public class PcTest
{
  private int id;
  private String titles;
  private String vedio;
  private long byteSize;
  private MultipartFile myFile;
  private String type;
  private String typeName;
  private String itemid;
  private int vedioType;
  private Date createdAt;
  private Date lastModifyTime;
  private String cjsj;
  private String xgsj;
  
  public int getId()
  {
    return this.id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getTitles() {
    return this.titles;
  }
  
  public String getTypeName() {
    return this.typeName;
  }
  
  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }
  
  public void setTitles(String titles) {
    this.titles = titles;
  }
  
  public String getVedio() {
    return this.vedio;
  }
  
  public String getItemid() {
    return this.itemid;
  }
  
  public void setItemid(String itemid) {
    this.itemid = itemid;
  }
  
  public void setVedio(String vedio) {
    this.vedio = vedio;
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
  
  public String getType() {
    return this.type;
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
  public int getVedioType() {
    return this.vedioType;
  }
  
  public void setVedioType(int vedioType) {
    this.vedioType = vedioType;
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
