package interfaces.personal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import web.upload.controller.UploadService;

import com.table.user.entity.User;
import com.table.user.service.UserService;

@Controller
@RequestMapping("/imageUploadInterface")
public class ImageUploadInterface {
	@Autowired
	private UserService userService;

	@Autowired
	private UploadService uploadService;

	/**
	 * 应用解析器上传文件。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		User user = userService.getUser(userId);
		Long time = System.currentTimeMillis();// 获取当前时间戳
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				// 文件类型限制
				List<String> fileTypes = new ArrayList<String>();
				fileTypes.add("jpg");
				fileTypes.add("jpeg");
				fileTypes.add("bmp");
				fileTypes.add("gif");
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					String ext = myFileName.substring(
							myFileName.lastIndexOf(".") + 1,
							myFileName.length());
					if (!fileTypes.contains(ext)) {
						return;
					}
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						System.out.println(myFileName);
						// 重命名上传后的文件名
						String fileName = "demoUpload"
								+ file.getOriginalFilename();
						// 定义上传路径
						String imgFilePath = "E:/workplace/userSafe/src/main/webapp/images/upload/images/";// 新生成的图片F
						imgFilePath = imgFilePath + userId + time + myFileName;
						String truePath = "/images/upload/images/" + userId
								+ time + fileName;
						File localFile = new File(imgFilePath);
						file.transferTo(localFile);
						user.setImage(truePath);
						userService.saveOrUpdate(user);
						PrintWriter out = response.getWriter();
						out.print("success");
						out.flush();
						out.close();
					}
				}
			}
		}
	}

	@RequestMapping("/uploadImg")
	@ResponseBody
	public void uploadImg(HttpServletRequest request,
			HttpServletResponse response, File image) throws IOException {
		if (image == null) // 图像数据为空
		{
			return;
		}
		// 生成jpeg图片
		String imgFilePath = "E:/workplace/userSafe/src/main/webapp/images/upload/images/";// 新生成的图片F
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		User user = userService.getUser(userId);
		Long time = System.currentTimeMillis();// 获取当前时间戳
		imgFilePath = imgFilePath + userId + time + ".png";
		String truePath = "/images/upload/images/" + userId + time + ".png";
		if (uploadService.androidSaveManager(image, imgFilePath)) {
			user.setImage(truePath);
			userService.saveOrUpdate(user);
			PrintWriter out = response.getWriter();
			out.print("success");
			out.flush();
			out.close();
		}
	}

	@RequestMapping("/upload2")
	public void upload2(@RequestParam("file") CommonsMultipartFile[] files,
			HttpServletRequest request, HttpServletResponse response,
			String token) throws IOException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String phoneMacString = (String) session.getAttribute("mac");
		if (!phoneMacString.endsWith(token)) {
			return;
		}

		for (int i = 0; i < files.length; i++) {
			System.out.println("fileName---------->"
					+ files[i].getOriginalFilename());

			if (!files[i].isEmpty()) {
				int pre = (int) System.currentTimeMillis();
				try {
					String imgFilePath = "D:/tomcat-8/wtpwebapps/userSafe/images/upload/images/";// 新生成的图片F

					User user = userService.getUser(userId);
					Long time = System.currentTimeMillis();// 获取当前时间戳
					imgFilePath = imgFilePath + userId + time
							+ files[i].getOriginalFilename();
					String truePath = "/images/upload/images/" + userId + time
							+ files[i].getOriginalFilename();
					user.setAndroidImage(truePath);

					// 拿到输出流，同时重命名上传的文件
					FileOutputStream os = new FileOutputStream(imgFilePath);
					// 拿到上传文件的输入流
					InputStream in = (InputStream) files[i].getInputStream();

					// 以写字节的方式写文件
					int b = 0;
					while ((b = in.read()) != -1) {
						os.write(b);
					}
					os.flush();
					os.close();
					in.close();
					int finaltime = (int) System.currentTimeMillis();
					System.out.println(finaltime - pre);
					userService.saveOrUpdate(user);

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("上传出错");
				}
			}
		}
		PrintWriter out = response.getWriter();
		out.print("success");
		out.flush();
		out.close();
	}
}
