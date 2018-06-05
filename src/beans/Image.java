package beans;

public class Image {
	
	private String Image_ID;
	private String Image_Name;
	private String Image_Path;
	private String Image_Ext;
	private String Image_Size;
	private String Image_Target_Link;
	private String Image_Is_Used;
	
	public Image(String Image_ID, String Image_Name, String Image_Path, String Image_Ext, String Image_Size, String Image_Target_Link, String Image_Is_Used){
		this.Image_ID = Image_ID;
		this.Image_Name = Image_Name;
		this.Image_Path = Image_Path;
		this.Image_Ext = Image_Ext;
		this.Image_Size = Image_Size;
		this.Image_Target_Link = Image_Target_Link;
		this.Image_Is_Used = Image_Is_Used;
	}

	public String getImage_ID() {
		return Image_ID;
	}

	public String getImage_Is_Used() {
		return Image_Is_Used;
	}

	public void setImage_Is_Used(String image_Is_Used) {
		Image_Is_Used = image_Is_Used;
	}

	public void setImage_ID(String image_ID) {
		Image_ID = image_ID;
	}

	public String getImage_Name() {
		return Image_Name;
	}

	public void setImage_Name(String image_Name) {
		Image_Name = image_Name;
	}

	public String getImage_Path() {
		return Image_Path;
	}

	public void setImage_Path(String image_Path) {
		Image_Path = image_Path;
	}

	public String getImage_Ext() {
		return Image_Ext;
	}

	public void setImage_Ext(String image_Ext) {
		Image_Ext = image_Ext;
	}

	public String getImage_Size() {
		return Image_Size;
	}

	public void setImage_Size(String image_Size) {
		Image_Size = image_Size;
	}

	public String getImage_Target_Link() {
		return Image_Target_Link;
	}

	public void setImage_Target_Link(String image_Target_Link) {
		Image_Target_Link = image_Target_Link;
	}
	
}
