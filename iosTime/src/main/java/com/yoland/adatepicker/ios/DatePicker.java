package com.yoland.adatepicker.ios;

import java.util.Calendar;

import com.yoland.adatepicker.R;
import com.yoland.adatepicker.ios.wheelview.NumericWheelAdapter;
import com.yoland.adatepicker.ios.wheelview.OnWheelChangedListener;
import com.yoland.adatepicker.ios.wheelview.WheelView;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 *
 * @author Martin
 */
public class DatePicker extends LinearLayout {

	/**
	 * 年
	 */
	private WheelView mViewYear;
	/**
	 * 月
	 */
	private WheelView mViewMonth;
	/**
	 * 日
	 */
	private WheelView mViewDay;
	/**
	 * Time
	 */
	private WheelView mViewTime;
	/**
	 * 不变的日历
	 */
	private Calendar mNowCalendar;
	/**
	 * 变化的日历
	 */
	private Calendar mChangeCalendar;

	private static String nianNum[],
			monthNum[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" },
			riNum[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
					"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" },
			timeNum[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
					"17", "18", "19", "20", "21", "22", "23" };

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public DatePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		initAttribute();
	}

	public DatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initAttribute();
	}

	public DatePicker(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DatePicker(Context context) {
		this(context, null, 0);
	}

	private void initAttribute() {
		LayoutInflater.from(getContext()).inflate(R.layout.view_date_picker, this, true);

		mViewYear = (WheelView) findViewById(R.id.wheelview_dialog_year);
		mViewYear.setVisibleItems(4);
		mViewMonth = (WheelView) findViewById(R.id.wheelview_dialog_month);
		mViewMonth.setVisibleItems(4);
		mViewDay = (WheelView) findViewById(R.id.wheelview_dialog_day);
		mViewDay.setVisibleItems(4);
		mViewTime = (WheelView) findViewById(R.id.wheelview_dialog_time);
		mViewTime.setVisibleItems(4);

		mNowCalendar = Calendar.getInstance();// 日历的实例

		/** 初始化年的轮子 **/
		int curYear = mNowCalendar.get(Calendar.YEAR);// 为高亮显示今年做准备
		// 初始化保存年的数组
		int count = 0, heightYear = 0;// 年数组临时下标、今年在数组中的下标
		nianNum = new String[2088 - 1969];
		for (int i = 1970; i <= 2088; i++) {
			nianNum[count] = String.valueOf(i);
			if (i == curYear) {// 如果年份是今年则把下标记录下来
				heightYear = count;
			}
			count++;
		}
		NumericWheelAdapter yearWheelAdapter = new DateNumericAdapter(getContext(), 1970, 2088, heightYear);
		yearWheelAdapter.setLabel(getContext().getString(R.string.year));
		mViewYear.setViewAdapter(yearWheelAdapter);// 设置年的数据适配
		mViewYear.addChangingListener(mYearMonthListener);
		mViewYear.setCyclic(true);

		/** 初始化月的轮子 **/
		int curMonth = mNowCalendar.get(Calendar.MONTH);
		NumericWheelAdapter monthWheelAdapter = new DateNumericAdapter(getContext(), 1, 12, curMonth);
		monthWheelAdapter.setLabel(getContext().getString(R.string.month));
		mViewMonth.setViewAdapter(monthWheelAdapter);
		mViewMonth.addChangingListener(mYearMonthListener);
		mViewMonth.setCyclic(true);

		/** 初始化日的轮子 **/
		// 初始化日
		updateDays();
		mViewDay.setCyclic(true);

		/** 初始化时间的轮子 **/
		int curTime = mNowCalendar.get(Calendar.HOUR_OF_DAY);
		NumericWheelAdapter timeWheelAdapter = new DateNumericAdapter(getContext(), 0, 23, curTime);
		timeWheelAdapter.setLabel(getContext().getString(R.string.time));
		mViewTime.setViewAdapter(timeWheelAdapter);
		mViewTime.addChangingListener(mYearMonthListener);
		mViewTime.setCyclic(true);

		// 初始化日期的年月日
		mViewYear.setCurrentItem(mNowCalendar.get(Calendar.YEAR) - 1970);
		mViewMonth.setCurrentItem(mNowCalendar.get(Calendar.MONDAY));
		mViewDay.setCurrentItem(mNowCalendar.get(Calendar.DAY_OF_MONTH) - 1);
		mViewTime.setCurrentItem(mNowCalendar.get(Calendar.HOUR_OF_DAY));
	}

	/**
	 * 获取日期
	 */
	public String[] getDate() {
		String year = nianNum[mViewYear.getCurrentItem()];
		String month = monthNum[mViewMonth.getCurrentItem()];
		String day = riNum[mViewDay.getCurrentItem()];
		String time = timeNum[mViewTime.getCurrentItem()];
		return new String[] { year, month, day, time };
	}

	public void setYMDTime(int[] str) {
		Log.e("TAG", str[0] + "----------" + str[1] + "+++++++");
		mViewYear.setCurrentItem(str[0] - 66);
		mViewMonth.setCurrentItem(str[1] - 1);
		mViewDay.setCurrentItem(str[2] - 1);
		mViewTime.setCurrentItem(str[3]);
	}

	/**
	 * 获取年
	 */
	public String getYear() {
		return nianNum[mViewYear.getCurrentItem()];
	}

	/**
	 * 获取日
	 */
	public String getMonth() {
		return monthNum[mViewMonth.getCurrentItem()];
	}

	/**
	 * 获取日
	 */
	public String getDay() {
		return riNum[mViewDay.getCurrentItem()];
	}

	/**
	 * 获取time
	 */
	public String getTime() {
		return timeNum[mViewTime.getCurrentItem()];
	}

	/**
	 * 年和月改变时联动日
	 */
	private OnWheelChangedListener mYearMonthListener = new OnWheelChangedListener() {
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			updateDays();
		}
	};

	/**
	 * 更新日
	 */
	private void updateDays() {
		/** 1.因为日历默认是今年；2.因为除了年，日和月都是从1开始的，所以用下标替换即可 **/
		if (mChangeCalendar == null) {
			mChangeCalendar = Calendar.getInstance();
		}
		mChangeCalendar.set(Calendar.YEAR, mChangeCalendar.get(Calendar.YEAR) + mViewYear.getCurrentItem());// 用轮子上选定的年份替换今年
		mChangeCalendar.set(Calendar.MONTH, mViewMonth.getCurrentItem());// 替换今月

		int maxDays = mChangeCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);// 拿到这个月的最大的日
		NumericWheelAdapter dayWheelAdapter = new DateNumericAdapter(getContext(), 1, maxDays,
				mChangeCalendar.get(Calendar.DAY_OF_MONTH) - 1);
		dayWheelAdapter.setLabel(getContext().getString(R.string.day));
		mViewDay.setViewAdapter(dayWheelAdapter);// 设置日的轮子日期
		// 比较两个今天和选定的日的大小，之后选定小的；因为如果现在选定的是3.31，那么月切换到2月后，2月最大为29，那么就要选定29
		int curDay = Math.min(maxDays, mViewDay.getCurrentItem());
		mViewDay.setCurrentItem(curDay, true);
	}

	/**
	 * 数字轮子的适配器，给当前项目高亮显示
	 */
	private class DateNumericAdapter extends NumericWheelAdapter {
		/**
		 * 要高亮的项目
		 */
		private int heigthItem;
		/**
		 * 当前项目的索引
		 */
		private int currentItem;

		public DateNumericAdapter(Context context, int minValue, int maxValue, int heightItem) {
			super(context, minValue, maxValue);
			this.heigthItem = heightItem;
			setTextSize(20);
		}

		@Override
		protected void configureTextView(TextView view) {
			super.configureTextView(view);
			if (currentItem == heigthItem) {
				view.setTextColor(Color.parseColor("#FF4F638B"));
			}
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			currentItem = index;// 保存当前item
			return super.getItem(index, cachedView, parent);
		}
	}
}
