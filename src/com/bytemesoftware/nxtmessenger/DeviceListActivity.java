package com.bytemesoftware.nxtmessengeradfree;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Iterator;
import java.util.Set;

public class DeviceListActivity extends Activity
{
  private static final boolean D = true;
  public static String DEVICE_INFO;
  public static String EXTRA_DEVICE_ADDRESS = "device_address";
  private static final String TAG = "DeviceListActivity";
  private BluetoothAdapter mBtAdapter;
  private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener()
  {
    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      DeviceListActivity.this.mBtAdapter.cancelDiscovery();
      String str = ((TextView)paramView).getText().toString();
      Intent localIntent = new Intent();
      localIntent.putExtra(DeviceListActivity.DEVICE_INFO, str);
      DeviceListActivity.this.setResult(-1, localIntent);
      DeviceListActivity.this.finish();
    }
  };
  private ArrayAdapter<String> mNewDevicesArrayAdapter;
  private ArrayAdapter<String> mPairedDevicesArrayAdapter;
  private final BroadcastReceiver mReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      String str1 = paramIntent.getAction();
      if ("android.bluetooth.device.action.FOUND".equals(str1))
      {
        BluetoothDevice localBluetoothDevice = (BluetoothDevice)paramIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (localBluetoothDevice.getBondState() != 12)
          DeviceListActivity.this.mNewDevicesArrayAdapter.add(localBluetoothDevice.getName() + "\n" + localBluetoothDevice.getAddress());
      }
      while (true)
      {
        return;
        if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(str1))
        {
          DeviceListActivity.this.setProgressBarIndeterminateVisibility(false);
          DeviceListActivity.this.setTitle(2130968587);
          if (DeviceListActivity.this.mNewDevicesArrayAdapter.getCount() != 0)
            continue;
          String str2 = DeviceListActivity.this.getResources().getText(2130968588).toString();
          DeviceListActivity.this.mNewDevicesArrayAdapter.add(str2);
          continue;
        }
      }
    }
  };

  static
  {
    DEVICE_INFO = "device_info";
  }

  private void doDiscovery()
  {
    Log.d("DeviceListActivity", "doDiscovery()");
    setProgressBarIndeterminateVisibility(true);
    setTitle(2130968586);
    findViewById(2131099651).setVisibility(0);
    if (this.mBtAdapter.isDiscovering())
      this.mBtAdapter.cancelDiscovery();
    this.mBtAdapter.startDiscovery();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(5);
    setContentView(2130903041);
    setResult(0);
    ((Button)findViewById(2131099653)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        DeviceListActivity.this.doDiscovery();
        paramView.setVisibility(8);
      }
    });
    this.mPairedDevicesArrayAdapter = new ArrayAdapter(this, 2130903042);
    this.mNewDevicesArrayAdapter = new ArrayAdapter(this, 2130903042);
    ListView localListView1 = (ListView)findViewById(2131099650);
    localListView1.setAdapter(this.mPairedDevicesArrayAdapter);
    localListView1.setOnItemClickListener(this.mDeviceClickListener);
    ListView localListView2 = (ListView)findViewById(2131099652);
    localListView2.setAdapter(this.mNewDevicesArrayAdapter);
    localListView2.setOnItemClickListener(this.mDeviceClickListener);
    IntentFilter localIntentFilter1 = new IntentFilter("android.bluetooth.device.action.FOUND");
    registerReceiver(this.mReceiver, localIntentFilter1);
    IntentFilter localIntentFilter2 = new IntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
    registerReceiver(this.mReceiver, localIntentFilter2);
    this.mBtAdapter = BluetoothAdapter.getDefaultAdapter();
    Set localSet = this.mBtAdapter.getBondedDevices();
    Iterator localIterator;
    if (localSet.size() > 0)
    {
      findViewById(2131099649).setVisibility(0);
      localIterator = localSet.iterator();
      if (localIterator.hasNext());
    }
    while (true)
    {
      return;
      BluetoothDevice localBluetoothDevice = (BluetoothDevice)localIterator.next();
      this.mPairedDevicesArrayAdapter.add(localBluetoothDevice.getName() + "\n" + localBluetoothDevice.getAddress());
      break;
      String str = getResources().getText(2130968585).toString();
      this.mPairedDevicesArrayAdapter.add(str);
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    if (this.mBtAdapter != null)
      this.mBtAdapter.cancelDiscovery();
    unregisterReceiver(this.mReceiver);
  }
}

/* Location:           /home/daniel/nxt/com.bytemesoftware.adfree/classes_dex2jar.jar
 * Qualified Name:     com.bytemesoftware.nxtmessengeradfree.DeviceListActivity
 * JD-Core Version:    0.6.0
 */