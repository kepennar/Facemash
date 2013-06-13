package org.kepennar.facemash.model.security;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.common.base.Objects;

@Document
public class Role {
	
	@Transient
	public transient static final String ADMIN = "ROLE_ADMIN";
	@Transient
	public transient static final String USER = "ROLE_USER";
	@Transient
	public transient static final String PUBLIC = "ROLE_PUBLIC";
	
	
	@Id
	private String id;
		
	public Role() { }
	
	public Role(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public boolean equals(Object obj) {
        if (!(obj instanceof Role)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Role rhs = (Role) obj;
        return new EqualsBuilder().append(this.id, rhs.getId()).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(this.id).toHashCode();
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(Role.class)
				.add("id", this.id)
			.toString();
	}
	
	

}