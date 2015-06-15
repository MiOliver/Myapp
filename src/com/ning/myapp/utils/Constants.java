package com.ning.myapp.utils;

public class Constants {

	public static final class Preference {
		public static final String LOGINSTATUS = "loginstatus";
		public static final String USERNAME = "username";
		public static final String USERID = "userid";
	}

	public static final class Url {

		public static final class Bloginfo {
			public static final String ALLBLOG = "http://10.236.121.37:8080/v1/blog/getUserBlog?userId=";
//			public static final String ALLBLOG = "http://192.168.1.106:8080/v1/blog/getUserBlog?userId=";
//			public static final String CREATEBLOG = "http://192.168.1.106:8080/v1/blog/";
			public static final String CREATEBLOG = "http://10.236.121.37:8080/v1/blog/";
			
		}

		public static final class Userinfo {

			public static final String url = "http://10.236.121.37:8080/v1/user/user_1433852026322401409";
//			public static final String url = "http://192.168.1.106:8080/v1/user/user_1433689062285430207";
			
			// private String LOGIN =
			// "http://192.168.1.111:8080/v1/user/login?";
			public static final String LOGIN = "http://10.236.121.37:8080/v1/user/login?";
//			public static final String LOGIN = "http://192.168.1.106:8080/v1/user/login?";

		}

		public static final class Category {
//			public static final String ALLCATEGORY = "http://192.168.1.106:8080/v1/blogcategory/getUserBlogCategory?userId=";
			public static final String ALLCATEGORY = "http://10.236.121.37:8080/v1/blogcategory/getUserBlogCategory?userId=";
			
		}

	}

}
