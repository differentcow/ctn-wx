package com.ctn.operate;

public enum SqlOperate {
	EQ {
		@Override
		public String value() {
			return "=";
		}
	},GT {
		@Override
		public String value() {
			return ">";
		}
	},LT {
		@Override
		public String value() {
			return "<";
		}
	},GTE {
		@Override
		public String value() {
			return ">=";
		}
	},LTE {
		@Override
		public String value() {
			return "<=";
		}
	};
	public abstract String value();
}
