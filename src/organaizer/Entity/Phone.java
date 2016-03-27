package organaizer.Entity;

import javax.xml.bind.annotation.XmlType;

@XmlType(name="phone")
public class Phone {
	private String mobile;
	private String home;
	private String work;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	
	
	
	

}
