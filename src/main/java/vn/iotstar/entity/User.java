package vn.iotstar.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	// danh sách các thuộc tính
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;

	@Column(name = "phone", columnDefinition = "nvarchar(255)")
	private String phone;

	@Column(name = "username", columnDefinition = "nvarchar(255)", nullable = false)
	private String username;

	@Column(name = "password", columnDefinition = "nvarchar(255)", nullable = false)
	private String password;

	@Column(name = "fullname", columnDefinition = "nvarchar(255)", length = 50)
	private String fullname;

	@Column(name = "email", columnDefinition = "nvarchar(255)", nullable = false)
	private String email;

	@Column(name = "avatar", columnDefinition = "nvarchar(255)", length = 50)
	private String avatar;

	@Column(name = "code", columnDefinition = "nvarchar(255)", length = 50)
	private String code;

	@Column(name = "createdate")
	private Date createdate;

	@Column(name = "sell_id")
	private Integer sellId;

	@Column(name = "status")
	private int status;

	// thiết lập mối quan hê
	@ManyToOne
	@JoinColumn(name = "roleid")
	private Role role;

	@Transient
	private String OTP;

}