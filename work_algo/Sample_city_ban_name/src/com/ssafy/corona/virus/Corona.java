package com.ssafy.corona.virus;

public class Corona extends Virus{
	public int spreadSpeed;

	// 11-0. public void Corona(String name, int level, int spreadSpeed) -> void 빼야 함 -> 생성자
	public Corona(String name, int level, int spreadSpeed) {
		setName(name);
		setLevel(level);
		setSpreadSpeed(spreadSpeed);
	}
	
	public int getSpreadSpeed() {
		return spreadSpeed;
	}
	public void setSpreadSpeed(int spreadSpeed) {
		this.spreadSpeed = spreadSpeed;
	}	
	
	// 12. -> overriding 안 되고 있었슴..
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(super.toString()).append("\t")
		  .append(getSpreadSpeed());
		return sb.toString();
	}
}