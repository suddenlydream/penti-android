package com.dreaner.penti.core.ui;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.dreaner.penti.R;
import com.dreaner.penti.core.api.TushuoApi;
import com.dreaner.penti.core.api.result.TushuoSummary;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class SummaryActivity extends Activity implements OnItemClickListener {
	private static final String TAG = "SummaryActivity";
	private List<TushuoSummary> mData;
	private Handler mHandler;
	private SummaryAdapter mAdapter;
	private View mLoading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHandler = new Handler();
		initImageLoader();
		setContentView(R.layout.ac_image_list);
		ListView listview = (ListView) findViewById(R.id.list);
		listview.setOnItemClickListener(this);
		mLoading = findViewById(R.id.loading);
		mAdapter = new SummaryAdapter(getLayoutInflater());
		listview.setAdapter(mAdapter);
		loadData();
	}

	AsyncHttpResponseHandler mResponse = new AsyncHttpResponseHandler() {
		@Override
		public void onSuccess(String content) {
			Log.d(TAG, "onSuccess: " + content);

			mData = DataTransHelper.parseSummary(content);
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
			Toast.makeText(SummaryActivity.this, "下载数据错误", Toast.LENGTH_SHORT).show();
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
		TushuoApi.requestSummary(mResponse);
	}

	private void initImageLoader() {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
				.threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO)
				.enableLogging().build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		TushuoSummary item = mData.get(arg2);
		if (item != null) {
			String date = item.getDate();
			if (date != null) {
				Intent intent = new Intent();
				intent.setClass(this, TushuoActivity.class);
				intent.putExtra("date", date);
				startActivity(intent);
			}
		}
	}
}
