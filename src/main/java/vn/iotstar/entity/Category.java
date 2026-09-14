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
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

//danh sách các thuộc tính

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryid;

	@Column(name = "categoryname", columnDefinition = "nvarchar(255)", nullable = false)
	private String categoryname;

	@Column(name = "images", columnDefinition = "nvarchar(255)")
	private String images;

	@Column(name = "status", columnDefinition = "nvarchar(255)")
	private int status;

	// thiết lập mối quan hê
	@OneToMany(mappedBy = "category")
	private List<Product> products;

}