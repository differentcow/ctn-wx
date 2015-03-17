package com.ctn.operate;

public class SqlParamElement {
	private SqlOperate operate;
	private Object value;

	private SqlParamElement(SqlOperate operate, Object value) {
		super();
		this.operate = operate;
		this.value = value;
	}

	public SqlOperate getOperate() {
		return operate;
	}

	public void setOperate(SqlOperate operate) {
		this.operate = operate;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
