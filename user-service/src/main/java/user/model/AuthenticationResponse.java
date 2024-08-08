package user.model;

public class AuthenticationResponse {

    private String jwtToken;
    private String username;
    
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public AuthenticationResponse(Builder builder) {
		 this.jwtToken = builder.jwtToken;
	     this.username = builder.username;
	}
	
	public AuthenticationResponse() {
		
	}
	 // Builder Class
    public static class Builder {
        private String jwtToken;
        private String username;

        public Builder setJwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(this);
        }
    }
    
    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "jwtToken='" + jwtToken + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
