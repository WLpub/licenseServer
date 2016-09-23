package model;

public class UserFilter {
	private User user;
	private String phoneCode;
	private String imageCode;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPhoneCode() {
		return phoneCode;
	}
	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
	public String getImageCode() {
		return imageCode;
	}
	public void setImageCode(String code) {
		this.imageCode = code;
	}
}
