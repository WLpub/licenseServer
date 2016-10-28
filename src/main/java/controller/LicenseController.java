package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import model.License;
import model.LicenseFilter;
import model.User;
import service.FileService;
import service.LicenseService;
import service.PermissionService;
import thread.ThreadVariables;

@Controller
public class LicenseController {
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(LicenseController.class);

	@Resource
	private LicenseService licenseService;
	@Resource
	private FileService fileService;
	@Resource
	private PermissionService permissionService;
	
	@RequestMapping(value = "/createLicense", method = RequestMethod.POST)
	public @ResponseBody JSONObject createLicense(@RequestParam("file") MultipartFile file,@RequestParam("description") String description,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			User ur = (User)httpSession.getAttribute("user");
			if(ur==null){
				throw new Exception("用户未登录！");
			}
			License license = new License();
			String fileName = fileService.keepFile(file,ur.getId());
			license.setUserID(ur.getId());
			license.setFile(fileName);
			license.setDescription(description);
			license.setStatus("0");
			int licenseID = licenseService.createLicense(license);
			if(licenseID<0)
				throw new Exception("创建失败，请重试！");
			
			ret.put("status", licenseID);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			ret.put("status", -1);
			ret.put("errMsg", e.getMessage());
			return ret;
		}
		return ret;
	}
	
	@RequestMapping(value = "/getLicenseByUserID", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject getLicenseByUserID(@RequestBody LicenseFilter licenseFilter,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			User ur = (User)httpSession.getAttribute("user");
			if(ur==null){
				throw new Exception("用户未登录！");
			}
			ret.put("licenses",licenseService.selectLicenseByUserID(ur.getId(),licenseFilter.getStart()));
			ret.put("count",licenseService.getTotalCountByUserID(ur.getId()));
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
	
	@RequestMapping(value = "/getLicenseByStatusUserID", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject getLicenseByStatusUserID(@RequestBody LicenseFilter licenseFilter,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			User ur = (User)httpSession.getAttribute("user");
			if(ur==null){
				throw new Exception("用户未登录！");
			}
			ret.put("licenses",licenseService.selectLicenseByStatusUserID(ur.getId(),licenseFilter.getLicense().getStatus(),licenseFilter.getStart()));
			ret.put("count",licenseService.getTotalCountByStatusUserID(licenseFilter.getLicense().getStatus(),ur.getId()));
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
	
	@RequestMapping(value = "/getLicenseByStatus", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject getLicenseByStatus(@RequestBody LicenseFilter licenseFilter,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			User ur = (User)httpSession.getAttribute("user");
			if(ur==null){
				throw new Exception("用户未登录！");
			}if(!permissionService.isAdmin(ur.getPermission())){
				throw new Exception("用户权限不够！");
			}
			ret.put("licenses",licenseService.selectLicenseByStatus(licenseFilter.getLicense().getStatus(),licenseFilter.getStart()));
			ret.put("count",licenseService.getTotalCountByStatus(licenseFilter.getLicense().getStatus()));
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
	
	@RequestMapping(value = "/updateLicenseStatus", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody JSONObject updateLicenseStatus(@RequestBody License license,HttpSession httpSession) {
		JSONObject ret = new JSONObject();
		try {
			User ur = (User)httpSession.getAttribute("user");
			if(ur==null){
				throw new Exception("用户未登录！");
			}if(!permissionService.isAdmin(ur.getPermission())){
				throw new Exception("用户权限不够！");
			}
			if(!license.getStatus().equals("1")){
				license.setResult("");
			}else{
				ThreadVariables.licenseList.add(license.getFile());
				license.setResult(license.getFile());
			}
			licenseService.updateLicenseStatus(license);
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
