package com.moyou.moyouRms.qiniu;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.lf5.util.DateFormatManager;
import org.springframework.web.multipart.MultipartFile;

import com.moyou.moyouRms.constants.FILECONSTANT;
import com.moyou.moyouRms.exception.InvalidExtensionException;
import com.moyou.moyouRms.qiniu.entity.UploadCondition;
import com.moyou.moyouRms.util.JsonUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * 文件上传操作
 * 
 * @author yubing
 * @date 2016年9月27日 上午11:40:16
 */
public class FileUploadUtils {
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	private static final String ACCESS_KEY = "KEWGcIyBb7w6g6XeNP8yQgNYtUXGdgAqr-WmJiJk";
	private static final String SECRET_KEY = "gCS5DYd7P4gTPgcmM6BE8TJwTjxzcdYzWACzf_Lj";
	// 要上传的空间
	private static final String BUCKET_NAME = "moyou";
	// 密钥配置
	private static final Auth AUTH = Auth.create(ACCESS_KEY, SECRET_KEY);
	// 默认上传的地址
	private static String defaultBaseDir = "upload";

	public static String getDefaultBaseDir() {
		return defaultBaseDir;
	}

	public static void setDefaultBaseDir(String defaultBaseDir) {
		FileUploadUtils.defaultBaseDir = defaultBaseDir;
	}

	/**
	 * 上传格式验证
	 * 
	 * @param filename
	 * @return
	 */
	public final static boolean isAllowedextension(String filename) {
		if (StringUtils.isBlank(filename))
			return false;
		return isAllowedextension(FILECONSTANT.DEFAULT_ALLOWED_EXTENSION, filename);
	}

	/**
	 * 上传格式验证
	 * 
	 * @param extensions
	 * @param filename
	 * @return
	 */
	public static final boolean isAllowedextension(String[] extensions, String filename) {
		if (StringUtils.isBlank(filename))
			return false;
		if (extensions == null || extensions.length < 0)
			return isAllowedextension(filename);
		return ArrayUtils.contains(extensions, FilenameUtils.getExtension(filename).toLowerCase());
	}

	/**
	 * 是否允许文件上传
	 * 
	 * @param file
	 *            上传的文件
	 * @param allowedExtension
	 *            允许上传的类型 null-->FileConstant.DEFAULT_ALLOWED_EXTENSION
	 * @param maxSize
	 *            最大大小 字节为单位 -1表示不限制
	 * @throws InvalidExtensionException
	 *             文件格式异常
	 * @throws FileSizeLimitExceededException
	 *             文件大小出错异常
	 */
	public static final void assertAllowed(MultipartFile file, String[] allowedExtension, long maxSize)
			throws InvalidExtensionException, FileSizeLimitExceededException {
		String filename = file.getOriginalFilename();// 文件名
		if (!isAllowedextension(allowedExtension, filename)) {// 上传格式验证
			if (allowedExtension != null) {
				throw new InvalidExtensionException(allowedExtension, filename);
			} else {
				throw new InvalidExtensionException(FILECONSTANT.DEFAULT_ALLOWED_EXTENSION, filename);
			}
		}
		long size = file.getSize();
		if (maxSize != -1 && size > maxSize) {// 文件大小验证
			throw new FileSizeLimitExceededException("文件大小出错！", size, maxSize);
		}
	}

	/**
	 * 验证文件格式是否支持上传
	 * 
	 * @param filename
	 * @return
	 */
	public static final boolean isAllowedExtension(String filename) {
		if (StringUtils.isBlank(filename))
			return false;
		return ArrayUtils.contains(FILECONSTANT.DEFAULT_ALLOWED_EXTENSION,
				FilenameUtils.getExtension(filename).toLowerCase());
	}

	/**
	 * 是否允许文件上传
	 * 
	 * @param file
	 * @throws InvalidExtensionException
	 * @throws FileSizeLimitExceededException
	 */
	public static final void assertAllowed(MultipartFile file)
			throws InvalidExtensionException, FileSizeLimitExceededException {
		String filename = file.getOriginalFilename();// 文件名
		if (!isAllowedExtension(filename))
			throw new InvalidExtensionException(FILECONSTANT.DEFAULT_ALLOWED_EXTENSION, filename);
		long size = file.getSize();
		if (size > FILECONSTANT.DEFAULT_MAX_SIZE) {// 文件大小验证
			throw new FileSizeLimitExceededException("文件大小出错！", size, FILECONSTANT.DEFAULT_MAX_SIZE);
		}
	}
	public static final void assertAllowed(MultipartFile[] files) throws FileSizeLimitExceededException, InvalidExtensionException{
		for (MultipartFile file : files) {
			assertAllowed(file);
		}
	}
	/**
	 * 文件上传
	 * 
	 * @param request
	 *            当前请求 从请求中提取 应用上下文根
	 * @param baseDir
	 *            相对应用的基目录
	 * @param file
	 *            上传的文件
	 * @param allowedExtension
	 *            允许上传的类型 null-->FileConstant.DEFAULT_ALLOWED_EXTENSION
	 * @param maxSize
	 *            最大大小 字节为单位 -1表示不限制
	 * @throws InvalidExtensionException
	 * @throws FileSizeLimitExceededException
	 * @throws IOException
	 */
	public static final String upload(HttpServletRequest request, String baseDir, MultipartFile file,
			String[] allowedExtension, long maxSize)
			throws InvalidExtensionException, FileSizeLimitExceededException, IOException {
		int length = file.getOriginalFilename().length();
		if (length > 0)// 文件名长度验证
			throw new InvalidExtensionException(FILECONSTANT.DEFAULT_FILE_NAME_LENGTH);
		assertAllowed(file, allowedExtension, maxSize);
		String filename = extractFilename(file, baseDir);// 文件名
		File desc = getAbsoluteFile(extractUploadDir(request), filename);// new
		file.transferTo(desc);
		return filename;
	}

