package web.interfaces.vo;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.base.HttpUtils;
import com.base.random.RandomSecret;

public class PassVo implements Serializable {
	/**
	 * 
	 */
	
	public PassVo(HttpServletRequest request,String userName) {
		this.userName = userName;
		this.time = HttpUtils.getTime();
		this.code = RandomSecret.getAccess();
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;
	private String code;
	private String userName;
	private String time;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "PassVo [code=" + code + ", userName=" + userName + ", time=" + time + "]";
	}
}
