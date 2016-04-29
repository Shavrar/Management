package dao.mySql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import Entities.Project;
import dao.ProjectDao;
import exception.DataException;

public class ProjectDaoImpl extends BaseDao implements ProjectDao {

	public ProjectDaoImpl(Connection connection) {
		super(connection);
		if(map==null)
		map = new HashMap<>();
	}

	@Override
	public Integer create(Project entity) throws DataException{
		 String sql = "INSERT INTO projects (name, client_id, begining, planed, rreal, domain_name) VALUES "  
                 + "(?, (SELECT id FROM clients WHERE  name = ?), ?, ?, ?, ?)";
	
      PreparedStatement s = null;
      try {                
          
    	  s = connection.prepareStatement(sql);   
          s.setString(1, entity.getName());
          s.setString(2, entity.getClient());
          s.setDate(3, entity.getBegining());
          s.setDate(4, entity.getPlaned());
          if(entity.getReal()==null){
          	s.setDate(5, null);	
          }
          else{
          	s.setDate(5, entity.getReal());
          }
          
          s.setString(6, entity.getDomain_name());
          
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
	public Project read(Integer identity) throws DataException{
		String sql = "select projects.*,clients.name as N from projects join clients on clients.id = projects.client_id  where projects.id = ?";
        
		if(!map.containsKey(identity)){
        PreparedStatement s = null;
        ResultSet r = null;
        try {
          
            s = connection.prepareStatement(sql);
            
            s.setInt(1, identity);
            
            r = s.executeQuery();
            
            Project temp = new Project();
            
            if(r.next()) {

            	temp.setId(r.getInt("id"));
                            	            	            	          	
                temp.setClient(r.getString("N"));
				                				
                temp.setName(r.getString("name"));
                
                temp.setDomain_name(r.getString("domain_name"));
                
                temp.setBegining(Date.valueOf(r.getString("begining")));
                
                temp.setPlaned(Date.valueOf(r.getString("planed")));
                
                String k=r.getString("rreal");
                
                if(k==null){
                	Date t = null;
                	temp.setReal(t);
                }
                else{
                	temp.setReal(Date.valueOf(r.getString("rreal")));
                }

            } 
            map.put(temp.getId(), temp);
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
		else
		{
			return (Project) map.get(identity);
		}
	}

	@Override
	public void update(Project entity) throws DataException{
		 String sql = "UPDATE projects SET "
                 + "name = ?, begining = ?, planed = ?, rreal = ?, domain_name = ? "
                 + "WHERE id = ?";     
      PreparedStatement s = null;
      
      try {

          s = connection.prepareStatement(sql);
                
          s.setString(1, entity.getName());
          
          s.setDate(2, entity.getBegining());
          s.setDate(3, entity.getPlaned());
          if(entity.getReal()==null){
          	s.setDate(4, null);	
          }
          else{
          	s.setDate(4, entity.getReal());
          }
          s.setString(5, entity.getDomain_name());
          s.setInt(6, entity.getId());
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
	public void delete(Integer identity) throws DataException{
		 String sql = "DELETE FROM projects "
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
	public Collection<Project> readAllProjects(String nameOfClient) throws DataException{
		String sql = "select projects.* from projects join clients on clients.id = projects.client_id  where clients.name = ? order by projects.name";
             
        PreparedStatement s = null;
        ResultSet r = null;
        try {
                       
            s = connection.prepareStatement(sql);
            
            s.setString(1, nameOfClient);
            
            r = s.executeQuery();
            Collection<Project> projects = new ArrayList<>();
            while(r.next()) {
            	Project temp = new Project();
    			          	
                temp.setId(r.getInt("id"));
                
                temp.setClient(nameOfClient);
				                				
                temp.setName(r.getString("name"));
                
                temp.setBegining(Date.valueOf(r.getString("begining")));
                
                temp.setPlaned(Date.valueOf(r.getString("planed")));
                
                temp.setDomain_name(r.getString("domain_name"));
                
                String k=r.getString("rreal");
       
                if(k==null){
                	Date t = null;
                	temp.setReal(t);
                }
                else{
                	temp.setReal(Date.valueOf(r.getString("rreal")));
                }
				                             
                projects.add(temp);
            }           
            return projects;
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
	public Integer getNumberOfAllProjects(String nameOfClient) throws DataException{
		String sql = "Select Count(*) as temp From projects join clients on clients.id = projects.client_id "        
                + "where clients.name = '"+nameOfClient+"'";  
     Statement s = null;
     ResultSet r = null;
     try {
         s = connection.createStatement();
         //s.setString(1, name);
         
         r = s.executeQuery(sql);
         
         Integer temp=0;
         while(r.next()) {
         	temp=r.getInt("temp");
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

	@Override
	public Integer getNumberOfFinishedProjects(String nameOfClient) throws DataException{
		 String sql = "Select Count(*) as temp From projects join clients on clients.id = projects.client_id "        
                 + "WHERE clients.name = '"+nameOfClient+"' AND rreal IS NOT NULL";      
      Statement s = null;
      ResultSet r = null;
      try {
                  
          s = connection.createStatement();
                
          r = s.executeQuery(sql);
          
          Integer temp=0;
          while(r.next()) {
          	temp=r.getInt("temp");
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
