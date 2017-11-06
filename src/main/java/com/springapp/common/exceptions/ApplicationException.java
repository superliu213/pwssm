package com.springapp.common.exceptions;

/**
 * 应用程序异常类
 * 
 */
public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据库操作,insert返回0
	 */
	public static final ApplicationException DB_INSERT_RESULT_0 = new ApplicationException(90040001, "数据库操作,insert返回0");

	/**
	 * 数据库操作,update返回0
	 */
	public static final ApplicationException DB_UPDATE_RESULT_0 = new ApplicationException(90040002, "数据库操作,update返回0");

	/**
	 * 数据库操作,selectOne返回null
	 */
	public static final ApplicationException DB_SELECTONE_IS_NULL = new ApplicationException(90040003, "数据库操作,selectOne返回null");

	/**
	 * 数据库操作,list返回null
	 */
	public static final ApplicationException DB_LIST_IS_NULL = new ApplicationException(90040004, "数据库操作,list返回null");

	/**
	 * Token 验证不通过
	 */
	public static final ApplicationException TOKEN_IS_ILLICIT = new ApplicationException(90040005, "Token 验证非法");
	/**
	 * 会话超时　获取session时，如果是空，throws 下面这个异常 拦截器会拦截爆会话超时页面
	 */
	public static final ApplicationException SESSION_IS_OUT_TIME = new ApplicationException(90040006, "会话超时");

	/**
	 * 获取序列出错
	 */
	public static final ApplicationException DB_GET_SEQ_NEXT_VALUE_ERROR = new ApplicationException(90040007, "获取序列出错");

	private int code;
	private String message;
	
	/**
	 * 默认的构造方法
	 * 
	 */
	public ApplicationException() {
		super();
	}

	/**
	 * 带一个参数String参数的构造方法
	 *
	 * @param message 错误描述
	 */
	public ApplicationException(String message) {
		super(message);
		this.code = -1;
		this.message = message;
	}

	/**
	 * 带一个Throwable的构造方法
	 *
	 * @param cause 异常
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
		this.code = -1;
	}

	/**
	 * 带一个String和一个Throwable参数的构造方法
	 *
	 * @param message 错误描述
	 * @param cause 异常
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
		this.code = -1;
		this.message = message;
	}

	/**
	 * 带错误代码、错误描述和异常的构造方法
	 *
	 * @param code 错误代码
	 * @param message 错误描述
	 * @param cause 异常
	 */
	public ApplicationException(int code, String message,
								Throwable cause) {
		super(message, cause);
		this.code = code;
		this.message = message;
	}

	public ApplicationException(int code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.message = String.format(msgFormat, args);
	}

	/**
	 * 实例化异常
	 *
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public ApplicationException newInstance(String msgFormat, Object... args) {
		return new ApplicationException(this.code, msgFormat, args);
	}

	/**
	 * 
	 * @return 错误代码
	 */
	public int getCode() {
		return code;
	}
	
	/**
	 * 
	 * @param code 错误代码
	 */
	public void setCode(int code) {
		this.code = code;
	}
	
	/**
	 * 
	 * @return 错误描述
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 
	 * @param message 错误描述
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
