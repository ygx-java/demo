package com.base.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.base.mybatis.dto.User;

public interface UserDao {

	@Select("SELECT * FROM user WHERE age = #{age}")
	List<User> get(int age);

	@Insert("INSERT INTO user(name, age) VALUES (#{name}, #{age})")
	void insert(User user);
}
