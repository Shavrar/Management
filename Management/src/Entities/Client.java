package Entities;

import java.sql.SQLException;

import SqlManag.Storage;

public class Client implements Entity {
    /**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private Integer id;
    private String adress;
    private String name;
    

    public Integer getId() {
        return id;
        
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }
    
    public String getAdress() {
        return adress;
    }
    
    public void setAdress(String adress) {
        this.adress=adress;
    }
    
    public Integer getCounta() throws SQLException{
    	
    	return Storage.Counta(name);
    	
    }
    
    public Integer getCountf() throws SQLException{
    	
    	return Storage.Countf(name);
    }

	@Override
	public Class getClassName() {
		return this.getClass();
	}

	@Override
	public String getStringName() {
		return "Client";
	}

}
