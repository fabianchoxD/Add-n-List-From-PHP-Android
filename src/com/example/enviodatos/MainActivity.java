package com.example.enviodatos;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button b;
	EditText et, pass;
	TextView tv;
	HttpPost httppost;
	StringBuffer buffer;
	HttpResponse response;
	HttpClient httpclient;
	List<NameValuePair> nameValuePairs;
	ProgressDialog dialog = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		b = (Button) findViewById(R.id.Button01);
		et = (EditText) findViewById(R.id.username);
		pass = (EditText) findViewById(R.id.password);		

		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog = ProgressDialog.show(MainActivity.this, "",
						"Validando Usuario...", true);
				new Thread(new Runnable() {
					public void run() {
						login();
					}
				}).start();
			}
		});
	}

	void login() {
		try {

			httpclient = new DefaultHttpClient();
			// Cambiar la ruta por la que tienen los documentos en php
			httppost = new HttpPost("http://192.168.0.10/putandget/check.php");
			// add your data
			nameValuePairs = new ArrayList<NameValuePair>(2);
			// Always use the same variable name for posting i.e the android
			// side variable name and php side variable name should be similar,
			nameValuePairs.add(new BasicNameValuePair("username", et.getText()
					.toString().trim().toUpperCase())); // $Edittext_value =
														// $_POST['Edittext_value'];
			nameValuePairs.add(new BasicNameValuePair("password", pass
					.getText().toString().trim().toUpperCase()));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			// Execute HTTP Post Request
			response = httpclient.execute(httppost);
			// edited by James from coderzheaven.. from here....
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			final String response = httpclient.execute(httppost,
					responseHandler);
			System.out.println("Response : " + response);
			runOnUiThread(new Runnable() {
				public void run() {
					// tv.setText("Response from PHP : " + response);
					dialog.dismiss();
					et.setText("");
					pass.setText("");
				}
			});

			if (response.equalsIgnoreCase("User Found")) {
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(MainActivity.this, R.string.Ingreso_exito,
								Toast.LENGTH_SHORT).show();
					}
				});

				Intent act = new Intent(this, AddActivity.class);
				startActivity(act);

			} else {
				showAlert();
			}

		} catch (Exception e) {
			dialog.dismiss();
			System.out.println("Exception : " + e.getMessage());
		}
	}

	public void showAlert() {
		MainActivity.this.runOnUiThread(new Runnable() {
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle(R.string.Login_error);
				builder.setMessage(R.string.Login_error_mensaje)
						.setCancelable(false)
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// do something on back.
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					MainActivity.this);

			// Setting Dialog Title
			alertDialog.setTitle(R.string.Salir);
			// Setting Dialog Message
			alertDialog
					.setMessage(R.string.Salir_mensaje);
			// Setting Icon to Dialog
			alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
			
			// Setting Positive "Yes" Button
			alertDialog.setPositiveButton(R.string.Si_btn,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// Write your code here to invoke YES event
							finish();
						}
					});

			// "NO" Button
			alertDialog.setNegativeButton(R.string.No_btn,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// Write your code here to invoke NO event
							dialog.cancel();
						}
					});
		
			// Showing Alert Message
			alertDialog.show();
		}

		return super.onKeyDown(keyCode, event);
	}
}
