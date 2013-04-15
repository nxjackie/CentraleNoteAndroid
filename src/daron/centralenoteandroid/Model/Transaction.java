package daron.centralenoteandroid.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	private String _id;
	private String _emitter;
	private String _receiver;
	private String _deleted;
	private String _comment;
	private String _date;
	private String _ip;
	private String _amount;
	
	public String getId() {
		return _id;
	}
	
	public String getEmitter() {
		return _emitter;
	}
	
	public String getReceiver() {
		return _receiver;
	}
	
	public String getDeleted() {
		return _deleted;
	}
	
	public String getComment() {
		return _comment;
	}
	
	public String getDate() {
		
		long ts = Long.parseLong(_date);
		Date date2 = new Date(ts * 1000);
		
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd \n hh:mm:ss");
		String date3 = format2.format(date2);
		
		
		return date3;
	}
	
	public String getIp() {
		return _ip;
	}
	
	public String getAmount() {
		return _amount;
	}
	
	public void setId(String id) {
		_id = id;
	}
	
	public void setEmitter(String emitter) {
		_emitter = emitter;
	}
	
	public void setReceiver(String receiver) {
		_receiver = receiver;
	}
	
	public void setDeleted(String deleted) {
		_deleted = deleted;
	}
	
	public void setComment(String comment) {
		_comment = comment;
	}
	
	public void setDate(String date) {
		_date = date;
	}
	
	public void setAmount(String amount) {
		_amount = amount;
	}
	
	public void setIp(String ip) {
		_ip = ip;
	}
	
	public void changeDeleted() {
		_deleted = Integer.toString(1 - Integer.parseInt(_deleted));
	}
}
