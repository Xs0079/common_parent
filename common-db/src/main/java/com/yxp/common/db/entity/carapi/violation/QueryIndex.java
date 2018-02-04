package com.yxp.common.db.entity.carapi.violation;


import java.util.ArrayList;
import java.util.List;

/**
 * 违章记录实体
 * @author yanzongrui
 *
 */
public class QueryIndex  implements java.io.Serializable{
	/**
	* 字段名称：True成功（只能代表通讯层，业务层面请参考ErrorCode）False 失败
	* 字段描述：查询状态码
	*/
		private Boolean success;
			
	
	/**
	* 字段名称：返回错误码，0成功，其它失败
	* 字段描述：返回错误码
	*/
		private Integer errorCode= 0;
			
	
	/**
	* 字段名称：返回错误描述
	* 字段描述：返回错误描述
	*/
		private String errMessage= "";
			
	
	/**
	* 字段名称：是否有违章。是 True  否 False
	* 字段描述：是否有违章
	*/
		private Boolean hasData;
			
	
	/**
	* 字段名称：违章详细信息列表
	* 字段描述：
	*/
	private List<RecordList> recordList= new ArrayList<RecordList>();
 		
	
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
				this.success = success==null? false:success;
			}
	
	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
				this.errorCode = errorCode==null? 0:errorCode;
			}
	
	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
				this.errMessage = errMessage==null? "":errMessage;
			}
	
	public Boolean getHasData() {
		return hasData;
	}

	public void setHasData(Boolean hasData) {
				this.hasData = hasData==null? false:hasData;
			}
	
	public List<RecordList> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<RecordList> recordList) {
				this.recordList = recordList;
			}
}