package spring.service.user;

import org.apache.ibatis.annotations.Mapper;

import spring.domain.User;

@Mapper
public interface NaverDao {
	public void addUser(User user) throws Exception ;
	
	public User getUser(String userId) throws Exception;
	
	public boolean checkDuplication(String userId) throws Exception;
}
