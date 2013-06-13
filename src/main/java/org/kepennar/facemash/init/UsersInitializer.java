package org.kepennar.facemash.init;

import javax.inject.Inject;
import javax.inject.Named;

import org.kepennar.facemash.model.security.Role;
import org.kepennar.facemash.model.security.UserAccount;
import org.kepennar.facemash.repository.security.RoleRepository;
import org.kepennar.facemash.repository.security.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.Index.Duplicates;
import org.springframework.data.mongodb.core.query.Order;



@Named("usersInitializer")
public class UsersInitializer implements InitializingBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersInitializer.class);
	
	@Inject 
	private RoleRepository roleRepository; 
	
	@Inject 
	private UserRepository userRepository;
	
	@Inject
	private MongoTemplate mongoTemplate;
	
	
    @Override
	public void afterPropertiesSet() {
    	
    	
    	
    	/* Clear */
    	mongoTemplate.dropCollection("role");
		mongoTemplate.dropCollection("userAccount");
		mongoTemplate.indexOps(UserAccount.class).ensureIndex(new Index().on("username", Order.DESCENDING).unique(Duplicates.DROP));
		
		/* ADMIN */
		mongoTemplate.insert(new Role(Role.ADMIN), "role");
		Role adminRole = roleRepository.findById(Role.ADMIN);
		
    	UserAccount admin = new UserAccount();
    	admin.setUsername("admin");
    	admin.setFirstname("Ad");
    	admin.setLastname("Min");
    	admin.setPassword("admin");
    	admin.addRole(adminRole);
    	LOGGER.debug("Save admin user : {}", admin);
    	userRepository.save(admin);
    	
    	/* USER */
    	mongoTemplate.insert(new Role(Role.USER), "role");
    	Role userRole = roleRepository.findById(Role.USER);
    	
    	UserAccount user = new UserAccount();
    	user.setUsername("user");
    	user.setFirstname("Simple");
    	user.setLastname("User");
    	user.setPassword("user");
    	user.addRole(userRole);
    	LOGGER.debug("Save user user : {}", user);
    	userRepository.save(user);
    	
    	/* VISITOR */
    	mongoTemplate.insert(new Role(Role.PUBLIC), "role");
    	Role visitorRole = roleRepository.findById(Role.PUBLIC);
    	
    	UserAccount visitor = new UserAccount();
    	visitor.setUsername("visitor");
    	visitor.setFirstname("Who");
    	visitor.setLastname("AreYou");
    	visitor.setPassword("visitor");
    	visitor.addRole(visitorRole);
    	LOGGER.debug("Save visitor user : {}", visitor);
    	userRepository.save(visitor);
    	
    }
}
