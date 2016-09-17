package model;

public class Resume {
	private int id;
	private int channel_id;
	private int hraccount_id;
	private String name;
	private String jobname;
	private String deliever_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(int channel_id) {
		this.channel_id = channel_id;
	}
	public int getHraccount_id() {
		return hraccount_id;
	}
	public void setHraccount_id(int hraccount_id) {
		this.hraccount_id = hraccount_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	public String getDeliever_date() {
		return deliever_date;
	}
	public void setDeliever_date(String deliever_date) {
		this.deliever_date = deliever_date;
	}
}
