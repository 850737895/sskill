package com.hnnd.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hnnd.entity.SSkillUser;

public class UserUtil {
	
	private static void createUser(int count) throws Exception{
		List<SSkillUser> users = new ArrayList<SSkillUser>(count);
		//生成用户
		for(int i=0;i<count;i++) {
			SSkillUser user = new SSkillUser();
			user.setUserId(13000000000L+i);
			user.setLoginCount(1);
			user.setNickName("user"+i);
			user.setRegisterTime(new Date());
			user.setSalt("1a2b3c");
			user.setHeadImg("http://www.baiduxxx.img");
			user.setPassword(MD5Utils.lastMd5("123456", user.getSalt()));
			user.setLastLoginTime(new Date());
			users.add(user);

		}
/*		System.out.println("create user");
		//插入数据库
		Connection conn = com.hnnd.util.DBUtil.getConn();
		String sql = "insert into sskill_user(login_count, nick_name, register_time, salt, password, user_id,head_img,last_login_time)values(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for(int i=0;i<users.size();i++) {
			SSkillUser user = users.get(i);
			pstmt.setInt(1, user.getLoginCount());
			pstmt.setString(2, user.getNickName());
			pstmt.setTimestamp(3, new Timestamp(user.getRegisterTime().getTime()));
			pstmt.setString(4, user.getSalt());
			pstmt.setString(5, user.getPassword());
			pstmt.setLong(6, user.getUserId());
			pstmt.setString(7,user.getHeadImg());
			pstmt.setTimestamp(8, new Timestamp(user.getLastLoginTime().getTime()));
			System.out.println("insert to db"+i);
			pstmt.addBatch();
		}
		pstmt.executeBatch();
		pstmt.close();
		conn.close();
		System.out.println("insert to db");*/
		//登录，生成token
		String urlString = "http://localhost:8080/login/doLogin";
		File file = new File("D:/tokens.txt");
		if(file.exists()) {
			file.delete();
		}
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		file.createNewFile();
		raf.seek(0);
		for(int i=0;i<users.size();i++) {
			SSkillUser user = users.get(i);
			URL url = new URL(urlString);
			HttpURLConnection co = (HttpURLConnection)url.openConnection();
			co.setRequestMethod("POST");
			co.setDoOutput(true);
			OutputStream out = co.getOutputStream();
			String params = "mobile="+user.getUserId()+"&password="+MD5Utils.firstMd5("123456");
			out.write(params.getBytes());
			out.flush();
			InputStream inputStream = co.getInputStream();
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			byte buff[] = new byte[1024];
			int len = 0;
			while((len = inputStream.read(buff)) >= 0) {
				bout.write(buff, 0 ,len);
			}
			inputStream.close();
			bout.close();
			String response = new String(bout.toByteArray());
			JSONObject jo = JSON.parseObject(response);
			String token = jo.getString("data");
			System.out.println("create token : " + user.getUserId());
			
			String row = user.getUserId()+","+token;
			raf.seek(raf.length());
			raf.write(row.getBytes());
			raf.write("\r\n".getBytes());
			System.out.println("write to file : " + user.getUserId());
		}
		raf.close();
		
		System.out.println("over");
	}
	
	public static void main(String[] args)throws Exception {
		createUser(5000);
	}
}
