package com.yxp.common.db.entity.carapi.violation;

public class RecordList  implements java.io.Serializable{
	/**
	* 字段名称：违章日期：2016-12-20
	* 字段描述：违章日期
	*/
			private String date= "";
			/**
	* 字段名称：违章地点
	* 字段描述：违章地点
	*/
			private String area= "";
			/**
	* 字段名称：违章行为
	* 字段描述：违章行为
	*/
			private String act= "";
			/**
	* 字段名称：违章代码(仅供参考)
	* 字段描述：违章代码(仅供参考)
	*/
			private String violationCode= "";
			/**
	* 字段名称：违章扣分
	* 字段描述：违章扣分
	*/
			private Integer fen= 0;
			/**
	* 字段名称：违章罚款
	* 字段描述：违章罚款
	*/
			private Integer money= 0;
			/**
	* 字段名称：是否处理，1处理 0未处理 空未知
	* 字段描述：是否处理
	*/
			private Integer handle= 0;
			
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
				this.date = date==null? "":date;
			}
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
				this.area = area==null? "":area;
			}
	
	public String getAct() {
		return act;
	}

	public void setAct(String act) {
				this.act = act==null? "":act;
			}
	
	public String getViolationCode() {
		return violationCode;
	}

	public void setViolationCode(String violationCode) {
				this.violationCode = violationCode==null? "":violationCode;
			}
	
	public Integer getFen() {
		return fen;
	}

	public void setFen(Integer fen) {
				this.fen = fen==null? 0:fen;
			}
	
	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
				this.money = money==null? 0:money;
			}
	
	public Integer getHandle() {
		return handle;
	}

	public void setHandle(Integer handle) {
				this.handle = handle==null? 0:handle;
			}
}