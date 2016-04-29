package dao.mySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import Entities.Client;
import dao.ClientDao;
import exception.DataException;

public class ClientDaoImpl extends BaseDao implements ClientDao {

	public ClientDaoImpl(Connection connection) {
		super(connection);		
		if(map==null)
			map = new HashMap<>();
	}

	@Override
	public Integer create(Client entity) throws DataException {
		String sql = "INSERT INTO clients ( name, adress) VALUES "  
                + "(?, ?)";    
     PreparedStatement s = null;
     try {               
         s = connection.prepareStatement(sql);
         
         s.setString(1, entity.getName());
         s.setString(2, entity.getAdress());
         
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
	public Client read(Integer identity) throws DataException {
		String sql = "select * from clients where id = ?";        
        PreparedStatement s = null;
        ResultSet r = null;
        if(!map.containsKey(identity)){
        try {
            s = connection.prepareStatement(sql);
            s.setInt(1, identity);
            r = s.executeQuery();
            Client temp = new Client();
            
            if(r.next()) {

                temp.setId(r.getInt("id"));
                
                temp.setName(r.getString("name"));
				
                temp.setAdress(r.getString("adress"));

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
        	return (Client) map.get(identity);
        }
	}

	@Override
	public void update(Client entity) throws DataException {
		String sql = "UPDATE clients SET "
                + "name = ?, adress = ? "
                + "WHERE id = ?";    
     PreparedStatement s = null;
     try {
         
         s = connection.prepareStatement(sql);        
         s.setString(1, entity.getName());
         s.setString(2, entity.getAdress());       
         s.setInt(3, entity.getId());
         s.executeUpdate();
         if(map.containsKey(entity.getId())){
        	 map.replace(entity.getId(), entity);
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
	public void delete(Integer identity) throws DataException {
		String sql = "DELETE FROM clients "
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
	public Collection<Client> readAllClients() throws DataException {
		String sql = "select * from clients order by name";                
        Statement s = null;
        ResultSet r = null;
        try {         
            
            s = connection.createStatement();
            r = s.executeQuery(sql);
            
            Collection<Client> clients = new ArrayList<>();
            
            while(r.next()) {
            	Client temp = new Client();
    			
                temp.setId(r.getInt("id"));
				                				
                temp.setName(r.getString("name"));
                
                temp.setAdress(r.getString("adress"));
				                             
                clients.add(temp);
            }           
            return clients;
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
	public Client readByName(String name) throws DataException {
		 String sql = "select * from clients where name = ?";
	        
	        PreparedStatement s = null;
	        ResultSet r = null;
	        try {	            	            
	            s = connection.prepareStatement(sql);
	            s.setString(1, name);
	            r = s.executeQuery();
	            Client temp = new Client();
	            
	            if(r.next()) {

	                temp.setId(r.getInt("id"));
	                
	                temp.setName(r.getString("name"));
					
	                temp.setAdress(r.getString("adress"));

	            }	            
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

}
