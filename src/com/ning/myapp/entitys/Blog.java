package com.ning.myapp.entitys;

public class Blog {
	private Long Blogid;
	private Long   BlogCategoryId;
	private String UserId;
	private String BlogTitle;
	private String Content;
	private String ImageUrl;
	private String Tags;
	private String CreatedTime;
	private int Public;
	public Long getBlogid() {
		return Blogid;
	}
	public void setBlogid(Long blogid) {
		Blogid = blogid;
	}
	public Long getBlogCategoryId() {
		return BlogCategoryId;
	}
	public void setBlogCategoryId(Long blogCategoryId) {
		BlogCategoryId = blogCategoryId;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getBlogTitle() {
		return BlogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		BlogTitle = blogTitle;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public String getTags() {
		return Tags;
	}
	public void setTags(String tags) {
		Tags = tags;
	}
	public String getCreatedTime() {
		return CreatedTime;
	}
	public void setCreatedTime(String createdTime) {
		CreatedTime = createdTime;
	}
	public int getPublic() {
		return Public;
	}
	public void setPublic(int public1) {
		Public = public1;
	}
	
	
}
