package com.lfb.user.dao;

import com.lfb.user.User;


public class UpdateUserSqlGenerator {

	public static String update(User user){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("UPDATE users SET ");
		if(user.getAvatar() != null){
			sb.append("avatar = #{avatar}, ");
		}
		if(user.getAddress() != null){
			sb.append("address = #{address}, ");
		}
		
		if(user.getGender() != -1){
			sb.append("gender = #{gender}, ");
		}
		
		if(user.getNickname() != null){
			sb.append("nickname = #{nickname}, ");
		}
		
		if(user.getProvince() != null){
			sb.append("province = #{province}");
		}
		
		//删除多余的间隔符号
		if(sb.toString().endsWith(", ")){
			sb.delete(sb.length()-2, sb.length()-1);
		}
		
		sb.append(" WHERE id = #{id}");
		System.out.println(sb.toString());
		
		return sb.toString();
	}
	
}
