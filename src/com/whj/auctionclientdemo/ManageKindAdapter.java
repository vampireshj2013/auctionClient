package com.whj.auctionclientdemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ManageKindAdapter extends BaseAdapter {
	private Context ctx;
	private JSONArray json;
	public ManageKindAdapter(Context ctx,JSONArray json){
		this.ctx = ctx;
		this.json = json;
	}
	@Override
	public int getCount() {
		return json.length();
	}

	@Override
	public Object getItem(int position) {
		return json.opt(position);
	}

	@Override
	public long getItemId(int position) {
		try {
			return ((JSONObject)getItem(position)).getInt("id");
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout container = new LinearLayout(ctx);
		container.setOrientation(1);
		LinearLayout linear = new LinearLayout(ctx);
		linear.setOrientation(1);
		ImageView iv = new ImageView(ctx);
		iv.setPadding(10, 0, 20, 0);
		iv.setImageResource(R.drawable.item);
		linear.addView(iv);
		// 创建一个TextView
		TextView tv = new TextView(ctx);
		try
		{
			// 获取JSONArray数组元素的kindName属性
			String kindName = ((JSONObject)getItem(position))
				.getString("kindName");
			// 设置TextView所显示的内容
			tv.setText(kindName);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		tv.setTextSize(20);
		// 将TextView添加到LinearLayout中
		linear.addView(tv);
		container.addView(linear);
		// 定义一个文本框来显示种类描述
		TextView descView = new TextView(ctx);
		descView.setPadding(30, 0, 0, 0);
		try
		{
			// 获取JSONArray数组元素的kindDesc属性
			String kindDesc = ((JSONObject)getItem(position))
				.getString("kindDesc");
			descView.setText(kindDesc);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		descView.setTextSize(16);
		container.addView(descView);
		return container;
	}

}
