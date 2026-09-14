package vn.iotstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.iotstar.entity.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
}
