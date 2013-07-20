/*
 * 版    权：深圳市快播科技有限公司
 * 描    述: 
 * 创建人: HuQiming
 * 创建时间: 2013-7-18
 * 
 */
package com.dreaner.penti.core.ui;

import java.util.List;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreaner.penti.R;
import com.dreaner.penti.core.api.result.TushuoSummary;

/**
 * @author HuQiming
 * @date 2013-7-18
 * 
 */
public class SummaryAdapter extends BaseAdapter {
	private static final String TAG = "TushuoAdapter";
	private LayoutInflater mInflater;
	private List<TushuoSummary> mData;

	public SummaryAdapter(LayoutInflater inflater) {
		mInflater = inflater;
	}

	public void setData(List<TushuoSummary> data) {
		mData = data;
	}

	@Override
	public int getCount() {
		return mData != null ? mData.size() : 0;
	}

	@Override
	public TushuoSummary getItem(int position) {
		return mData != null && position <= mData.size() ? mData.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		final ViewHolder holder;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_summary, null);
			holder = new ViewHolder();
			holder.title = (TextView) view.findViewById(R.id.title);
			holder.date = (TextView) view.findViewById(R.id.date);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		TushuoSummary model = getItem(position);
		if (model != null) {
			holder.title.setText(model.getTitle());
			holder.date.setText(model.getDate());
		}
		return view;
	}

	private class ViewHolder {
		public TextView title;
		public TextView date;
	}

}
