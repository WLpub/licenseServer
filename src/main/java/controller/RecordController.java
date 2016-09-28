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

import model.Record;
import model.User;
import service.RecordService;

@Controller
public class RecordController {
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(RecordController.class);

	@Resource
	private RecordService recordService;
	
	@RequestMapping(value = "/createRecord", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject createRecord(@RequestBody Record record,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			if(httpSession.getAttribute("user")==null){
				throw new Exception("用户未登录！");
			}
			ret.put("status",recordService.createRecord(record));
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		return ret;
	}
	
	@RequestMapping(value = "/getRecord", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject getRecord(HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			User ur = (User)httpSession.getAttribute("user");
			if(ur==null){
				throw new Exception("用户未登录！");
			}
			ret.put("records",recordService.selectRecordByUserID(ur.getId()));
			ret.put("status", 1);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		return ret;
	}
}
