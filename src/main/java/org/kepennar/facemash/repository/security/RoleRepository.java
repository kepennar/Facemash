package org.kepennar.facemash.repository.security;

import org.kepennar.facemash.model.security.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends MongoRepository<Role, String> { 
	Role findById(String roleId);
}
