package com.whj.fragment;

import org.json.JSONArray;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.whj.auctionclientdemo.Callbacks;
import com.whj.auctionclientdemo.HomeListener;
import com.whj.auctionclientdemo.JSONArrayAdapter;
import com.whj.auctionclientdemo.R;
import com.whj.util.DialogUtil;
import com.whj.util.HttpUtil;

public class ViewItemFragment extends Fragment{
	ListView auctionList;
	private Callbacks mCallbacks;
	Button bnHome;
	TextView textView;
	ListView view ;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.view_item, container);
		bnHome = (Button)rootView.findViewById(R.id.view_button);
		textView = (TextView)rootView.findViewById(R.id.view_text);
		view = (ListView)rootView.findViewById(R.id.viewSucc);
		// 为返回按钮的单击事件绑定事件监听器
		bnHome.setOnClickListener(new HomeListener(getActivity()));
		String action = getArguments().getString("action");
		if(action.equals("viewFail.jsp")){
			textView.setText(R.string.view_fail);
		}
		try{
			JSONArray json = new JSONArray(HttpUtil.getRequest(action));
			JSONArrayAdapter adapter = new JSONArrayAdapter(getActivity(), json, "name", true);
			view.setAdapter(adapter);
		}catch(Exception e){
			DialogUtil.showDialog(getActivity(), "服务器响应异常，请稍后再试！", false);
			e.printStackTrace();
		}
		view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 查看指定物品的详细情况。
				viewItemDetail(position);
				
			}
		});
		return rootView;
	}
	
	public void viewItemDetail(int position){
		
	}
}
