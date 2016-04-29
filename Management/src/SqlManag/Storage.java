package SqlManag;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Entities.Client;
import Entities.Project;
import Entities.User;







public class Storage {
    private static String jdbcUrl = null;
    private static String jdbcUser = null;
    private static String jdbcPassword = null;

    public static void init(String jdbcDriver,
                            String jdbcUrl,
                            String jdbcUser,
                            String jdbcPassword)
                                     throws ClassNotFoundException {
        Class.forName(jdbcDriver);
        Storage.jdbcUrl = jdbcUrl;
        Storage.jdbcUser = jdbcUser;
        Storage.jdbcPassword = jdbcPassword;
    }

    public static Collection<Client> readAllClients() throws SQLException {
        String sql = "select * from clients order by name";        
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            
            c.setAutoCommit(false);
            
            s = c.createStatement();
            r = s.executeQuery(sql);
            
            Collection<Client> clients = new ArrayList<>();
            
            while(r.next()) {
            	Client temp = new Client();
    			
                temp.setId(r.getInt("id"));
				                				
                temp.setName(r.getString("name"));
                
                temp.setAdress(r.getString("adress"));
				                             
                clients.add(temp);
            }
            c.commit();
            return clients;
        } 
        catch(SQLException e){
        	c.rollback();
        	return null;
        }      
        finally {
            try {
                r.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
            	
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }
    
    public static Collection<Project> readAllProjects(String b) throws SQLException {
        String sql = "select projects.* from projects join clients on clients.id = projects.client_id  where clients.name = ? order by projects.name";
        
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            
            c.setAutoCommit(false);
            
            s = c.prepareStatement(sql);
            
            s.setString(1, b);
            
            r = s.executeQuery();
            Collection<Project> projects = new ArrayList<>();
            while(r.next()) {
            	Project temp = new Project();
    			
            	
                temp.setId(r.getInt("id"));
                
                temp.setClient(b);
				                				
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
            c.commit();
            return projects;
        }
        catch(SQLException e){
        	c.rollback();
        	return null;
        }      
        finally {
            try {
                r.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
            	
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }
     
    public static Collection<User> readAllUsers() throws SQLException {
        String sql = "select * from users order by login";        
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            
            c.setAutoCommit(false);
            
            s = c.createStatement();
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
            c.commit();
            return users;
        } 
        catch(SQLException e){
        	c.rollback();
        	return null;
        }      
        finally {
            try {
                r.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
            	
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }

    public static Client readClientById(Integer id) throws SQLException {
        String sql = "select * from clients where id = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            
            c.setAutoCommit(false);
            
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            r = s.executeQuery();
            Client temp = new Client();
            
            if(r.next()) {

                temp.setId(r.getInt("id"));
                
                temp.setName(r.getString("name"));
				
                temp.setAdress(r.getString("adress"));

            }
            c.commit();
            return temp;
        }
        catch(SQLException e){
        	c.rollback();
        	return null;
        }      
        finally {
            try {
                r.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }
    
    public static Client readClientByName(String name) throws SQLException {
        String sql = "select * from clients where name = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            
            c.setAutoCommit(false);
            
            s = c.prepareStatement(sql);
            s.setString(1, name);
            r = s.executeQuery();
            Client temp = new Client();
            
            if(r.next()) {

                temp.setId(r.getInt("id"));
                
                temp.setName(r.getString("name"));
				
                temp.setAdress(r.getString("adress"));

            }
            c.commit();
            return temp;
        }
        catch(SQLException e){
        	c.rollback();
        	return null;
        }      
        finally {
            try {
                r.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }
    
    public static Project readProjectById(Integer id) throws SQLException {
        String sql = "select * from projects where id = ?";
        
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
        	
        	
        	
            c = getConnection();
            c.setAutoCommit(false);
            
            s = c.prepareStatement(sql);
            
            s.setInt(1, id);
            
            r = s.executeQuery();
            
            Project temp = new Project();
            
            if(r.next()) {

            	temp.setId(r.getInt("id"));
                
            	Integer tt = r.getInt("client_id");
            	
            	Client cl = Storage.readClientById(tt);
            	
                temp.setClient(cl.getName());
				                				
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
            c.commit();
            return temp;
        }
        catch(SQLException e){
        	c.rollback();
        	return null;
        }      
        finally {
            try {
                r.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }
    
    public static User readUserById(Integer id) throws SQLException {
        String sql = "select * from users where id = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            
            c.setAutoCommit(false);
            
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            r = s.executeQuery();
            User temp = new User();
            if(r.next()) {

                temp.setId(r.getInt("id"));
				
                temp.setLogin(r.getString("login"));
				
                temp.setPassword(r.getString("password"));
                
                temp.setRole(r.getString("role"));
  
            }
            return temp;
        } 
        catch(SQLException e){
        	c.rollback();
        	return null;
        }      
        finally {
            try {
                r.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
            	
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void createClient(Client client) throws SQLException {
        String sql = "INSERT INTO clients ( name, adress) VALUES "  
                   + "(?, ?)";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
           
            c.setAutoCommit(false);
            
            s = c.prepareStatement(sql);
            
            s.setString(1, client.getName());
            s.setString(2, client.getAdress());
            
            s.executeUpdate();
            c.commit();
            
        }
        catch(SQLException e){
        	c.rollback();       	
        }      
        finally {
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }
    
    public static void createProject(Project project) throws SQLException {
        String sql = "INSERT INTO projects (name, client_id, begining, planed, rreal, domain_name) VALUES "  
                   + "(?, ?, ?, ?, ?, ?)";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            
            Client cl = Storage.readClientByName(project.getClient());
            
            
            s = c.prepareStatement(sql);
            
            s.setString(1, project.getName());
            s.setInt(2, cl.getId());
            s.setDate(3, project.getBegining());
            s.setDate(4, project.getPlaned());
            if(project.getReal()==null){
            	s.setDate(5, null);	
            }
            else{
            	s.setDate(5, project.getReal());
            }
            
            s.setString(6, project.getDomain_name());
            
            s.executeUpdate();
            c.commit();
        }
        catch(SQLException e){
        	c.rollback();
        	
        }      
        finally {
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }
    
    
    public static void createUser(User user) throws SQLException {
        String sql = "INSERT INTO users ( login, password, role) VALUES "  
                   + "(?, ?, ?)";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            
            s = c.prepareStatement(sql);
            
            s.setString(1, user.getLogin());
            s.setString(2, user.getPassword());
            s.setString(3, user.getRole());
           
            
            s.executeUpdate();
            c.commit();
        }
        catch(SQLException e){
        	c.rollback();
        	
        }      
        finally {
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void updateClient(Client client) throws SQLException {
        String sql = "UPDATE clients SET "
                   + "name = ?, adress = ? "
                   + "WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            
            s = c.prepareStatement(sql);
           
            
            s.setString(1, client.getName());
            s.setString(2, client.getAdress());
            
            s.setInt(3, client.getId());
            s.executeUpdate();
            c.commit();
        }
        catch(SQLException e){
        	c.rollback();
        	
        }      
        finally {
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }
    
    public static void updateProject(Project project) throws SQLException {
        String sql = "UPDATE projects SET "
                   + "name = ?, begining = ?, planed = ?, rreal = ?, domain_name = ? "
                   + "WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            
            s = c.prepareStatement(sql);
           
            
            s.setString(1, project.getName());
            
            s.setDate(2, project.getBegining());
            s.setDate(3, project.getPlaned());
            if(project.getReal()==null){
            	s.setDate(4, null);	
            }
            else{
            	s.setDate(4, project.getReal());
            }
            s.setString(5, project.getDomain_name());
            s.setInt(6, project.getId());
            s.executeUpdate();
            c.commit();
        }
        catch(SQLException e){
        	c.rollback();
        	
        }      
        finally {
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }
    
    
    public static void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET "
                   + "login = ?, password = ?, role = ? "
                   + "WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            
            s = c.prepareStatement(sql);
           
            s.setString(1, user.getLogin());
            s.setString(2, user.getPassword());
            s.setString(3, user.getRole());
            s.setInt(4, user.getId());
            s.executeUpdate();
            c.commit();
        }
        catch(SQLException e){
        	c.rollback();
        	
        }      
        finally {
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }

    public static void deleteClient(Integer id) throws SQLException {
        String sql = "DELETE FROM clients "
                   + "WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            s.executeUpdate();
            c.commit();
            
        }
        catch(SQLException e){
        	c.rollback();
        	
        }      
        finally {
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }
    
    public static void deleteProject(Integer id) throws SQLException {
        String sql = "DELETE FROM projects "
                   + "WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            s.executeUpdate();
            c.commit();
        }
        catch(SQLException e){
        	c.rollback();
        	
        }      
        finally {
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }

    
    public static void deleteUser(Integer id) throws SQLException {
        String sql = "DELETE FROM users "
                   + "WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            s.executeUpdate();
            c.commit();
        }
        catch(SQLException e){
        	c.rollback();
        	
        }      
        finally {
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }
    
    
    public static String checkUser(User user) throws SQLException {
        String sql = "SELECT role "
                   + "FROM users "
                   + "WHERE login = ? AND password = ?";
        String admin="admin";
        String manager="manager";
        
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            
            s = c.prepareStatement(sql);
            s.setString(1, user.getLogin());
            s.setString(2, user.getPassword());
            r = s.executeQuery();
            c.commit();
            
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
        	c.rollback();
        	return null;
        }      
        finally {
            try {
                r.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }
    
    public static Integer Counta(String name) throws SQLException {
        String sql = "Select Count(*) as temp From projects join clients on clients.id = projects.client_id "        
                   + "where clients.name = '"+name+"'";  
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            
            s = c.createStatement();
            //s.setString(1, name);
            
            r = s.executeQuery(sql);
            
            Integer temp=0;
            while(r.next()) {
            	temp=r.getInt("temp");
            }
            c.commit();
            return temp;
        } 
        catch(SQLException e){
        	c.rollback();
        	return null;
        }      
        finally {
            try {
                r.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }
    
    public static Integer Countf(String name) throws SQLException {
        String sql = "Select Count(*) as temp From projects join clients on clients.id = projects.client_id "        
                   + "WHERE clients.name = '"+name+"' AND rreal IS NOT NULL";
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            
            s = c.createStatement();
           
            
            r = s.executeQuery(sql);
            
            Integer temp=0;
            while(r.next()) {
            	temp=r.getInt("temp");
            }
            c.commit();
            return temp;
        } 
        catch(SQLException e){
        	c.rollback();
        	return null;
        }      
        finally {
            try {
                r.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                s.close();
            } catch(NullPointerException | SQLException e) {}
            try {
                c.close();
            } catch(NullPointerException | SQLException e) {}
        }
    }
    
    public static Integer getNumber() {
       
            return 0;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl,
                                           jdbcUser,
                                           jdbcPassword);
    }
}