package com.yxp.common.db.entity.carapi.violation;

import java.util.ArrayList;
import java.util.List;

public class ProvinceList implements java.io.Serializable{
	/**
	* 字段名称：省代码
	* 字段描述：
	*/
			private String provinceCode= "";
			/**
	* 字段名称：省名称
	* 字段描述：
	*/
			private String provinceName= "";
			/**
	* 字段名称：市列表
	* 字段描述：
	*/
		private List<CityList> cityList= new ArrayList<CityList>();
		
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
				this.provinceCode = provinceCode==null? "":provinceCode;
			}
	
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
				this.provinceName = provinceName==null? "":provinceName;
			}
	
	public List<CityList> getCityList() {
		return cityList;
	}

	public void setCityList(List<CityList> cityList) {
				this.cityList = cityList;
			}
}