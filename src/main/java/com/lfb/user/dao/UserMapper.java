package com.lfb.user.dao;

/**
 * User Dao类
 */

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.lfb.configure.PrimaryMapper;
import com.lfb.user.Authority;
import com.lfb.user.User;

@PrimaryMapper
@Mapper
public interface UserMapper {

	/**
	 * 根据用户名称查找用户
	 * @param username
	 * @return User
	 */
	@Select("SELECT * FROM users WHERE username = #{username}")
	User findUserByName(@Param("username") String username);
	
	@Select("SELECT * FROM users WHERE id = #{id}")
	User findUserById(@Param("id")int id);
	
	/**
	 * 检查是否已经存在同样的用户
	 * @param account
	 * @return
	 */
	@Select("SELECT 1 FROM users WHERE username = #{username}")
	Integer checkDuplicateUserName(@Param("username") String username);

	/**
	 * 注册用户
	 * @param user
	 */
	@Insert("INSERT INTO users(username, password, plain_password) VALUES (#{username}, #{password}, #{plain_password})")
	void addUser(User user);
	
	/**
	 * 更新用户信息,使用dynamic sql，根据user不同的内容生成不同的sql语句
	 * @param user
	 */
	@UpdateProvider(type=UpdateUserSqlGenerator.class, method="update")
	void updateUser(User user);
	
	/**
	 * 保存用户权限
	 * @param authority
	 */
	@Insert("INSERT INTO authorities VALUES (#{username}, #{authority})")
	void saveAuthority(Authority authority);
}
