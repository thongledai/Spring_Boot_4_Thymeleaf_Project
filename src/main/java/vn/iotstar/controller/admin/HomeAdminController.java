package vn.iotstar.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeAdminController {

	@GetMapping({ "/admin/home", "/admin" })
	public String home() {
		// return "web/home";
		return "views/admin/index";
	}
}
