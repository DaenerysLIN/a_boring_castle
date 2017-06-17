package wyykt.gloria.practice.castle;


public class Specialty {

	public Specialty(String what, String desc, String effect) {
		super();
		this.what = what;
		this.desc = desc;
		this.effect = effect;
	}
	
	private String what;
	private String desc;
	private String effect;
	
	public String getWhat() {
		return what;
	}
	
	public String getDesc( ){
		return desc;
	}
	
	public String getEffect() {
		return effect;
	}
	
	
	
}
