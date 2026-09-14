package vn.iotstar.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.iotstar.entity.User;

public interface IUserService {

	Page<User> findAll(Pageable pageable);

	Page<User> searchByKeyword(String keyword, Pageable pageable);

	Page<User> findLatestUsers(Pageable pageable);

	User findById(int id);

	User findByUsername(String username);

	User save(User user);

	void deleteById(int id) throws Exception;

	long count();

	boolean existsById(int id);
}