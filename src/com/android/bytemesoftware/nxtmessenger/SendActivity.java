/**
 * The MIT License
 *
 * Copyright (c) 2011 Daniel Ward apps(at)bytemesoftware.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * No person, persons, or entity is allowed to sell or make any profit off this
 * software or variants of this software other than the author.
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 * 
 * This class models a Devices with a name and address represented as a String
 * pair.
 *  
 * @author Daniel Ward
 * @version 1.00
 * @date March 7 2011
 */
package com.android.bytemesoftware.nxtmessenger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class SendActivity extends Activity {
	
	public final int SUCCESS = 1;
    public final int FAILURE = 0;
    public static final String STATUS = "status";
	
	private String address = "";
	private String result = "";
	private int mailbox;
	private String message;
	
	private BluetoothAdapter adapter;
	private BluetoothDevice device;
    private BluetoothSocket socket;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.send);
     
        setResult(Activity.RESULT_CANCELED);
        
        Bundle b = this.getIntent().getExtras();
        
        this.address = b.getString("Address");
        this.message = b.getString("Message");
        this.mailbox = b.getInt("Mailbox");
               
        adapter = BluetoothAdapter.getDefaultAdapter();
        
        if(adapter == null)
        	finish();
        
        device = adapter.getRemoteDevice(address);
        
        if(device == null)
        	finish();
	}

	public void onStart(){
		super.onStart();
		
		Log.d("NXT","SendActivity started");
		
		ProgressDialog dialog = ProgressDialog.show(SendActivity.this, "", 
                "Loading. Please wait...", true);
		
		new SendThread().run();
		
		
		
		
		Log.d("NXT","Send Activity finished");
	             
		done(SUCCESS);

	}
	
	private void done(int status)
	{
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Intent intent = new Intent();
		intent.putExtra(SendActivity.STATUS, result);
		if(status == SUCCESS)
			setResult(Activity.RESULT_OK, intent);
		else
			setResult(Activity.RESULT_CANCELED, intent);
		finish();
	}
	
	public void onPause()
	{
		super.onPause();
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onDestroy()
	{
		super.onDestroy();
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class SendThread extends Thread{
		public void run()
		{
			try {
				SendActivity.this.socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
				SendActivity.this.socket.connect();
				Log.d("NXT","Socket Created");
				
			} catch (IOException e) {
				
				Method mMethod;
				try 
				{
					mMethod = device.getClass().getMethod("createRfcommSocket", new Class[] { int.class });
					SendActivity.this.socket = (BluetoothSocket) mMethod.invoke(SendActivity.this.device, Integer.valueOf(1));
					SendActivity.this.socket.connect();
					Log.d("NXT","Socket Created");
					
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					SendActivity.this.done(FAILURE);
				} catch (NoSuchMethodException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					SendActivity.this.done(FAILURE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					Log.e("NXT","Socket Creation Failed");
					e.printStackTrace();
					SendActivity.this.done(FAILURE);
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					SendActivity.this.done(FAILURE);
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					SendActivity.this.done(FAILURE);
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					SendActivity.this.done(FAILURE);
				}
		       
			}
			
			Log.d("NXT","Sending message");
			
			byte[] message = SendMessage.formatMessage(SendActivity.this.message, SendActivity.this.mailbox);
	        
			OutputStream outStream = null;
			InputStream inStream = null;
			
			try 
			{
				outStream = SendActivity.this.socket.getOutputStream();
				inStream = SendActivity.this.socket.getInputStream();
				
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e("NXT","Could not get streams");
				e.printStackTrace();
				SendActivity.this.done(FAILURE);
			}
			
			try 
			{
				outStream.write(message.length);
				outStream.write(message.length >> 8);
				outStream.write(message, 0, message.length);
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e("NXT","Could not write to stream OutputStream");
				e.printStackTrace();
				SendActivity.this.done(FAILURE);
			}
			
			try {
				int length = inStream.read();
				length = (inStream.read() >> 8) + length;
				
				Log.d("NXT","Getting mesage od length: "+length);
				
				byte[] reply = new byte[length];
				inStream.read(reply);
				
				SendActivity.this.result = SendMessage.status(reply[2]);
				
				Log.d("NXT","Reply status: " + result);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e("NXT","Could not read stream");
				e.printStackTrace();
				SendActivity.this.done(FAILURE);
			}
		}
	}

}


