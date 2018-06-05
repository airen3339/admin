package beans;

public class Tiny {

	private String t_id;
	private String tiny_id;
	private String tiny_name;
	private String tiny_lastDate;
	private String tiny_param1;
	private String tiny_param2;
	
	public Tiny() {
	}
	
	public Tiny(String t_id, String tiny_id, String tiny_name, String tiny_lastDate) {
		super();
		this.t_id = t_id;
		this.tiny_id = tiny_id;
		this.tiny_name = tiny_name;
		this.tiny_lastDate = tiny_lastDate;
	}

	public String getT_id() {
		return t_id;
	}

	public void setT_id(String t_id) {
		this.t_id = t_id;
	}

	public String getTiny_id() {
		return tiny_id;
	}

	public void setTiny_id(String tiny_id) {
		this.tiny_id = tiny_id;
	}

	public String getTiny_name() {
		return tiny_name;
	}

	public void setTiny_name(String tiny_name) {
		this.tiny_name = tiny_name;
	}

	public String getTiny_lastDate() {
		return tiny_lastDate;
	}

	public void setTiny_lastDate(String tiny_lastDate) {
		this.tiny_lastDate = tiny_lastDate;
	}

	public String getTiny_param1() {
		return tiny_param1;
	}

	public void setTiny_param1(String tiny_param1) {
		this.tiny_param1 = tiny_param1;
	}

	public String getTiny_param2() {
		return tiny_param2;
	}

	public void setTiny_param2(String tiny_param2) {
		this.tiny_param2 = tiny_param2;
	}
	
	
}
