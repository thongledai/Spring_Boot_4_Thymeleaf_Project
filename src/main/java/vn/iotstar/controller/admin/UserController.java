//package vn.iotstar.controller.admin;
//
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.StringUtils;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import vn.iotstar.entity.User;
//import vn.iotstar.models.UserModel;
//import vn.iotstar.services.IRoleService;
//import vn.iotstar.services.IUserService;
//
//@Controller
//@RequestMapping("/admin/users")
//@RequiredArgsConstructor
//public class UserController {
//
//	private final IUserService userService;
//	private final IRoleService roleService;
//
//	// Hiển thị danh sách và tìm kiếm phân trang
//	@GetMapping({ "", "/", "/searchpaginated" })
//	public String search(Model model,
//			@RequestParam(name = "keyword", required = false) String keyword,
//			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
//			@RequestParam(name = "size", required = false, defaultValue = "5") int size) {
//
//		int currentPage = page > 0 ? page - 1 : 0;
//		Pageable pageable = PageRequest.of(currentPage, size);
//
//		Page<User> userPage = userService.searchByKeyword(keyword, pageable);
//
//		int totalPages = userPage.getTotalPages();
//		if (totalPages > 0) {
//			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
//			model.addAttribute("pageNumbers", pageNumbers);
//		}
//
//		model.addAttribute("userPage", userPage);
//		model.addAttribute("keyword", keyword);
//		model.addAttribute("size", size);
//		model.addAttribute("page", page);
//
//		return "admin/user/list";
//	}
//
//	// Mở form thêm người dùng
//	@GetMapping("/add")
//	public String add(Model model) {
//		UserModel user = new UserModel();
//		user.setIsEdit(false);
//		model.addAttribute("user", user);
//		model.addAttribute("roles", roleService.findAll());
//		return "admin/user/addoredit";
//	}
//
//	// Mở form chỉnh sửa người dùng
//	@GetMapping("/edit/{userid}")
//	public String edit(@PathVariable("userid") int userid, Model model,
//			RedirectAttributes redirectAttributes) {
//
//		User entity = userService.findById(userid);
//		if (entity != null) {
//			UserModel user = new UserModel();
//			BeanUtils.copyProperties(entity, user);
//			if (entity.getRole() != null) {
//				user.setRoleid(entity.getRole().getRoleid());
//			}
//			user.setIsEdit(true);
//			model.addAttribute("user", user);
//			model.addAttribute("roles", roleService.findAll());
//			return "admin/user/addoredit";
//		}
//
//		redirectAttributes.addFlashAttribute("message", "Người dùng không tồn tại!");
//		return "redirect:/admin/users/searchpaginated";
//	}
//
//	// Thêm mới hoặc cập nhật người dùng
//	@PostMapping("/saveOrUpdate")
//	public String saveOrUpdate(@Valid @ModelAttribute("user") UserModel userModel,
//			BindingResult result, RedirectAttributes redirectAttributes, Model model) {
//
//		if (result.hasErrors()) {
//			model.addAttribute("roles", roleService.findAll());
//			return "admin/user/addoredit";
//		}
//
//		User entity = new User();
//		BeanUtils.copyProperties(userModel, entity);
//
//		// Gán Role nếu chọn
//		if (userModel.getRoleid() > 0) {
//			roleService.findById(userModel.getRoleid()).ifPresent(entity::setRole);
//		}
//
//		// Xử lý mật khẩu và ngày tạo khi cập nhật
//		if (Boolean.TRUE.equals(userModel.getIsEdit())) {
//			User existing = userService.findById(userModel.getUserid());
//			if (existing != null) {
//				if (!StringUtils.hasText(userModel.getPassword())) {
//					entity.setPassword(existing.getPassword());
//				}
//				if (entity.getCreatedate() == null) {
//					entity.setCreatedate(existing.getCreatedate());
//				}
//			}
//		} else {
//			if (entity.getCreatedate() == null) {
//				entity.setCreatedate(new Date());
//			}
//		}
//
//		userService.save(entity);
//
//		String message = Boolean.TRUE.equals(userModel.getIsEdit())
//				? "Cập nhật người dùng thành công!"
//				: "Thêm mới người dùng thành công!";
//		redirectAttributes.addFlashAttribute("message", message);
//
//		return "redirect:/admin/users/searchpaginated";
//	}
//
//	// Xóa người dùng
//	@GetMapping("/delete/{userid}")
//	public String delete(@PathVariable("userid") int userid,
//			RedirectAttributes redirectAttributes) {
//
//		try {
//			userService.deleteById(userid);
//			redirectAttributes.addFlashAttribute("message", "Xóa người dùng thành công!");
//		} catch (Exception e) {
//			redirectAttributes.addFlashAttribute("message", "Xóa người dùng thất bại: " + e.getMessage());
//		}
//
//		return "redirect:/admin/users/searchpaginated";
//	}
//}