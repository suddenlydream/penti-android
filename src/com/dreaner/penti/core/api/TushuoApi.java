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
import com.loopj.android.http.RequestParams;

/**
 * @author HuQiming
 * @date 2013-7-18
 * 
 */
public class TushuoApi {
	private static final String TAG = "TushuoApi";

	public static void requestTushuo(AsyncHttpResponseHandler handler, String date) {
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams("date", date);
		client.get(ApiUrl.TUSHUO, params, handler);
	}

	public static void requestSummary(AsyncHttpResponseHandler handler) {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(ApiUrl.SUMMARY, handler);
	}
}
