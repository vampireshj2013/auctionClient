package com.whj.auctionclientdemo;

import com.whj.fragment.AuctionListFragment;
import com.whj.fragment.ViewItemFragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity implements Callbacks {
	private boolean isBig;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if(findViewById(R.id.auction_detail_container)!=null){
			isBig = true;
			((AuctionListFragment)getFragmentManager()
					.findFragmentById(R.id.autionlist))
					.setActivateOnItemClick(true);
		}
	}
	public void onItemSelected(Integer id, Bundle bundle) {
		//若是大屏幕时：
		if(isBig){
			Fragment fragment = null;
			switch (id) {
			// 查看竞得物品
			case 1:
				fragment  =  new ViewItemFragment();
				Bundle bundle2 = new Bundle();
				bundle.putString("action", "viewSucc.jsp");
				fragment.setArguments(bundle2);
				break;
			case 2:
				// 创建ViewItemFragment
				fragment = new ViewItemFragment();
				// 创建Bundle，准备向Fragment传入参数
				Bundle arguments2 = new Bundle();
				arguments2.putString("action", "viewFail.jsp");
				// 向Fragment传入参数
				fragment.setArguments(arguments2);
				break;
			case 3:
	
				break;
			case 4:
	
				break;
			case 5:
	
				break;
			default:
				break;
			}
			getFragmentManager().beginTransaction()
			.replace(R.id.auction_detail_container, fragment)
			.addToBackStack(null)
			.commit();
		}
		else{
			Intent intent = null;
			switch (id) {
			case 1:
				startActivity(intent);
				break;

			default:
				break;
			}
		}
		
	}
}
