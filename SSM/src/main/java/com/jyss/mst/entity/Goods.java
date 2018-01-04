package com.jyss.mst.entity;

import java.util.Date;

public class Goods {
  private int id;
  private int type;
  private String name;
  private String category;
  private int categoryId;
  private String pics;
  private String img;
  private String content;
  private float price;
  private int status;
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
  
  public String getCategory() {
    return this.category;
  }
  
  public void setCategory(String category) {
    this.category = category;
  }
  
  public String getImg() {
    return this.img;
  }
  
  public void setImg(String img) {
    this.img = img;
  }
  
  public String getContent() {
    return this.content;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
  
  public int getType() {
    return this.type;
  }
  
  public void setType(int type) {
    this.type = type;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getCategoryId() {
    return this.categoryId;
  }
  
  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }
  
  public String getPics() {
    return this.pics;
  }
  
  public void setPics(String pics) {
    this.pics = pics;
  }
  
  public float getPrice() {
    return this.price;
  }
  
  public void setPrice(float price) {
    this.price = price;
  }
  
  public int getStatus() {
    return this.status;
  }
  
  public void setStatus(int status) {
    this.status = status;
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
