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

import com.android.bytemesoftware.nxtmessenger.R;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Main extends Activity{
	
	//the bluetooth adapter instance for this class
	private BluetoothAdapter bluetoothAdapter;
	
	//some constants for the activity callbacks
	private static final int REQUEST_ENABLE_BT = 2;
	private static final int REQUEST_DEVICE = 1;
	private static final int SEND_MESSAGE = 3;
	
	//private string instances for this class
	private String device_name;
	private String device_address;
	private String mailbox;
	
	
    /**
     * Create method that is used when the activity is created
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.d("NXT","Activity started");
        
        //set he layout of the UI of the main activity
        setContentView(R.layout.main);
        
        Spinner spinner = (Spinner) findViewById(R.id.mailbox_select);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        
        this.mailbox = "1";
        
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		    	Main.this.mailbox = parent.getItemAtPosition(pos).toString();
		    }
		
		    public void onNothingSelected(AdapterView<?> parent) {
		      // Do nothing.
		    }
		});
        
        //create a reference to the send button on the main screen
        Button sendButton = (Button)findViewById(R.id.send);
        
        //add a click listener to the button
        sendButton.setOnClickListener(new OnClickListener(){
        	public void onClick(View arg){
        		
        		//get the message text field
        		EditText messageEntry = (EditText)findViewById(R.id.entry);
        		
        		//create a new bundle to pass information to the next activity
        		Bundle bundle = new Bundle();
        		bundle.putString("Address", Main.this.device_address);
        		bundle.putString("Message", messageEntry.getText().toString());
        		bundle.putInt("Mailbox", Integer.valueOf(Main.this.mailbox));
        		
        		Log.d("NXT","Transfering address: " + Main.this.device_address);
        		Log.d("NXT","Transfering message: " + messageEntry.getText().toString());
        		Log.d("NXT","Transfering mailbox: " + Main.this.mailbox);
        		
        		//create a new intent to start the SendActivity activity
        		Intent intent = new Intent(Main.this,SendActivity.class);
        		intent.putExtras(bundle);//put the bundle in the intent        		
        		
        		//start the activity
        		startActivityForResult(intent, Main.SEND_MESSAGE);
        	}//end onClick
        });//end setOnClickListener
        
        //get the default Bluetooth adapter on the device
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not supported and exit
        if (bluetoothAdapter == null) {
        	//print that the device is not available
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            
            //Terminate the activity
            finish();
        }//end if
        
    }//end onCreate
    
    
    @Override
    public void onStart() {
        super.onStart();
        
	    // If BT is not on, request that it be enabled.
	    if (!bluetoothAdapter.isEnabled()) {
	        Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	        startActivityForResult(enableIntent, REQUEST_ENABLE_BT);	
	    }
	    
	    //After the bluetooth adapter is enabled run the DeviceList Activity
	    Intent intent = new Intent(this, DeviceListActivity.class);
	    startActivityForResult(intent, REQUEST_DEVICE);        
    }//end onStart
    
    @Override
    public void onPause(){
    	super.onPause();    	
    	//TODO save the state of the String instances
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	//TODO restore the state of the strings
    }
    
    /**
     * Create the options menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu, menu);         
        return true;
    }
    
    /**
     * How to handle the actions from the options menu
     */
    public boolean onOptionsItemSelected(MenuItem item) {
    	
        // Handle item selection based on the id
        switch (item.getItemId()) 
        {
	        case R.id.new_device:
	        	//if the selection is for a new device and relaunch the DeviceListActivity
	        	Intent intent = new Intent(this, DeviceListActivity.class);
	     	    startActivityForResult(intent, REQUEST_DEVICE);  
	     	    return true;
	        default:
	            return super.onOptionsItemSelected(item);
        }//end switch
    }//end onOptionsItemSelected
    
    /**
     * Get the result of a sub activity using this callback
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	//use the codes the chose what action to do from the result of the activity
        switch (requestCode) 
        {        
	        //try to get a device from the list
	        case REQUEST_DEVICE:
	        	
	            // When DeviceListActivity returns with a device to connect
	            if (resultCode == Activity.RESULT_OK) 
	            {
	                // Get the device info
	                String info = data.getExtras().getString(DeviceListActivity.DEVICE_INFO);
	                
	                //the name and address are separated by a \n character
	                String[] device = info.split("\n");
	                
	                if(device.length == 2){
	                	device_name = device[0];
	                    device_address = device[1];
	                    
	                    //update the UI with the device info
	                    TextView deviceNameView = (TextView)findViewById(R.id.device_name);
	                    deviceNameView.setText(device_name);
	                    
	                    Log.d("NXT","Using device: " + device_address);
	                    
	                    Toast.makeText(this, "Using: " + device_name, Toast.LENGTH_SHORT).show();
	                }//end if
	                else{
	                	//change the device info
	                	device_name = "";
		                device_address = "";
		                
		                //update the UI info with the new device info
		                TextView deviceNameView = (TextView)findViewById(R.id.device_name);
		                deviceNameView.setText(R.string.device_name);
		                
		                Toast.makeText(this, "No Device Selected", Toast.LENGTH_SHORT).show();
	                }//end else	                
	            }//end if
	            break;
	            
	        //trying to enable bluetooth
	        case REQUEST_ENABLE_BT:
	            // When the request to enable Bluetooth returns
	            if (resultCode != Activity.RESULT_OK) {
	                Toast.makeText(this, "Bluetooth not enabled", Toast.LENGTH_SHORT).show();
	            }
	            break;  
	            
	        //Sending a message result
	        case SEND_MESSAGE:	
	        	if(resultCode == Activity.RESULT_OK){
	        		String status = data.getExtras().getString(SendActivity.STATUS);
	        		Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
	        	}
	        	break;
        }//end switch
    }//end onActivityResult
}//end Main