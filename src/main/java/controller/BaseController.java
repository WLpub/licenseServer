package controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import model.Channel;
import model.ChannelFilter;
import model.Filter;
import model.Resume;
import model.ResumeFilter;
import model.User;
import service.ChannelService;
import service.ResumeService;
import service.UserService;

@Controller
public class BaseController {

	private static final String VIEW_INDEX = "index";
	private static final String VIEW_MANAGE = "manage";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);

	@Resource
	private UserService userService;
	@Resource
	private ChannelService channelService;
	@Resource
	private ResumeService resumeService;
	
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
				if(httpSession.getAttribute("user")==null){
					throw new Exception("用户未登陆");
				}
				User ur = (User)httpSession.getAttribute("user");
				ur.setPassword("");
				ret.put("user", ur);
				ret.put("totalWay", channelService.getChannelCount(ur.getId()));
				ret.put("totalFile", resumeService.getResumeCount(ur.getId()));
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
				ret.put("errMsg", "密码为空！");
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
					ret.put("errMsg", "用户不存在！");
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
	public @ResponseBody JSONObject createUser(@RequestBody User user) {
		JSONObject ret = new JSONObject();
		try {
			int uId = userService.createUser(user);
			if(uId>0){
				ret.put("status", uId);
			}
			else{
				ret.put("status", -1);
				ret.put("errMsg", "用户已经存在！");
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
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject doFilter(@RequestBody Filter filter) {
		JSONObject ret = new JSONObject();
		try {
			/*SearchCondition condition1 = new SearchCondition(DefaultSearchField.GraduateSchoolField,
					new SearchValue<String>("华中科技大学"));
			SearchCondition condition2 = new SearchCondition(DefaultSearchField.currentPositionField,
					new SearchValue<String>("九江"));
			List<com.linkedinhr.model.hr.Resume> resumes = YiqunSearch.search("testhr_index", "resume", Arrays.asList(condition1,condition2),
					ConditionOperator.AND);
			System.out.println(resumes);*/
		} catch (Exception e) {
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		ret.put("status", 0);
		return ret;
	}
	
	@RequestMapping(value = "/getChannelByUserId", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject getChannelByUserId(@RequestBody ChannelFilter channelFilter,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			if(httpSession.getAttribute("user")==null){
				throw new Exception("用户未登陆");
			}
			User ur = (User) httpSession.getAttribute("user");
			List<Channel> channels = channelService.getChannelByUserId(channelFilter.getStart(),ur.getId());
			if(channels.size()>0){
				ret.put("status", 1);
				ret.put("channels", channels);
				ret.put("count",channelService.getChannelCount(ur.getId()));
			}
			else{
				ret.put("status", -1);
				ret.put("errMsg", "渠道不存在！");
			}
		} catch (Exception e) {
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		return ret;
	}
	@RequestMapping(value = "/getChannelById", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject getChannelById(@RequestBody Channel channel,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			if(httpSession.getAttribute("user")==null){
				throw new Exception("用户未登陆");
			}
			List<Channel> channels = channelService.getChannelById(channel.getId());
			if(channels.size()>0){
				ret.put("status", 1);
				ret.put("channels", channels);
			}
			else{
				ret.put("status", -1);
				ret.put("errMsg", "渠道不存在！");
			}
		} catch (Exception e) {
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		return ret;
	}
	@RequestMapping(value = "/createChannel", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject createChannel(@RequestBody Channel channel,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			if(httpSession.getAttribute("user")==null){
				throw new Exception("用户未登陆");
			}
			channel.setUser_id(((User) httpSession.getAttribute("user")).getId());
			int cId = channelService.createChannel(channel);
			if(cId>0){
				ret.put("status", cId);
			}
			else{
				ret.put("status", -1);
				ret.put("errMsg", "渠道已经存在！");
			}
		} catch (Exception e) {
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		return ret;
	}
	
	@RequestMapping(value = "/changeChannel", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject changeChannel(@RequestBody Channel channel,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			if(httpSession.getAttribute("user")==null){
				throw new Exception("用户未登陆");
			}
			int cId = channelService.changeChannel(channel);
			if(cId>0){
				ret.put("status", cId);
			}
			else{
				ret.put("status", -1);
				ret.put("errMsg", "渠道修改失败！");
			}
		} catch (Exception e) {
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		return ret;
	}
	
	@RequestMapping(value = "/changeChannelState", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject changeChannelState(@RequestBody Channel channel,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			if(httpSession.getAttribute("user")==null){
				throw new Exception("用户未登陆");
			}
			int cId = channelService.changeChannelStatus(channel);
			if(cId>0){
				ret.put("status", cId);
			}
			else{
				ret.put("status", -1);
				ret.put("errMsg", "渠道修改失败！");
			}
		} catch (Exception e) {
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		return ret;
	}
	
	@RequestMapping(value = "/getResumeByUserId", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject getResumeByUserId(@RequestBody ResumeFilter resumeFilter,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			if(httpSession.getAttribute("user")==null){
				throw new Exception("用户未登陆");
			}
			User ur = (User)httpSession.getAttribute("user");
			List<Resume> resumes = resumeService.getResumeByUserId(resumeFilter.getStart(),ur.getId());
			if(resumes.size()>0){
				ret.put("status", 1);
				ret.put("resumes", resumes);
				ret.put("count",resumeService.getResumeCount(ur.getId()));
			}
			else{
				ret.put("status", -1);
				ret.put("errMsg", "简历不存在！");
			}
		} catch (Exception e) {
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		return ret;
	}
	@RequestMapping(value = "/getResumeById", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject getResumeById(@RequestBody Resume resume,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			if(httpSession.getAttribute("user")==null){
				throw new Exception("用户未登陆");
			}
			List<Resume> resumes = resumeService.getResumeById(resume.getId());
			if(resumes.size()>0){
				ret.put("status", 1);
				ret.put("channels", resumes);
			}
			else{
				ret.put("status", -1);
				ret.put("errMsg", "简历不存在！");
			}
		} catch (Exception e) {
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		return ret;
	}
}
