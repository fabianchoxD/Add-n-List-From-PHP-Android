package com.example.enviodatos;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity {
	EditText nombre;
	EditText apellido;
	CheckBox modo;
	EditText edad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		nombre = (EditText) findViewById(R.id.et_nombre);
		apellido = (EditText) findViewById(R.id.et_apellido);
		edad = (EditText) findViewById(R.id.et_edad);
		modo = (CheckBox) findViewById(R.id.ck_modo);
	}

	// invoca la nueva actividad
	public void listadoOnClick(View view) {
		startActivity(new Intent(this, ListadoActivity.class));

	}

	// metodo que va en el boton agregar
	public void EnviarOnClik(View view) {
		if (nombre.getText().toString().trim().equals("")) {
			nombre.setError("Debe ingresar un nombre Válido");

		} else if (apellido.getText().toString().trim().equals("")) {

			apellido.setError("Debe ingresar un apellido Válido");

		} else if (edad.getText().toString().trim().equals("")) {
			edad.setError("Debe Rellenar este campo");

		} else {
			// ejecuta el asyntask el cual añade los datos.
			new MiTarea().execute("");
		}
	}

	public String enviarPost(String nombre, String apellido, String edad) {

		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpPost httpPost = new HttpPost(
				"http://192.168.0.13/putandget/putdata.php");			
		HttpResponse response = null;
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>(3);
			params.add(new BasicNameValuePair("nombre", nombre));
			params.add(new BasicNameValuePair("apellido", apellido));
			params.add(new BasicNameValuePair("edad", edad));
			params.add(new BasicNameValuePair("modo", "POST"));
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			response = httpClient.execute(httpPost, localContext);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return response.toString();
	}

	public String enviarGet(String nombre, String apellido, String edad) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpResponse response = null;
		String parametros = "?nombre=" + nombre + "&apellido=" + apellido
				+ "&edad=" + edad + "&modo=GET";

		HttpGet httpget = new HttpGet(
				"http://192.168.0.13/putandget/putdata.php" + parametros);
		try {
			response = httpClient.execute(httpget, localContext);

		} catch (Exception e) {

		}
		return response.toString();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// Tarea asincrona...
	public class MiTarea extends AsyncTask<String, Void, String> {

		@Override
		// ejecuta con preExceute todo antes de hacer do in background
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		// ejecuta una tarea en segundo plano
		protected String doInBackground(String... params) {
			// TODO Auto-generated method st
			// System.out.println("Background");
			final String res;

			if (modo.isChecked()) {

				res = enviarGet(nombre.getText().toString(), apellido.getText()
						.toString(), edad.getText().toString());

			} else {
				res = enviarPost(nombre.getText().toString(), apellido
						.getText().toString(), edad.getText().toString());
			}

			return null;

		}

		// se ejecuta una vez termina onPreExcecute

		protected void onPostExecute(String algo) {
			System.out.println("post");
			Crouton.showText(MainActivity.this, R.string.registro_exito,
					Style.CONFIRM);
			nombre.setText("");
			apellido.setText("");
			edad.setText("");
		}
	}
}
