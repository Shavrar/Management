package Entities;


	

	public class User implements Entity {
		private Integer id;
	    private String login;
	    private String password;
	    private String role;
	    
	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }
	    
	    public String getLogin() {
	        return login;
	    }

	    public void setLogin(String login) {
	        this.login = login;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	    
	    public String getRole(){
	    	return role;
	    }
	    
	    public void setRole(String role){
	    	this.role=role;
	    }
	    @Override
		public Class getClassName() {
			return this.getClass();
		}

		@Override
		public String getStringName() {
			return "User";
		}
	}

