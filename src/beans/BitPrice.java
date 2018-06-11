package beans;

public class BitPrice {
	
	
	private String PRICE_ID;
	private String Project_ID;
	private String PRICE_USER;
	private String PRICE_USER_ID;
	private String PRICE_PRICE;
	private String PRICE_PRICE_DATE;
	
	public BitPrice() {}
	
	public BitPrice(String PRICE_ID, String Project_ID, String PRICE_USER, String PRICE_USER_ID,
				String PRICE_PRICE, String PRICE_PRICE_DATE) {
		super();
		
		this.Project_ID = Project_ID;
		this.PRICE_ID = PRICE_ID;
		this.PRICE_USER = PRICE_USER;
		this.PRICE_USER_ID = PRICE_USER_ID;
		
		this.PRICE_PRICE = PRICE_PRICE;
		this.PRICE_PRICE_DATE = PRICE_PRICE_DATE;
	}

	public String getPRICE_ID() {
		return PRICE_ID;
	}

	public void setPRICE_ID(String pRICE_ID) {
		PRICE_ID = pRICE_ID;
	}

	public String getProject_ID() {
		return Project_ID;
	}

	public void setProject_ID(String project_ID) {
		Project_ID = project_ID;
	}

	public String getPRICE_USER() {
		return PRICE_USER;
	}

	public void setPRICE_USER(String pRICE_USER) {
		PRICE_USER = pRICE_USER;
	}

	public String getPRICE_USER_ID() {
		return PRICE_USER_ID;
	}

	public void setPRICE_USER_ID(String pRICE_USER_ID) {
		PRICE_USER_ID = pRICE_USER_ID;
	}

	public String getPRICE_PRICE() {
		return PRICE_PRICE;
	}

	public void setPRICE_PRICE(String pRICE_PRICE) {
		PRICE_PRICE = pRICE_PRICE;
	}

	public String getPRICE_PRICE_DATE() {
		return PRICE_PRICE_DATE;
	}

	public void setPRICE_PRICE_DATE(String pRICE_PRICE_DATE) {
		PRICE_PRICE_DATE = pRICE_PRICE_DATE;
	}
	
	
}
