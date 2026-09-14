package vn.iotstar.models;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int categoryid;

	@NotBlank(message = "Tên danh mục không được để trống")
	private String categoryname;

	private String images;

	private int status = 1;

	private Boolean isEdit = false;
}
