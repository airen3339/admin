package beans;

public class Info{
	private String Info_ID;
	private String Info_Title;
	private String Info_Content;
	private String Info_Reader_ID;
	private String Info_Reader_Name;
	
	public Info(){}
	public Info(String id, String title, String Info_Content, String reader_id, String reader_name){
		this.Info_ID = id;
		this.Info_Title = title;
		this.Info_Content = Info_Content;
		this.Info_Reader_ID = reader_id;
		this.Info_Reader_Name = reader_name;
	}
	
	
	public boolean isViewable(String user_name){
		Boolean flag = false;
		String viewable_name = this.getInfo_Reader_Name();
		
		if(viewable_name == null){	// 如果可见人id为空，说明所有用户都可见
			flag = true;
		}else{							// 否则若可见人包含指定用户id，该用户可见
			if(viewable_name.contains(user_name)){
				flag = true;
			}
		}
		return flag;
	}
	
	
	
	public String getInfo_ID() {
		return Info_ID;
	}
	public void setInfo_ID(String info_ID) {
		Info_ID = info_ID;
	}
	public String getInfo_Title() {
		return Info_Title;
	}
	public void setInfo_Title(String info_Title) {
		Info_Title = info_Title;
	}
	public String getInfo_Content() {
		return Info_Content;
	}
	public void setInfo_Content(String info_Content) {
		Info_Content = info_Content;
	}
	public String getInfo_Reader_ID() {
		return Info_Reader_ID;
	}
	public void setInfo_Reader_ID(String info_Reader_ID) {
		Info_Reader_ID = info_Reader_ID;
	}
	public String getInfo_Reader_Name() {
		return Info_Reader_Name;
	}
	public void setInfo_Reader_Name(String info_Reader_Name) {
		Info_Reader_Name = info_Reader_Name;
	}
	
};