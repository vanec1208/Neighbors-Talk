package program_movil.neighborstalk;

public class listUsers {

	protected String id;
	protected String name;
	protected String userName;
	
	public listUsers(String id, String name, String userName) {
		this.id = id;
		this.name = name;
		this.userName = userName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
