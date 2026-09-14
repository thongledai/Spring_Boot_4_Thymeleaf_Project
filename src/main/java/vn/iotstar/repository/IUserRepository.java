package vn.iotstar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.iotstar.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

	// Lấy danh sách người dùng mới nhất, có phân trang.
	@Query("SELECT u FROM User u ORDER BY u.createdate DESC, u.userid DESC")
	Page<User> findLatestUsers(Pageable pageable);

	// Tìm kiếm người dùng theo họ tên, tên đăng nhập, email,
	// số điện thoại hoặc trạng thái.
	@Query("SELECT u FROM User u WHERE " + "LOWER(u.fullname) LIKE LOWER(CONCAT('%', :keyword, '%')) "
			+ "OR LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')) "
			+ "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%')) "
			+ "OR LOWER(u.phone) LIKE LOWER(CONCAT('%', :keyword, '%')) "
			+ "OR CAST(u.status AS string) LIKE CONCAT('%', :keyword, '%') " + "ORDER BY u.userid DESC")
	Page<User> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

	// Tìm người dùng theo tên đăng nhập.
	User findByUsername(String username);
}