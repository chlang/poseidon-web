package edu.mum.se.poseidon.web.models;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Yuriy Yugay on 10/11/2017.
 *
 * @author Yuriy Yugay
 */
public class User {
	private String firstName;
    private String lastName;
    
	@NotEmpty
	private String username;
    
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String type;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
