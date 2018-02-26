package cetc.software.lujunzizi.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileOperateUtil {

	/**
	 * 下载文件
	 * 
	 * 2016-4-14 follow the k10 to surround every close method with try/catch
	 * 
	 * @param request
	 * @param response
	 * @param downLoadPath
	 * @param contentType
	 * @param realName
	 * @throws Exception
	 */
	public static void download(HttpServletRequest request,
			HttpServletResponse response, String downLoadPath,
			String contentType, String realName) {
		response.setContentType("text/html;charset=UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			request.setCharacterEncoding("UTF-8");
			long fileLength = new File(downLoadPath).length();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(realName.getBytes(), "ISO-8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));

			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static File creatFolder(String brandName, String fileName) {
		File file = null;
		brandName = brandName.replaceAll(" ", ""); // 替换半角空格
		brandName = brandName.replaceAll(" ", ""); // 替换全角空格
		File Folder = new File(brandName);
		if (Folder.exists()) { // 如果二级文件夹也存在，则创建文件
			file = new File(Folder, fileName);
		} else { // 如果二级文件夹不存在，则创建二级文件夹
			Folder.mkdir();
			file = new File(Folder, fileName); // 创建完二级文件夹后，再合建文件
		}
		return file;
	}

	public static void createFile(String fileContent, File file) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "utf-8"));
			try {
				bw.append(fileContent);
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String readTxtFile(String filePath) {
		InputStreamReader read = null;
		BufferedReader br = null;
		StringBuffer sb = null;
		try {
			read = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
			br = new BufferedReader(read);
			sb = new StringBuffer();
			String lineTxt = "";
			while ((lineTxt = br.readLine()) != null) {
				sb.append(lineTxt);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
