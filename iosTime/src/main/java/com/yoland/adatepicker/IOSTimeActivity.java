package com.yoland.adatepicker;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yoland.adatepicker.ios.DatePicker;

/**
 * 
 *
 * @author Martin
 */
public class IOSTimeActivity extends Activity implements View.OnClickListener {
	/**
	 * 日期选择
	 */
	private DatePicker mDatePicker;
	/**
	 * 预览日期
	 */
	private TextView mTvShowDate;
	/**
	 * 显示单独的年月日
	 */
	private TextView mTvShowSplit;

	/**
	 * 输入的时间
	 */
	private EditText mEtSetTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ios_time_demo_layout);
		mDatePicker = (DatePicker) findViewById(R.id.date_picker);
		mTvShowDate = (TextView) findViewById(R.id.tv_show);
		mTvShowSplit = (TextView) findViewById(R.id.tv_show_split);
		mEtSetTime = (EditText) findViewById(R.id.ed_set_time);
		findViewById(R.id.btn_get).setOnClickListener(this);
		findViewById(R.id.btn_get_day).setOnClickListener(this);
		findViewById(R.id.btn_get_month).setOnClickListener(this);
		findViewById(R.id.btn_get_year).setOnClickListener(this);
		findViewById(R.id.btn_get_time).setOnClickListener(this);
		findViewById(R.id.btn_set_time).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_get) {
			String[] date = mDatePicker.getDate();
			String dateString = TextUtils.join("-", date);
			mTvShowDate.setText(dateString);
		} else if (v.getId() == R.id.btn_get_day) {
			mTvShowSplit.setText(mDatePicker.getDay());
		} else if (v.getId() == R.id.btn_get_month) {
			mTvShowSplit.setText(mDatePicker.getMonth());
		} else if (v.getId() == R.id.btn_get_year) {
			mTvShowSplit.setText(mDatePicker.getYear());
		} else if (v.getId() == R.id.btn_get_time) {
			mTvShowSplit.setText(mDatePicker.getTime());
		}  else if (v.getId() == R.id.btn_set_time) {
			String time = mEtSetTime.getText().toString();
			String[] times = time.split("-");
			int[] num = new int[times.length];
			for(int i = 0; i<times.length; i++) {
				num[i] = Integer.valueOf(times[i]);
			}
			mDatePicker.setYMDTime(num);
		} 
	}
}
