package Entities;




public class Client implements Entity {
    /**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private Integer id;
    private String adress;
    private String name;
    private Integer all;
    private Integer finished;
    
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
    
    

	@Override
	public Class getClassName() {
		return this.getClass();
	}

	@Override
	public String getStringName() {
		return "Client";
	}

	public Integer getFinished() {
		return finished;
	}

	public void setFinished(Integer finished) {
		this.finished = finished;
	}

	public Integer getAll() {
		return all;
	}

	public void setAll(Integer all) {
		this.all = all;
	}

}
