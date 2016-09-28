package model;

public class Price {
	private int time;//mouths
	private int money;
	private int point;

	public Price(int time,int money, int point){
		this.time = time;
		this.money = money;
		this.point = point;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
}
