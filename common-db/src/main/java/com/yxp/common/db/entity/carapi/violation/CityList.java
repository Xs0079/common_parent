package com.yxp.common.db.entity.carapi.violation;

public class CityList implements java.io.Serializable{
	/**
	* 字段名称：市代码
	* 字段描述：
	*/
			private String cityCode= "";
			/**
	* 字段名称：市名称
	* 字段描述：
	*/
			private String cityName= "";
			/**
	* 字段名称：车牌号前缀
	* 字段描述：
	*/
			private String carNumberPrefix= "";
			/**
	* 字段名称：是否需要车架号（1.是 0.否）
	* 字段描述：
	*/
			private String isNeedVin= "";
			/**
	* 字段名称：需要车架号后几位（0全部，1-9需要车架号后N位）
	* 字段描述：
	*/
			private String vinLength= "";
			/**
	* 字段名称：是否需要发动机号（1.是 0.否）
	* 字段描述：
	*/
			private String isNeedEngineNo= "";
			/**
	* 字段名称：需要发动机号后几位（0全部，1-9需要发动机号后N位）
	* 字段描述：
	*/
			private String engineNoLength= "";
			
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
				this.cityCode = cityCode==null? "":cityCode;
			}
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
				this.cityName = cityName==null? "":cityName;
			}
	
	public String getCarNumberPrefix() {
		return carNumberPrefix;
	}

	public void setCarNumberPrefix(String carNumberPrefix) {
				this.carNumberPrefix = carNumberPrefix==null? "":carNumberPrefix;
			}
	
	public String getIsNeedVin() {
		return isNeedVin;
	}

	public void setIsNeedVin(String isNeedVin) {
				this.isNeedVin = isNeedVin==null? "":isNeedVin;
			}
	
	public String getVinLength() {
		return vinLength;
	}

	public void setVinLength(String vinLength) {
				this.vinLength = vinLength==null? "":vinLength;
			}
	
	public String getIsNeedEngineNo() {
		return isNeedEngineNo;
	}

	public void setIsNeedEngineNo(String isNeedEngineNo) {
				this.isNeedEngineNo = isNeedEngineNo==null? "":isNeedEngineNo;
			}
	
	public String getEngineNoLength() {
		return engineNoLength;
	}

	public void setEngineNoLength(String engineNoLength) {
				this.engineNoLength = engineNoLength==null? "":engineNoLength;
			}
}