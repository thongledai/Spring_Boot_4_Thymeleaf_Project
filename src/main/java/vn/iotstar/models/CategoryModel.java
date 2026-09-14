package vn.iotstar.models;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int categoryid;

	@NotEmpty(message = "Tên danh mục không được để trống")
	private String categoryname;

	private String images;

	private int status = 1;

	private Boolean isEdit = false;
}
