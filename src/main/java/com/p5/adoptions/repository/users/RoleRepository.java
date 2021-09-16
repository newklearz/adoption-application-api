package com.p5.adoptions.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>
{
    Optional<Role> findByRole (RoleEnum rolesEnum);
}
