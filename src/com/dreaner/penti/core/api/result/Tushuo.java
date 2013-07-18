/*
 * 版    权：深圳市快播科技有限公司
 * 描    述: 
 * 创建人: HuQiming
 * 创建时间: 2013-7-18
 * 
 */
package com.dreaner.penti.core.api.result;

import java.util.List;

/**
 * @author HuQiming
 * @date 2013-7-18
 * 
 */
public class Tushuo {
	private String title;
	private List<TushuoPart> parts;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<TushuoPart> getParts() {
		return parts;
	}

	public void setParts(List<TushuoPart> parts) {
		this.parts = parts;
	}

}
