package vn.iotstar.services;

import java.util.List;
import java.util.Optional;

import vn.iotstar.entity.Role;

public interface IRoleService {

	List<Role> findAll();

	Optional<Role> findById(int id);
}
