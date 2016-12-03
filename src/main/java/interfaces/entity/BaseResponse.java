package interfaces.entity;

import java.io.Serializable;



/**
 * 网络返回基类 支持泛型
 * Created by gbq
 */
public class BaseResponse<T> implements Serializable{

    /**
	 * 2016年11月24日
	 */
	private static final long serialVersionUID = 1L;
	private int code = -1;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
