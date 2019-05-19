package com.zhibo.trafficlight.mapper;

import java.util.List;

import com.zhibo.trafficlight.data.MsgManager;

public interface MsgManagerMapper {

	void insert(MsgManager msgManager);
	void deleteById(long msgManagerId);
	/**
	 * 更新通信管理机编号
	 * @param msgManagerId
	 * @param code
	 */
	void updateMsgManagerCode(long id, int code);
	
	List<MsgManager> findAll();
	
	List<MsgManager> findByDetailsAddressId(Long detailsAddressId);
	
	MsgManager findByCode(Integer msgManagerCode);
	
	MsgManager findById(Long id);
}
