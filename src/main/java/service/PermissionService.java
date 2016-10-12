package service;

import org.springframework.stereotype.Service;

/**
 * 获取用户的状态：0-无，1-未认证/等待认证，2-认证<br/>
 * 1) 是否购买过商品 <br/>
 * 2) 是否为教师 <br/>
 * 3) 是否是认证过的github开源用户 <br/>
 * 4) 是否是企業用戶 <br/>
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
	
	public boolean getShopping(){
		return this.permission.charAt(5)=='1';
	}
	
	public String setShopping(boolean per){
		StringBuilder strBuilder = new StringBuilder(this.permission);
		strBuilder.setCharAt(5, per?'1':'0');
		this.permission= strBuilder.toString();
		return this.permission;
	}
	
	public String setShoppingAuth(boolean per){
		StringBuilder strBuilder = new StringBuilder(this.permission);
		strBuilder.setCharAt(5, per?'2':'0');
		this.permission= strBuilder.toString();
		return this.permission;
	}
	
	public String setTeacher(boolean per){
		StringBuilder strBuilder = new StringBuilder(this.permission);
		strBuilder.setCharAt(4, per?'1':'0');
		this.permission= strBuilder.toString();
		return this.permission;
	}
	
	public String setTeacherAuth(boolean per){
		StringBuilder strBuilder = new StringBuilder(this.permission);
		strBuilder.setCharAt(4, per?'2':'0');
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
	
	public String setGithubAuth(boolean per){
		StringBuilder strBuilder = new StringBuilder(this.permission);
		strBuilder.setCharAt(3, per?'2':'0');
		this.permission= strBuilder.toString();
		return this.permission;
	}
	
	public boolean getGithub(){
		return this.permission.charAt(3)=='1';
	}
	
	public String setCompony(boolean per){
		StringBuilder strBuilder = new StringBuilder(this.permission);
		strBuilder.setCharAt(2, per?'1':'0');
		this.permission= strBuilder.toString();
		return this.permission;
	}
	
	public String setComponyAuth(boolean per){
		StringBuilder strBuilder = new StringBuilder(this.permission);
		strBuilder.setCharAt(2, per?'2':'0');
		this.permission= strBuilder.toString();
		return this.permission;
	}
	
	public boolean getCompony(){
		return this.permission.charAt(2)=='1';
	}
}
