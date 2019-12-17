package com.base.databackup.dao;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.base.databackup.dto.Person;


@Repository
public interface PersonDao {

	@Select("SELECT * FROM cast_user_tab WHERE name = #{name}")
	List<Person> get(String name);
}
