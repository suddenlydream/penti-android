/*
 * 版    权：深圳市快播科技有限公司
 * 描    述: 
 * 创建人: HuQiming
 * 创建时间: 2013-7-18
 * 
 */
package com.dreaner.penti.core.api;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * @author HuQiming
 * @date 2013-7-18
 * 
 */
public class TushuoApi {
	private static final String TAG = "TushuoApi";

	public void requestTushuo(AsyncHttpResponseHandler handler){
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(API_URL.TUSHUO, handler);
	}
}
