package program_movil.neighborstalk;

public class listComments {

	protected String id;
	protected String content;
	protected String user_id;
	protected String event_id;
	
	public listComments(String id, String content, String user_id, String event_id) {
		this.id = id;
		this.content = content;
		this.user_id = user_id;
		this.event_id = event_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	
	
	
	
}
