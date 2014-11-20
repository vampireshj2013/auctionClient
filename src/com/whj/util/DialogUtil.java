package com.whj.util;


import com.whj.auctionclientdemo.MainActivity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.View;

public class DialogUtil {
	public static void showDialog(final Context ctx, String msg, boolean goHome) {
		Builder builder = new Builder(ctx).setMessage(msg).setCancelable(false);
		if(goHome){
			builder.setPositiveButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent i = new Intent(ctx , MainActivity.class);
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					ctx.startActivity(i);
					
				}
			});
		}else{
			builder.setPositiveButton("确定", null);
		}
		builder.create().show();
	}
	public static void showDialog(Context ctx , View view){
		new Builder(ctx)
		.setView(view)
		.setCancelable(false)
		.setPositiveButton("确定", null)
		.create()
		.show();
	}
}
