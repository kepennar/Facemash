package org.kepennar.facemash.repository.security;

import org.kepennar.facemash.model.security.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserAccount, String> {
 
	UserAccount findByUsername(final String username);
}
