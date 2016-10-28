package controller;

import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import model.MsgFilter;
import service.MsgService;

@Controller
public class MsgController {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(MsgController.class);
	@Resource(name="AliImpl")
	private MsgService msgSender;
	
	@RequestMapping(value = { "/phoneMsg" }, method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject getCode(HttpServletRequest req,@RequestBody MsgFilter msgFilter) {
		JSONObject ret = new JSONObject();
		try {
			int code=(new Random()).nextInt(9999);//    
			if(code<1000) code+=1000; 
			 // 将四位数字的验证码保存到Session中。
		    HttpSession session = req.getSession();
		    System.out.print(code);
		    session.setAttribute("phoneCode", code+"");
		    session.setAttribute("phone", msgFilter.getPhone());
			String returnString = msgSender.batchSend(msgFilter.getPhone(), "",code+"");
			if(returnString.equals("1")){
				ret.put("status", 0);
			}else{
				ret.put("status",-1);
			}
			logger.debug(returnString);
		} catch (Exception e) {
			logger.warn("MsgController:"+e.getMessage());
			e.printStackTrace();
			ret.put("status",-1);
		}
		return ret;
	}
}
