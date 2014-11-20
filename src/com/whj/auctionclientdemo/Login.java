package com.whj.auctionclientdemo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.whj.util.DialogUtil;
import com.whj.util.HttpUtil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends ActionBarActivity {

	EditText etName, etPass;
	Button bnLogin, bnCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		etName = (EditText) findViewById(R.id.userEditText);
		etPass = (EditText) findViewById(R.id.pwdEditText);
		bnLogin = (Button) findViewById(R.id.bnLogin);
		bnCancel = (Button) findViewById(R.id.bnCancel);
		bnCancel.setOnClickListener(new HomeListener(this));
		bnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(validate()){
					if(loginPro()){
						Intent intent = new Intent(Login.this,MainActivity.class);
						startActivity(intent);
						finish();
					}else{
						DialogUtil.showDialog(Login.this, "用户名称或者密码错误，请重新输入！",false);
					}
				}

			}
		});
	}

	private boolean loginPro(){
		String username = etName.getText().toString();
		String pass = etPass.getText().toString();
		JSONObject json;
		try {
			json = query(username, pass);
			if(json!=null&&json.getInt("userId")>0){
				return true;
			}
		} catch (Exception e) {
			DialogUtil.showDialog(this, "登陆错误，请稍后重试！",false);
			e.printStackTrace();
		}
		return false;
	}
	private boolean  validate(){
		String username = etName.getText().toString();
		String pass = etPass.getText().toString();
		if(username==null||username.equals("")){
			DialogUtil.showDialog(this, "用户名不能为空！", false);
			return false;
		}else if(pass == null||pass.equals("")){
			DialogUtil.showDialog(this, "用户口令不能为空！", false);
			return false;
		}
		return true;
	}
	private JSONObject query(String username, String password) throws Exception {
		Map<String, String> hashmap = new HashMap<String, String>();
		hashmap.put("user", username);
		hashmap.put("pass", password);
		String url = HttpUtil.BASE_URL + "login.jsp";
		return new JSONObject(HttpUtil.postRequest(url, hashmap));
	}
}
