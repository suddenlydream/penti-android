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
public class TushuoPart {
	private String title;
	private List<TushuoPartItem> items;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<TushuoPartItem> getItems() {
		return items;
	}

	public void setItems(List<TushuoPartItem> items) {
		this.items = items;
	}

}
