package web.interfaces.vo;

import java.io.Serializable;

import com.base.HttpUtils;
import com.base.random.RandomSecret;
import com.table.user.entity.User;

public class RegisterVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RegisterVo(User user,String userName) {
		this.userName = userName;
		this.time = HttpUtils.getTime();
		this.code = RandomSecret.getAccess();
		this.tel = user.getTel();
		this.image = user.getEmail();
		this.idNum = user.getIdNum();
		this.code = RandomSecret.getAccess();
		this.password = RandomSecret.genRandomNum();
		// TODO Auto-generated constructor stub
	}
	private String tel;
	private String userName;
	private String image;
	private String idNum;
	private String code;
	private String time;
    private String password;
    
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getUserId() {
		return userName;
	}
	public void setUserId(String userId) {
		this.userName = userId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "RegisterVo [tel=" + tel + ", userName=" + userName + ", image=" + image + ", idNum=" + idNum + ", code="
				+ code + ", time=" + time + ", password=" + password + "]";
	}
}
