package vn.iotstar.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.iotstar.entity.Category;
import vn.iotstar.models.CategoryModel;
import vn.iotstar.services.ICategoryService;

@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final ICategoryService categoryService;

	// Hien thi danh sach, tim kiem va phan trang
	@GetMapping({ "", "/", "/search", "/searchpaginated" })
	public String search(Model model,
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size) {

		int currentPage = page > 0 ? page - 1 : 0;
		Pageable pageable = PageRequest.of(currentPage, size, Sort.by("categoryid").descending());

		Page<Category> categoryPage;
		if (StringUtils.hasText(keyword)) {
			categoryPage = categoryService.findByKeyword(keyword.trim(), pageable);
		} else {
			categoryPage = categoryService.findAll(pageable);
		}

		int totalPages = categoryPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("categoryPage", categoryPage);
		model.addAttribute("keyword", keyword != null ? keyword : "");
		model.addAttribute("size", size);
		model.addAttribute("currentPage", page);

		return "admin/categories/search";
	}

	// Mo form them moi danh muc
	@GetMapping("/add")
	public String add(Model model) {
		CategoryModel category = new CategoryModel();
		category.setIsEdit(false);
		category.setImages("dogiadung.jpg");
		category.setStatus(1);
		model.addAttribute("category", category);
		return "admin/categories/addOrEdit";
	}

	// Mo form cap nhat danh muc
	@GetMapping("/edit/{categoryid}")
	public String edit(@PathVariable("categoryid") int categoryid, Model model,
			RedirectAttributes redirectAttributes) {

		Optional<Category> opt = categoryService.findById(categoryid);
		if (opt.isPresent()) {
			Category entity = opt.get();
			CategoryModel category = new CategoryModel();
			BeanUtils.copyProperties(entity, category);
			category.setIsEdit(true);
			model.addAttribute("category", category);
			return "admin/categories/addOrEdit";
		}

		redirectAttributes.addFlashAttribute("errorMessage", "Khong tim thay danh muc voi ID = " + categoryid);
		return "redirect:/admin/categories";
	}

	// Them moi hoac cap nhat danh muc
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(@Valid @ModelAttribute("category") CategoryModel categoryModel,
			BindingResult result, RedirectAttributes redirectAttributes, Model model) {

		if (result.hasErrors()) {
			return "admin/categories/addOrEdit";
		}

		Category entity = new Category();
		BeanUtils.copyProperties(categoryModel, entity);
		categoryService.save(entity);

		String message = Boolean.TRUE.equals(categoryModel.getIsEdit()) 
				? "Cap nhat danh muc thanh cong!" 
				: "Them moi danh muc thanh cong!";
		redirectAttributes.addFlashAttribute("message", message);

		return "redirect:/admin/categories";
	}

	// Xoa danh muc
	@GetMapping("/delete/{categoryid}")
	public String delete(@PathVariable("categoryid") int categoryid,
			RedirectAttributes redirectAttributes) {

		try {
			categoryService.delete(categoryid);
			redirectAttributes.addFlashAttribute("message", "Xoa danh muc thanh cong!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Xoa danh muc that bai: " + e.getMessage());
		}

		return "redirect:/admin/categories";
	}
}