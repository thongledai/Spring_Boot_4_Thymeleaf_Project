package vn.iotstar.models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iotstar.entity.Role;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int userid;

	@NotEmpty(message = "Tên đăng nhập không được để trống")
	private String username;

	private String password;

	private String fullname;

	@NotEmpty(message = "Email không được để trống")
	private String email;

	private String phone;

	private String avatar;

	private String code;

	private Date createdate;

	private Integer sellId;

	private int status = 1;

	private int roleid;

	private Role role;

	private Boolean isEdit = false;
}