	/**
	 * 默认配置上传文件
	 * 
	 * @param request
	 *            当前请求
	 * @param file
	 *            要上传的文件
	 * @return
	 * @throws IOException
	 * @throws InvalidExtensionException
	 *             文件格式、文件名长度验证出错
	 * @throws FileSizeLimitExceededException
	 *             文件大小出错
	 */
	public static final String upload(HttpServletRequest request, MultipartFile file)
			throws FileSizeLimitExceededException, InvalidExtensionException, IOException {
		return upload(request, file, FILECONSTANT.DEFAULT_ALLOWED_EXTENSION);
	}

	/**
	 * 默认配置上传文件
	 * 
	 * @param request
	 * @param file
	 * @param defaultAllowedExtension
	 * @return
	 * @throws FileSizeLimitExceededException
	 * @throws InvalidExtensionException
	 * @throws IOException
	 */
	public static final String upload(HttpServletRequest request, MultipartFile file, String[] defaultAllowedExtension)
			throws FileSizeLimitExceededException, RuntimeException, IOException {
		return upload(request, getDefaultBaseDir(), file, defaultAllowedExtension, FILECONSTANT.DEFAULT_MAX_SIZE);
	}

	/**
	 * 构建上传文件名 格式为 yyyy-MM-dd_uuid_文件名.后缀名
	 * 
	 * @param file
	 * @param uuid
	 * @return
	 */
	public static final String reconsFilename(MultipartFile file, String uuid) {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "_" + uuid + "_" + file.getOriginalFilename();
	}

	/**
	 * 上传文件至七牛云
	 * 
	 * @param condition
	 *            condition 中 userId 不能是 null 或者空字符串
	 * @return
	 * @throws IOException
	 * @throws QiniuException
	 */
	public static final List<Response> uploadQiniu(UploadCondition condition) throws QiniuException, IOException {
		UploadManager uploadManager = new UploadManager();
		List<Response> list = new ArrayList<>();
		MultipartFile file = null;
		String uuid = condition.getUserId();
		String fileType = condition.getFilePath();
		for (int i = 0; i < condition.getFiles().length; i++) {
			file = condition.getFiles()[i];
			// assertAllowed(file);
			list.add(uploadManager.put(file.getBytes(), fileType + reconsFilename(file, uuid),
					AUTH.uploadToken(BUCKET_NAME)));
		}
		return list;
	}

	/**
	 * 返回上传结果URI
	 * 
	 * @param response
	 * @return
	 * @throws QiniuException
	 */
	public static final String processResponse(Response response) throws QiniuException {
		return JsonUtil.toMap(response.bodyString()).get("key").toString();
	}
	
	/**
	 * 
	 * @param uploadDir
	 *            上传文件夹
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	private static File getAbsoluteFile(String uploadDir, String filename) throws IOException {
		uploadDir = FilenameUtils.normalizeNoEndSeparator(uploadDir);
		File desc = new File(uploadDir + File.separator + filename);

		if (!desc.getParentFile().exists()) {
			desc.getParentFile().mkdirs();
		}
		if (!desc.exists()) {
			desc.createNewFile();
		}
		return desc;
	}

	/**
	 * 文件名提取
	 * 
	 * @param file
	 * @param baseDir
	 * @return
	 */
	public static final String extractFilename(MultipartFile file, String baseDir) {
		return baseDir + File.separator + file.getOriginalFilename().replaceAll(" ", "");
	}

	/**
	 * 根传目录
	 * 
	 * @param request
	 * @return
	 */
	public static final String extractUploadDir(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/");
	}

	public static void main(String[] args) throws QiniuException, IOException {
		String u = "{\"hash\":\"FjIJ9PmhLdBhFauJmTMyxtjnGXc9\",\"key\":\"image/110732f67bsaaqon6ssfna.jpg\"}";
		@SuppressWarnings({ "unused", "unchecked" })
		Map<String, String> map = (Map<String, String>) JsonUtil.toObject(u, HashMap.class);
		System.out.println(new DateFormatManager(Locale.CHINESE));

//		List<Response> res = uploadQiniu(new UploadCondition());
		List<String> strs = new ArrayList<>();
		strs.add("1");
		strs.add("1");
		strs.add("1");
		strs.add("1");
//		for (Response response : res) {
//			strs.add(processResponse(response));
//		}
	}
}
