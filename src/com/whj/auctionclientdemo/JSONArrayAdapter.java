package com.whj.auctionclientdemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class JSONArrayAdapter extends BaseAdapter {
	private Context ctx;
	// 定义需要包装的JSONArray对象
	private JSONArray jsonArray;
	// 定义列表项显示JSONObject对象的哪个属性
	private String property;
	private boolean hasIcon;
	public JSONArrayAdapter(Context ctx,JSONArray jsonArray,String property,boolean hasIcon) {
		this.ctx = ctx;
		this.jsonArray = jsonArray;
		this.property = property;
		this.hasIcon = hasIcon;
	}
	@Override
	public int getCount() {
		return jsonArray.length();
	}

	@Override
	public Object getItem(int position) {
		return jsonArray.optJSONArray(position);
	}

	@Override
	public long getItemId(int position) {
		try
		{
			// 返回物品的ID
			return ((JSONObject)getItem(position)).getInt("id");
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout layout = new LinearLayout(ctx);
		//水平线性布局
		layout.setOrientation(0);
		ImageView iv = new ImageView(ctx);
		iv.setPadding(10, 0, 20, 0);
		iv.setImageResource(R.drawable.item);
		layout.addView(iv);
		TextView tv = new TextView(ctx);
		tv.setTextSize(20);
		try{
			String name  = ((JSONObject)getItem(position)).getString(property);
			tv.setText(name);
			
		}catch (JSONException e){
			e.printStackTrace();
		}
		if(hasIcon){
			layout.addView(tv);
			return layout;
		}else{
			return tv;
		}
		
	}

}
