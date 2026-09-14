package vn.iotstar.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	// danh sách các thuộc tính
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productid;

	@Column(name = "productname", columnDefinition = "nvarchar(255)", nullable = false)
	private String productname;

	@Column(name = "price", nullable = false)
	private double price;

	@Column(name = "description", columnDefinition = "nvarchar(1000)")
	private String description;

	@Column(name = "image", columnDefinition = "nvarchar(500)")
	private String image;

	// thiết lập mối quan hệ
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryid", nullable = false)
	private Category category;

}