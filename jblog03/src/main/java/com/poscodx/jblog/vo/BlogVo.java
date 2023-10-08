package com.poscodx.jblog.vo;

public class BlogVo {
	
	private String title;
	private String image;
	private String blog_id;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}
	@Override
	public String toString() {
		return "BlogVo [title=" + title + ", image=" + image + ", blog_id=" + blog_id + "]";
	}
	

}
