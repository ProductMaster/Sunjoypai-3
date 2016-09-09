package com.taoists.sys.passport.service;

public interface PassportLoginRecordService {

	public int findLoginFailureCount(String ip);
}
