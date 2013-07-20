/*
 * 版    权：深圳市快播科技有限公司
 * 描    述: 
 * 创建人: HuQiming
 * 创建时间: 2013-7-18
 * 
 */
package com.dreaner.penti.core.ui;

import java.util.ArrayList;
import java.util.List;

import com.dreaner.penti.core.api.result.Tushuo;
import com.dreaner.penti.core.api.result.TushuoPart;
import com.dreaner.penti.core.api.result.TushuoPartItem;
import com.dreaner.penti.core.api.result.TushuoSummary;
import com.dreaner.penti.util.json.JacksonUtils;

/**
 * @author HuQiming
 * @date 2013-7-18
 * 
 */
public class DataTransHelper {
	public static List<TushuoPartItem> parseTushuo(String json) {
		Tushuo tushuo = JacksonUtils.shareJacksonUtils().parseJson2Obj(json, Tushuo.class);
		List<TushuoPartItem> result = new ArrayList<TushuoPartItem>();
		if (tushuo != null) {
			List<TushuoPart> list = tushuo.getParts();
			for (TushuoPart item : list) {
				result.addAll(item.getItems());
			}
		}
		return result;
	}
	
	public static List<TushuoSummary> parseSummary(String json) {
		return JacksonUtils.shareJacksonUtils().parseJson2List(json, TushuoSummary.class);
	}
}
