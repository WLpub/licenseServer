package service;

import org.springframework.stereotype.Service;

/**
 * 获取用户的状态<br/>
 * 1) 是否购买过商品 <br/>
 * 2) 是否为教师 <br/>
 * 3) 是否是认证过的github开源用户 <br/>
 */
@Service
public class PermissionService {
	private String permission;
	
	public PermissionService(){
		this.permission = "000000";
	}
	
	public String setUserPermission(String permission){
		this.permission = permission;
		return this.permission;
	}
	
	public String setShopping(boolean per){
		StringBuilder strBuilder = new StringBuilder(this.permission);
		strBuilder.setCharAt(5, per?'1':'0');
		this.permission= strBuilder.toString();
		return this.permission;
	}
	
	public boolean getShopping(){
		return this.permission.charAt(5)=='1';
	}
	
	public String setTeacher(boolean per){
		StringBuilder strBuilder = new StringBuilder(this.permission);
		strBuilder.setCharAt(5, per?'1':'0');
		this.permission= strBuilder.toString();
		return this.permission;
	}
	
	public boolean getTeacher(){
		return this.permission.charAt(4)=='1';
	}
	
	public String setGithub(boolean per){
		StringBuilder strBuilder = new StringBuilder(this.permission);
		strBuilder.setCharAt(3, per?'1':'0');
		this.permission= strBuilder.toString();
		return this.permission;
	}
	
	public boolean getGithub(){
		return this.permission.charAt(3)=='1';
	}
}
