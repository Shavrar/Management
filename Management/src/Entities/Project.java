package Entities;

import java.sql.Date;

public class Project implements Entity{
	Integer id;
	String client;
	String name;
	Date begining;
	Date planed;
	Date real;
	// return true if x>=y else false
	public static Boolean compareDate(Date x,Date y){
		
		String Xday=(x.toString()).substring(8, 10);
		String Yday=(y.toString()).substring(8, 10);
		String Xmonth=(x.toString()).substring(5, 7);
		String Ymonth=(y.toString()).substring(5, 7);
		
			if(Integer.parseInt(Xmonth)>Integer.parseInt(Ymonth)){
				return true;
			}
			else if(Integer.parseInt(Xmonth)==Integer.parseInt(Ymonth)){
				if(Integer.parseInt(Xday)>=Integer.parseInt(Yday)){
					return true;
				}
			}
		

		return false;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBegining() {
		return begining;
	}
	public void setBegining(Date begining) {
		this.begining = begining;
	}
	public Date getPlaned() {
		return planed;
	}
	public void setPlaned(Date planed) {
		this.planed = planed;
	}
	public Date getReal() {
		return real;
	}
	public void setReal(Date real) {
		this.real = real;
	}
	
	/*public String getT() {
		return (planed.toString()).substring(8, 10);
	}*/
	
	public Boolean getFinished(){
		if(real==null) return false;
		
		if(Project.compareDate(planed, real)){
			return true;
		}
		else return false;
	}
	@Override
	public Class getClassName() {
		return this.getClass();
	}

	@Override
	public String getStringName() {
		return "Project";
	}
}
