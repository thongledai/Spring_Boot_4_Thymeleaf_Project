package vn.iotstar.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "roles")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	// danh sách các thuộc tính
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleid;

	@Column(name = "rolename", columnDefinition = "nvarchar(50)", nullable = false)
	private String rolename;

	// thiết lập mối quan hê
	@OneToMany(mappedBy = "role")
	private List<User> users;

}