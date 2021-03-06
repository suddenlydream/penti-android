package com.dreaner.penti.core.ui;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.dreaner.penti.R;
import com.dreaner.penti.core.api.TushuoApi;
import com.dreaner.penti.core.api.result.TushuoPartItem;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class TushuoActivity extends Activity {
	private static final String TAG = "TushuoActivity";
	private List<TushuoPartItem> mData;
	private Handler mHandler;
	private TushuoAdapter mAdapter;
	private View mLoading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHandler = new Handler();
		initImageLoader();
		setContentView(R.layout.ac_image_list);
		ListView listview = (ListView) findViewById(R.id.list);
		mLoading = findViewById(R.id.loading);
		mAdapter = new TushuoAdapter(getLayoutInflater());
		listview.setAdapter(mAdapter);
		loadData();
	}

	AsyncHttpResponseHandler mResponse = new AsyncHttpResponseHandler() {
		@Override
		public void onSuccess(String content) {
			Log.d(TAG, "onSuccess: " + content);
			mData = DataTransHelper.parseTushuo(content);
			mHandler.post(new Runnable() {

				@Override
				public void run() {
					mLoading.setVisibility(View.GONE);
					mAdapter.setData(mData);
					mAdapter.notifyDataSetChanged();
				}
			});
		}

		@Override
		public void onFailure(Throwable error, String content) {
			Log.d(TAG, "onFailure: " + content);
			super.onFailure(error, content);
		}

		@Override
		public void onFinish() {
			Log.d(TAG, "onFinish: ");
			super.onFinish();
		}

		@Override
		public void onStart() {
			Log.d(TAG, "onStart: ");
			super.onStart();
		}
	};

	private void loadData() {
		Intent intent = getIntent();
		if (intent == null) {
			return;
		}

		String date = intent.getStringExtra("date");
		TushuoApi.requestTushuo(mResponse, date);
	}

	private void initImageLoader() {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
				.threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO)
				.enableLogging().build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}
