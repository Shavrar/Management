package dao.mySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import Entities.User;
import dao.UserDao;
import exception.DataException;


public class UserDaoImpl extends BaseDao implements UserDao {
	public UserDaoImpl(Connection connection) {
		super(connection);
		if(map==null)
			map = new HashMap<>();
	}

	@Override
	public Integer create(User user) throws DataException{
		String sql = "INSERT INTO users ( login, password, role) VALUES "  
                + "(?, ?, ?)";
    	
		PreparedStatement s = null;
	     try {
	         	        
	         s = connection.prepareStatement(sql);
	         
	         s.setString(1, user.getLogin());
	         s.setString(2, user.getPassword());
	         s.setString(3, user.getRole());
	        
	         
	         s.executeUpdate();
	         return 1;
	     }
	     catch(SQLException e){
	    	 throw new DataException(e);     	
	     }      
	     finally {
	         try {
	             s.close();
	         } catch(NullPointerException | SQLException e) {}	         
	     }
	}

	@Override
	public User read(Integer identity) throws DataException{
			String sql = "select * from users where id = ?";
	        
	        PreparedStatement s = null;
	        ResultSet r = null;
	        if(!map.containsKey(identity)){
	        try {
     
	            s = connection.prepareStatement(sql);
	            s.setInt(1, identity);
	            r = s.executeQuery();
	            User temp = new User();
	            if(r.next()) {

	                temp.setId(r.getInt("id"));
					
	                temp.setLogin(r.getString("login"));
					
	                temp.setPassword(r.getString("password"));
	                
	                temp.setRole(r.getString("role"));
	  
	            }
	            map.put(identity, temp);
	            return temp;
	        } 
	        catch(SQLException e){
	        	throw new DataException(e); 
	        }      
	        finally {
	            try {
	                r.close();
	            } catch(NullPointerException | SQLException e) {}
	            try {
	                s.close();
	            } catch(NullPointerException | SQLException e) {}	            
	        }
	        }
	        else{
	        	return (User) map.get(identity);
	        }
	}

	@Override
	public void update(User user) throws DataException{
		String sql = "UPDATE users SET "
                + "login = ?, password = ?, role = ? "
                + "WHERE id = ?";
     
     PreparedStatement s = null;
     try {
             
         s = connection.prepareStatement(sql);
        
         s.setString(1, user.getLogin());
         s.setString(2, user.getPassword());
         s.setString(3, user.getRole());
         s.setInt(4, user.getId());
         s.executeUpdate();  
         if(map.containsKey(user.getId())){
        	 map.replace(user.getId(), user);
         }
     }
     catch(SQLException e){
    	 throw new DataException(e);  	
     }      
     finally {
         try {
             s.close();
         } catch(NullPointerException | SQLException e) {}        
     }
	}

	@Override
	public void delete(Integer identity) throws DataException{
		String sql = "DELETE FROM users "
                + "WHERE id = ?";
	     
	     PreparedStatement s = null;
	     try {

	         s = connection.prepareStatement(sql);
	         s.setInt(1, identity);
	         s.executeUpdate();
	         if(map.containsKey(identity)){
	        	 map.remove(identity);
	         }
	     }
	     catch(SQLException e){
	    	 throw new DataException(e);     	
	     }      
	     finally {
	         try {
	             s.close();
	         } catch(NullPointerException | SQLException e) {}	         
	     }
	}

	@Override
	public String checkUser(User user) throws DataException{
		String sql = "SELECT role "
                + "FROM users "
                + "WHERE login = ? AND password = ?";
     String admin="admin";
     String manager="manager";
        
     PreparedStatement s = null;
     ResultSet r = null;
     try {
       
         s = connection.prepareStatement(sql);
         s.setString(1, user.getLogin());
         s.setString(2, user.getPassword());
         r = s.executeQuery();        
         
         if(r.next()) {
         	if(r.getString("role")==null){
         		return " ";
         	}
         	else if(r.getString("role").equalsIgnoreCase(admin)){
         		return admin;	
         	}
         	else if(r.getString("role").equalsIgnoreCase(manager)){
         		return manager;
         	}
         }
         return null;
     }
     catch(SQLException e){     	
    	 throw new DataException(e); 
     }      
     finally {
         try {
             r.close();
         } catch(NullPointerException | SQLException e) {}
         try {
             s.close();
         } catch(NullPointerException | SQLException e) {}         
     }
	}

	@Override
	public Collection<User> readAllUsers() throws DataException{
		String sql = "select * from users order by login";        
        
        Statement s = null;
        ResultSet r = null;
        try {          
        	
            s = connection.createStatement();
            r = s.executeQuery(sql);
            Collection<User> users = new ArrayList<>();
            while(r.next()) {
            	User temp = new User();
    			
                temp.setId(r.getInt("id"));
				
                temp.setLogin(r.getString("login"));
				
                temp.setPassword(r.getString("password"));
                
                temp.setRole(r.getString("role"));
				
                users.add(temp);
            }
            
            return users;
        } 
        catch(SQLException e){
        	throw new DataException(e); 
        }      
        finally {
            try {
                r.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            
        }
	}
}
