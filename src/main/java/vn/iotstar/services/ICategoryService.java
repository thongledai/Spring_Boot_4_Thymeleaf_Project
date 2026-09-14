package vn.iotstar.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.iotstar.entity.Category;

public interface ICategoryService {

	// Lấy danh mục có phân trang
	Page<Category> findAll(Pageable pageable);

	// Tìm danh mục theo ID
	Optional<Category> findById(int id);

	// Thêm mới hoặc cập nhật danh mục
	Category save(Category category);

	// Xóa danh mục theo ID
	void delete(int id) throws Exception;

	// Đếm tổng số danh mục
	int count();

// Tìm danh mục theo key có phân trang
	Page<Category> findByKeyword(String keyword, Pageable page);

	Optional<Category> findByCategorynameIgnoreCase(String categoryname);

}