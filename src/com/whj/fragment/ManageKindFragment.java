package com.whj.fragment;

import org.json.JSONArray;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.whj.auctionclientdemo.Callbacks;
import com.whj.auctionclientdemo.HomeListener;
import com.whj.auctionclientdemo.ManageKindAdapter;
import com.whj.auctionclientdemo.R;
import com.whj.util.Constant;
import com.whj.util.DialogUtil;
import com.whj.util.HttpUtil;

public class ManageKindFragment extends Fragment {
	ListView listView ;
	Callbacks mCallbacks;	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.manage_kind,container,false);
		view.findViewById(R.id.bn_home).setOnClickListener(new HomeListener(getActivity()));
		Button bnAdd = (Button)view.findViewById(R.id.bnAdd);
		listView = (ListView)view.findViewById(R.id.kindList);
		bnAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCallbacks.onItemSelected(Constant.ADD_KIND , null);
				
			}
		});
		String url = HttpUtil.BASE_URL + "viewKind.jsp";
		try {
			JSONArray json = new JSONArray(HttpUtil.getRequest(url));
			listView.setAdapter(new ManageKindAdapter(getActivity(), json));
		} catch (Exception e) {
			DialogUtil.showDialog(getActivity(), "服务器异常，请稍后重试！",false);
			e.printStackTrace();
		}
		return view;
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(activity instanceof Callbacks){
			mCallbacks =(Callbacks)activity;
		}else{
			throw new IllegalStateException(
					"ManageKindFragment所在的Activity必须实现Callbacks接口!");
		}
	}
	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
	}
}
