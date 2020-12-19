package com.changgou.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/****
 * @Author:shenkunlin
 * @Description:Provinces构建
 * @Date 2019/6/14 19:13
 *****/
@TableName(value="tb_provinces")
public class Provinces implements Serializable{

	@Id
    @Column(name = "provinceid")
	private String provinceid;//省份ID

    @Column(name = "province")
	private String province;//省份名称



	//get方法
	public String getProvinceid() {
		return provinceid;
	}

	//set方法
	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}
	//get方法
	public String getProvince() {
		return province;
	}

	//set方法
	public void setProvince(String province) {
		this.province = province;
	}


}
