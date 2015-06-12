package com.ning.myapp.entitys;

public class BlogCategory {

	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getDescri() {
		return Descri;
	}
	public void setDescri(String descri) {
		Descri = descri;
	}
	public String getCreatedTime() {
		return CreatedTime;
	}
	public void setCreatedTime(String createdTime) {
		CreatedTime = createdTime;
	}
	private Long Id;
	private String Title;
	private String UserId;
	private String Descri;
	private String CreatedTime;
	
	

}
