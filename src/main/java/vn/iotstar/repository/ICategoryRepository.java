package vn.iotstar.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.iotstar.entity.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
// có sẵn các hàm
//	cfindAll();
//	findById(id);
//	save(category);
//	deleteById(id);
//	count();
//	existsById(id)
//	findAll(Pageable pageable)

	// Tìm kiếm danh mục theo tên, hình ảnh hoặc trạng thái, có phân trang.
	@Query("SELECT c FROM Category c WHERE " + "LOWER(c.categoryname) LIKE LOWER(CONCAT('%', :keyword, '%')) "
			+ "OR LOWER(c.images) LIKE LOWER(CONCAT('%', :keyword, '%')) "
			+ "OR CAST(c.status AS string) LIKE CONCAT('%', :keyword, '%') " + "ORDER BY c.categoryid DESC")
	Page<Category> findByKeyword(@Param("keyword") String keyword, Pageable page);

	Optional<Category> findByCategorynameIgnoreCase(String categoryname);
}