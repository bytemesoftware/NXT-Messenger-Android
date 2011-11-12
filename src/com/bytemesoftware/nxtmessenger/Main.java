package com.bytemesoftware.nxtmessengeradfree;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity
{
  private static final int REQUEST_DEVICE = 1;
  private static final int REQUEST_ENABLE_BT = 2;
  private BluetoothAdapter bluetoothAdapter;
  private String device_address;
  private String device_name;
  private ProgressDialog dialog;
  private String mailbox;

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      return;
      if (paramInt2 != -1)
        continue;
      String[] arrayOfString = paramIntent.getExtras().getString(DeviceListActivity.DEVICE_INFO).split("\n");
      if (arrayOfString.length == 2)
      {
        this.device_name = arrayOfString[0];
        this.device_address = arrayOfString[1];
        ((TextView)findViewById(2131099662)).setText(this.device_name);
        Log.d("NXT", "Using device: " + this.device_address);
        Toast.makeText(this, "Using: " + this.device_name, 0).show();
        continue;
      }
      this.device_name = "";
      this.device_address = "";
      ((TextView)findViewById(2131099662)).setText(2130968589);
      Toast.makeText(this, "No Device Selected", 0).show();
      continue;
      if (paramInt2 == -1)
        continue;
      Toast.makeText(this, "Bluetooth not enabled", 0).show();
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(5);
    Log.d("NXT", "Activity started");
    setContentView(2130903044);
    Spinner localSpinner = (Spinner)findViewById(2131099659);
    ArrayAdapter localArrayAdapter = ArrayAdapter.createFromResource(this, 2131034112, 17367048);
    localArrayAdapter.setDropDownViewResource(17367049);
    localSpinner.setAdapter(localArrayAdapter);
    this.mailbox = "1";
    localSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        Main.this.mailbox = paramAdapterView.getItemAtPosition(paramInt).toString();
      }

      public void onNothingSelected(AdapterView<?> paramAdapterView)
      {
      }
    });
    ((Button)findViewById(2131099658)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        EditText localEditText = (EditText)Main.this.findViewById(2131099657);
        Log.d("NXT", "Transfering address: " + Main.this.device_address);
        Log.d("NXT", "Transfering message: " + localEditText.getText().toString());
        Log.d("NXT", "Transfering mailbox: " + Main.this.mailbox);
        String str1 = Main.this.device_address;
        String str2 = localEditText.getText().toString();
        String str3 = Main.this.mailbox;
        if ((str1 != null) && (!str1.equals("")))
        {
          Main.SendTask localSendTask = new Main.SendTask(Main.this, null);
          String[] arrayOfString = new String[3];
          arrayOfString[0] = str1;
          arrayOfString[1] = str2;
          arrayOfString[2] = str3;
          localSendTask.execute(arrayOfString);
        }
        while (true)
        {
          return;
          Toast.makeText(Main.this, "Device selection seems to be invalid", 1).show();
        }
      }
    });
    this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if (this.bluetoothAdapter == null)
    {
      Toast.makeText(this, "Bluetooth is not available", 1).show();
      finish();
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2130903045, paramMenu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
    case 2131099655:
    }
    for (boolean bool = super.onOptionsItemSelected(paramMenuItem); ; bool = true)
    {
      return bool;
      startActivityForResult(new Intent(this, DeviceListActivity.class), 1);
    }
  }

  public void onPause()
  {
    super.onPause();
  }

  public void onResume()
  {
    super.onResume();
  }

  public void onStart()
  {
    super.onStart();
    if (!this.bluetoothAdapter.isEnabled())
      startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 2);
    startActivityForResult(new Intent(this, DeviceListActivity.class), 1);
  }

  private class SendTask extends AsyncTask<String, String, String>
  {
    private SendMessage sender;

    private SendTask()
    {
    }

    protected String doInBackground(String[] paramArrayOfString)
    {
      this.sender = new SendMessage(paramArrayOfString[0], paramArrayOfString[1], paramArrayOfString[2]);
      return this.sender.send();
    }

    protected void onPostExecute(String paramString)
    {
      Main.this.dialog.cancel();
      Toast.makeText(Main.this, paramString, 1).show();
    }

    protected void onPreExecute()
    {
      Main.this.dialog = new ProgressDialog(Main.this);
      Main.this.dialog.setMessage("Sending...");
      Main.this.dialog.setIndeterminate(true);
      Main.this.dialog.setCancelable(true);
      Main.this.dialog.show();
    }
  }
}

/* Location:           /home/daniel/nxt/com.bytemesoftware.adfree/classes_dex2jar.jar
 * Qualified Name:     com.bytemesoftware.nxtmessengeradfree.Main
 * JD-Core Version:    0.6.0
 */