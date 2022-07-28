package practice_parsing;

public class Weather {
	
	private String hour;
	private String temp;
	private String wfKor;
	private String reh;
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getWfKor() {
		return wfKor;
	}
	public void setWfKor(String wfKor) {
		this.wfKor = wfKor;
	}
	public String getReh() {
		return reh;
	}
	public void setReh(String reh) {
		this.reh = reh;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Weather [hour=");
		builder.append(hour);
		builder.append(", temp=");
		builder.append(temp);
		builder.append(", wfKor=");
		builder.append(wfKor);
		builder.append(", reh=");
		builder.append(reh);
		builder.append("]\n");
		return builder.toString();
	}
	
	
	
	
}
