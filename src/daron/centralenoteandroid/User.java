package daron.centralenoteandroid;

public class User {
	private String _id;
	private String _name;
	private String _debt;
	
	public String getId() {
		return _id;
	}
	
	public String getName() {
		return _name;
	}
	
	public String getDebt() {
		return _debt;
	}
	
	public void setId(String id) {
		_id = id;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public void setDebt(String debt) {
		_debt = debt;
	}
}
