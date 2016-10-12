package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import util.StringUtil;

@Controller
public class ImageController {
	private static String t=Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static String path=t.substring(1,t.indexOf(".metadata")).replace('/', '\\')+"LicenseServer\\file\\";
	/**
	 * 加载图片
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getImage")
	public ModelAndView getImage(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "fileName", required = false) String fileName) {
		if (!StringUtil.isNull(fileName)) {
			String templatePath = path + fileName;
			System.out.println(templatePath);
			File temp = new File(templatePath);
			FileInputStream steam = null;
			if (temp.exists()) {
				try {
					steam = new FileInputStream(templatePath);
					String fileNames = new String(fileName.getBytes("UTF-8"), "ISO_8859_1");
					response.setCharacterEncoding("UTF-8");
					String contentType = null;
					if (fileName.endsWith(".emf")) {
						contentType = "application/x-emf";
					} else if (fileName.endsWith(".wmf")) {
						contentType = "application/x-wmf";
					} else if (fileName.endsWith(".jpeg") || fileName.endsWith(".jpg")) {
						contentType = "image/jpeg";
					} else if (fileName.endsWith(".png")) {
						contentType = "image/png";
					} else if (fileName.endsWith(".dib")) {
						contentType = "application/x-dib";
					} else if (fileName.endsWith(".gif")) {
						contentType = "image/gif";
					} else if (fileName.endsWith(".tiff")) {
						contentType = "image/tiff";
					} else if (fileName.endsWith(".eps")) {
						contentType = "application/x-ps";
					} else if (fileName.endsWith(".bmp")) {
						contentType = "application/x-bmp";
					} else if (fileName.endsWith(".wpg")) {
						contentType = "application/x-wpg";
					}
					if (contentType != null) {
						response.setContentType(contentType);
					}
					response.setHeader("Pragma", "No-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 0);
					response.addHeader("content-disposition", "attachment; filename=" + fileNames);
					OutputStream out = response.getOutputStream();
					byte[] bt = new byte[1024];
					while (steam.read(bt) > -1) {
						out.write(bt);
					}
					out.flush();
					out.close();
				} catch (Exception e) {
				} finally {
					try {
						if (steam != null) {
							steam.close();
						}
					} catch (IOException e) {
					}
				}
			} else {
				System.out.println("file not exsits");
				try {
					steam = new FileInputStream(request.getSession().getServletContext().getRealPath("/images/getPhotoFailed.jpg"));
					String fileNames = new String(fileName.getBytes("UTF-8"), "ISO_8859_1");
					response.setCharacterEncoding("UTF-8");
					response.setContentType("image/jpeg");
					response.setHeader("Pragma", "No-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 0);
					response.addHeader("content-disposition", "attachment; filename=" + fileNames);
					OutputStream out = response.getOutputStream();
					byte[] bt = new byte[1024];
					while (steam.read(bt) > -1) {
						out.write(bt);
					}
					out.flush();
					out.close();
				} catch (Exception e) {
				} finally {
					try {
						if (steam != null) {
							steam.close();
						}
					} catch (IOException e) {
					}
				}
			}
		} else {// fileName为空时
			FileInputStream steam = null;
			try {
				steam = new FileInputStream(request.getSession().getServletContext().getRealPath("/images/noPhoto.jpg"));
				String fileNames = new String(fileName.getBytes("UTF-8"), "ISO_8859_1");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("image/jpeg");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.addHeader("content-disposition", "attachment; filename=" + fileNames);
				OutputStream out = response.getOutputStream();
				byte[] bt = new byte[1024];
				while (steam.read(bt) > -1) {
					out.write(bt);
				}
				out.flush();
				out.close();
			} catch (Exception e) {
			} finally {
				try {
					if (steam != null) {
						steam.close();
					}
				} catch (IOException e) {
				}
			}
		}
		return null;
	}
}
