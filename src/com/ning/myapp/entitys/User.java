package com.ning.myapp.entitys;

public class User {
	
	private String Id;
	private String Username;
	private String Password;
	private String Gender;
	private int Age;
	private String Address;
	private String Email;
	private Long DefaultCateId;
	private String CreatedTime;
	private int Weight;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getCreatedTime() {
		return CreatedTime;
	}
	public void setCreatedTime(String createdTime) {
		CreatedTime = createdTime;
	}
	public int getWeight() {
		return Weight;
	}
	public void setWeight(int weight) {
		Weight = weight;
	}
	
	public Long getDefaultCateId() {
		return DefaultCateId;
	}
	public void setDefaultCateId(Long defaultCateId) {
		DefaultCateId = defaultCateId;
	}
	
	
}
