package edu.mum.se.poseidon.web.services.dto;

public class UserDto {
	private String firstName;
    private String lastName;
    private long id;
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private String username;

	private String password;

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
