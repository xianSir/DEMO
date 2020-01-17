package com.demo.io;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.rmi.Naming;
import java.util.LinkedList;
import java.util.List;

public class FileUtil {

	//	/**
	//	 * @功能 下载临时素材接口
	//	 * @param filePath 文件将要保存的目录
	//	 * @param method 请求方法，包括POST和GET
	//	 * @param url 请求的路径
	//	 * @return
	//	 */
	//
	//	public static File saveUrlAs(String url,String filePath,String filename,String method){
	//		 //System.out.println("fileName---->"+filePath);
	//		 //创建不同的文件夹目录
	//		 File file=new File(filePath);
	//		 //判断文件夹是否存在
	//		 if (!file.exists())
	//		{
	//			//如果文件夹不存在，则创建新的的文件夹
	//			 file.mkdirs();
	//		}
	//		 FileOutputStream fileOut = null;
	//		 HttpURLConnection conn = null;
	//		 InputStream inputStream = null;
	//		 try
	//		{
	//			 // 建立链接
	//			 URL httpUrl=new URL(url);
	//			 conn=(HttpURLConnection) httpUrl.openConnection();
	//			 //以Post方式提交表单，默认get方式
	//			 conn.setRequestMethod(method);
	//		     conn.setDoInput(true);  
	//		     conn.setDoOutput(true);
	//		     // post方式不能使用缓存 
	//		     conn.setUseCaches(false);
	//		     //连接指定的资源 
	//		     conn.connect();
	//		     //获取网络输入流
	//		     inputStream=conn.getInputStream();
	//		     BufferedInputStream bis = new BufferedInputStream(inputStream);
	//		     //判断文件的保存路径后面是否以/结尾
	////		     if (!filePath.endsWith("/")) {
	////
	////		    	 filePath += "/";
	////
	////		    	 }
	//		     //写入到文件（注意文件保存路径的后面一定要加上文件的名称）
	//	         fileOut = new FileOutputStream(filePath+filename);
	//	         BufferedOutputStream bos = new BufferedOutputStream(fileOut);
	//	         
	//	         byte[] buf = new byte[4096];
	//	         int length = bis.read(buf);
	//	         //保存文件
	//	         while(length != -1)
	//	         {
	//	        	 bos.write(buf, 0, length);
	//	        	 length = bis.read(buf);
	//	         }
	//	         bos.close();
	//	         bis.close();
	//	         conn.disconnect();
	//		} catch (Exception e)
	//		{
	//			 e.printStackTrace();
	//			 System.out.println("抛出异常！！");
	//		}
	//		 
	//		 return file;
	//		 
	//	 }
	//
	//	
	//	public static byte[] readAsByteArray(InputStream inStream)
	//			throws IOException {
	//		int size = 1024;
	//		byte[] ba = new byte[size];
	//		int readSoFar = 0;
	//		while (true) {
	//			int nRead = inStream.read(ba, readSoFar, size - readSoFar);
	//			if (nRead == -1) {
	//				break;
	//			}
	//			readSoFar += nRead;
	//			if (readSoFar == size) {
	//				int newSize = size * 2;
	//				byte[] newBa = new byte[newSize];
	//				System.arraycopy(ba, 0, newBa, 0, size);
	//				ba = newBa;
	//				size = newSize;
	//			}
	//		}
	//
	//		byte[] newBa = new byte[readSoFar];
	//		System.arraycopy(ba, 0, newBa, 0, readSoFar);
	//		return newBa;
	//	}

	public static byte[] openFile(String filePath) {
		try {
			String url = filePath.substring(0, filePath.lastIndexOf('/') + 1);
			String filename = filePath.substring(filePath.lastIndexOf('/') + 1);
			filename = URLEncoder.encode(filename, "UTF-8");
			filename = filename.replaceAll("\\+", "%20");
			URL httpUrl = new URL(url + filename);
			HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
			byte[] btImg = readInstream(inStream);//得到图片的二进制数据 return btImg;
			return btImg;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//读取流文件的内容
	private static byte[] readInstream(InputStream inputStream) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); //创建ByteArrayOutputStream 对象
		byte[] buffer = new byte[1024];//声明缓冲区
		int length = -1;//定义读取默认长度
		while ((length = inputStream.read(buffer)) != -1) {
			byteArrayOutputStream.write(buffer, 0, length);
			//把缓存区中输出到内存中
		}

		byteArrayOutputStream.close(); //关闭输出流
		inputStream.close(); //关闭输入流
		return byteArrayOutputStream.toByteArray();
	}

