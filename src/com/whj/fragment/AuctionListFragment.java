package com.whj.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;

import com.whj.auctionclientdemo.Callbacks;
import com.whj.auctionclientdemo.R;

public class AuctionListFragment extends Fragment{
	ListView auctionList;
	private Callbacks mCallbacks;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.aution_list, container,false);
		auctionList = (ListView)rootView.findViewById(R.id.list_view);
		auctionList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
			mCallbacks.onItemSelected(position, null);
				
			}
			
		});
		return rootView;
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(!(activity instanceof Callbacks)){
			throw new IllegalStateException("Activity未实现Callbacks");
		}
		mCallbacks = (Callbacks)activity;
	}
	

}
