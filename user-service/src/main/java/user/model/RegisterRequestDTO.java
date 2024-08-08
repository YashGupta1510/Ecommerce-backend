package user.model;

public class RegisterRequestDTO {

    private String email;
    private String password;
    private String role;
    private String name;
    
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "RegisterUserDTO [email=" + email + ", password=" + password + ", role=" + role + ", name=" + name + "]";
	}
	

}