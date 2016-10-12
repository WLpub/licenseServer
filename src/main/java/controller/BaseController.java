package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import model.User;
import model.UserFilter;
import service.CodeService;
import service.PermissionService;
import service.UserService;

@Controller
public class BaseController {

	private static final String VIEW_INDEX = "index";
	private static final String VIEW_MANAGE = "manage";
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Resource
	private UserService userService;
	@Resource
	private PermissionService permissionService;
	@Resource
	private CodeService codeID;
	
	@RequestMapping(value = {"/","index","index.html","index.jsp"}, method = RequestMethod.GET)
	public String welcome(HttpSession httpSession) {
			return VIEW_INDEX;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession httpSession) {
		try {
			httpSession.setAttribute("user",null);
		} catch (Exception e) {
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manage(HttpSession httpSession) {
		// Spring uses InternalResourceViewResolver and return back manage.jsp
		if(httpSession.getAttribute("user")==null){
			return "redirect:/#/login";
		}else{
			return VIEW_MANAGE;
		}
	}
	
	@RequestMapping(value = "/getGeneralInfo", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject getGeneralInfo(HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
				User u = (User)httpSession.getAttribute("user");
				if(u==null){
					throw new Exception("用户未登录！");
				}
				User ur = userService.selectUserById(u.getId());
				ur.setPassword("");
				ret.put("user", ur);
				ret.put("status", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		return ret;
	}
	@RequestMapping(value = "/judgeUser", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject judgeUser(@RequestBody User user,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			if(user.getPassword()==null||user.getPassword().equals("")){
				ret.put("status", -1);
				ret.put("errMsg", "用户未登录！");
				return ret;
			}
			if (!(user.getEmail()==null)&&!user.getEmail().equals("")) {
				ret.put("status", userService.judgeUserByEmail(user));
			} else if (!(user.getPhone()==null)&&!user.getPhone().equals("")) {
				User us = userService.judgeUserByPhone(user);
				if(us!=null&&us.getId()>0){
					us.setPassword("");
					httpSession.setAttribute("user",us);
					ret.put("status", us.getId());
					ret.put("user", us);
				}else{
					ret.put("status", -1);
					ret.put("errMsg", "用户名或密码错误！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		return ret;
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject createUser(@RequestBody UserFilter userFilter,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			User user = userFilter.getUser();
			if(!userFilter.getImageCode().equals(httpSession.getAttribute("imageCode"))){
				ret.put("status", -1);
				ret.put("errMsg","图片验证码错误！");
			}
			else if(!userFilter.getPhoneCode().equals(httpSession.getAttribute("phoneCode"))){
				ret.put("status", -1);
				ret.put("errMsg","手机验证码错误！");
			}else{
				user.setPoint(10);
				if(user.getPermission()!=null&&user.getPermission().equals("1")){
					user.setPermission(permissionService.setCompony(true));
				}
				user.setBalance(0);
				int uId = userService.createUser(user);
				String serCode = codeID.toSerialCode(uId);
				user.setCode(serCode);
				userService.updateUser(user);
				if(uId>0){
					ret.put("status", uId);
				}
				else{
					ret.put("status", -1);
					ret.put("errMsg", "创建用户失败，手机或邮箱重复！");
				}
			}
		} catch (Exception e) {
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		return ret;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject updateUser(@RequestBody User user) {
		JSONObject ret = new JSONObject();
		try {
			if (user.getId() < 1) {
				ret.put("status", -1);
				ret.put("errMsg", "Wrong id number!");
				logger.debug("user id:" + ret + " updated failed");
				return ret;
			}
			ret.put("id", userService.updateUser(user));
		} catch (Exception e) {
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		ret.put("status", 0);
		logger.debug("user id:" + ret + "; with name:" + user.getUsername());
		return ret;
	}
	
}