	public static void main(String[] aa) throws IOException {
        File file = new File("G:\\dx二级单位1_批量人员1_9987_男.jpg");
        String name="批量人员";
        String num="9987";
        String sex="男.jpg";
        for (int i=1;i<300;i++){
            File file1 = new File("G:\\imgs\\dx二级单位1_" + name + i + "_" + (Integer.parseInt(num) + i) + "_" + sex);
            copyFileUsingFileStreams(file,file1);
        }
    }

    private static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }


	public static String fileUpload(byte[] bytes, String fileUrl, String path) throws Exception {
		String resultSet = null;
		try {
			HttpURLConnection huc = (HttpURLConnection) new URL(
					fileUrl + "/upload_file.shtml?uploadChildPath=" + path + "")
							.openConnection();
			huc.setRequestMethod("POST");// 设置提交方式为post方式
			huc.setDoInput(true);
			huc.setDoOutput(true);// 设置允许output
			huc.setUseCaches(false);// POST不能使用缓存

			huc.setRequestProperty("Connection", "Keep-Alive");
			huc.setRequestProperty("Charset", "UTF-8");
			String boundary = "----------" + System.currentTimeMillis();
			huc.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			StringBuilder sb = new StringBuilder();
			sb.append("--"); // ////////必须多两道线
			sb.append(boundary);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"head.png\"\r\n");
			sb.append("Content-Type: application/octet-stream\r\n\r\n");

			OutputStream out = new DataOutputStream(huc.getOutputStream());
			out.write(sb.toString().getBytes("utf-8"));// 写入header
			out.write(bytes, 0, bytes.length);// 写入文件数据
			byte[] foot = ("\r\n--" + boundary + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
			out.write(foot);// 写入尾信息
			out.flush();
			out.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
			huc.connect();
			resultSet = br.readLine();
			br.close();
			huc.disconnect();
		} catch (Exception e) {
			throw e;
		}
		JSONObject s = (JSONObject) JSONObject.parse(resultSet);
		JSONArray joarr = (JSONArray) s.get("data");
		s = (JSONObject) joarr.get(0);
		return s.get("url").toString();
	}

	/**
	 * 解析文件
	 */
	public static List<String[]> readTxt(String filePath) {
		if (null == filePath) {
			throw new IllegalArgumentException("参数不能为空");
		}
		List<String[]> records = new LinkedList<>();
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
				br = new BufferedReader(isr);
				String lineTxt = null;
				while ((lineTxt = br.readLine()) != null) {
					String[] arrStrings = lineTxt.split("\\|");
					records.add(arrStrings);
				}
				br.close();
				isr.close();
				return records;
			} else {
				System.out.println("文件不存在!");
			}
		} catch (Exception e) {
			System.out.println("文件读取错误!" + e.getMessage());
		}
		return records;
	}

	/**
	 * 写入文件内容
	 */
	public static void writeFile(File filename, StringBuffer content) throws IOException {
		// 文件输出流
		FileOutputStream outstream = null;
		OutputStreamWriter write = null;

		// 文件输入流
		FileInputStream inputstream = null;
		// 字符流读取
		BufferedReader buffer = null;
		// 字节流读取
		InputStreamReader readerstream = null;

		String temp = "";
		StringBuffer buf = new StringBuffer();

		try {
			inputstream = new FileInputStream(filename);
			readerstream = new InputStreamReader(inputstream, "UTF-8");
			buffer = new BufferedReader(readerstream);
			for (int j = 1; (temp = buffer.readLine()) != null; j++) {
				buf = buf.append(temp);
				buf = buf.append("\n");
			}
			buf.append(content);
			outstream = new FileOutputStream(filename);
			write = new OutputStreamWriter(outstream, "UTF-8");
			write.write(buf.toString());
			write.flush();
		} finally {
			if (write != null) {
				write.close();
			}
			if (outstream != null) {
				outstream.close();
			}
			if (readerstream != null) {
				readerstream.close();
			}
			if (buffer != null) {
				buffer.close();
			}
			if (inputstream != null) {
				inputstream.close();
			}
		}
	}

	/**
	 * 删除单个文件
	 *
	 * @param fileName 被删除文件的文件名
	 * @return 单个文件删除成功返回true, 否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			return true;
		} else {
			return false;
		}
	}

    public static String readDir(String path) {
        // File对象 可以是文件或者目录
        File file = new File(path);
        if(file.isDirectory()) {
            File[] array = file.listFiles();
            for (int i = 0; i < array.length; i++) {
                if(array[i].isFile()) {
                    System.out.println("文件名称" + array[i].getName());
                    System.out.println("文件" + array[i]);
                    System.out.println("文件路径" + array[i].getPath());
                } else if(array[i].isDirectory()) {
                    readDir(array[i].getPath());
                }
            }
        }
        return null;
    }

}
