package vn.iotstar.services.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import vn.iotstar.entity.Category;
import vn.iotstar.repository.ICategoryRepository;
import vn.iotstar.services.ICategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

	private final ICategoryRepository categoryRepository;

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	@Override
	public Optional<Category> findById(int id) {
		return categoryRepository.findById(id);
	}

	@Override
	@Transactional
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	@Transactional
	public void delete(int id) throws Exception {
		if (!categoryRepository.existsById(id)) {
			throw new Exception("Không tìm thấy danh mục với ID = " + id);
		}
		categoryRepository.deleteById(id);
	}

	@Override
	public int count() {
		return (int) categoryRepository.count();
	}

	@Override
	public Page<Category> findByKeyword(String keyword, Pageable page) {
		return categoryRepository.findByCategorynameContainingIgnoreCase(keyword, page);
	}

	@Override
	public Optional<Category> findByCategorynameIgnoreCase(String categoryname) {
		return categoryRepository.findByCategorynameIgnoreCase(categoryname);
	}
}
