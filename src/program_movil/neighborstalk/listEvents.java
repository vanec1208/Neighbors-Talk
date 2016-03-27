package program_movil.neighborstalk;

public class listEvents {
	
	protected String id;
	protected String title;
	protected String description;
	protected String date;
	protected String time;
	protected String place;
	protected String user_id;
	
	
	public listEvents(String id, String title, String description, String date, String time, String place, String user_id) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.time = time;
		this.place = place;
		this.user_id = user_id;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	
	
	
}
