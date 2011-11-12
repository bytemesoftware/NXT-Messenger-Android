package com.bytemesoftware.nxtmessengeradfree;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class CommandActivity extends Activity
{
  private static final int REQUEST_DEVICE = 1;
  private final int BATTERY_LEVEL = 2;
  private final int PLAY_SOUND = 3;
  private final int PROGRAM_NAME = 5;
  private final int START_PROGRAM = 1;
  private final int STOP_PROGRAM = 0;
  private final int STOP_SOUND = 4;
  private String deviceAddress;
  private String deviceName;
  private ProgressDialog dialog;

  private void displayBattery(int paramInt)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage("battery level is " + paramInt);
    localBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
      }
    });
    localBuilder.show();
  }

  private void startProgram()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage("Enter the program name to run");
    EditText localEditText = new EditText(this);
    localBuilder.setView(localEditText);
    localBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener(localEditText)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        String str = this.val$input.getText().toString();
        CommandActivity.SendTask localSendTask = new CommandActivity.SendTask(CommandActivity.this, null);
        String[] arrayOfString = new String[2];
        arrayOfString[0] = "1";
        arrayOfString[1] = str;
        localSendTask.execute(arrayOfString);
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
      }
    });
    localBuilder.show();
  }

  private void startSound()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage("Enter name of sound file");
    EditText localEditText = new EditText(this);
    localBuilder.setView(localEditText);
    localBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener(localEditText)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        String str = this.val$input.getText().toString();
        CommandActivity.SendTask localSendTask = new CommandActivity.SendTask(CommandActivity.this, null);
        String[] arrayOfString = new String[2];
        arrayOfString[0] = "3";
        arrayOfString[1] = str;
        localSendTask.execute(arrayOfString);
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
      }
    });
    localBuilder.show();
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default:
    case 1:
    }
    while (true)
    {
      return;
      if (paramInt2 == -1)
      {
        String[] arrayOfString = paramIntent.getExtras().getString(DeviceListActivity.DEVICE_INFO).split("\n");
        if (arrayOfString.length != 2)
          continue;
        this.deviceName = arrayOfString[0];
        this.deviceAddress = arrayOfString[1];
        SharedPreferences.Editor localEditor = getSharedPreferences("prefs", 0).edit();
        localEditor.putString("devicename", this.deviceName);
        localEditor.putString("deviceaddress", this.deviceAddress);
        localEditor.commit();
        Log.d("NXT", "Using device: " + this.deviceAddress);
        Toast.makeText(this, "Using: " + this.deviceName, 0).show();
        continue;
      }
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903040);
    ListView localListView = (ListView)findViewById(2131099648);
    String[] arrayOfString = new String[6];
    arrayOfString[0] = "Stop Program";
    arrayOfString[1] = "Run Program";
    arrayOfString[2] = "Get Battery Level";
    arrayOfString[3] = "Play Sound File";
    arrayOfString[4] = "Stop Sound Playback";
    arrayOfString[5] = "Current Program Name";
    localListView.setAdapter(new ArrayAdapter(this, 17367043, arrayOfString));
    localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        switch (paramInt)
        {
        default:
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        }
        while (true)
        {
          return;
          CommandActivity.SendTask localSendTask4 = new CommandActivity.SendTask(CommandActivity.this, null);
          String[] arrayOfString4 = new String[1];
          arrayOfString4[0] = "0";
          localSendTask4.execute(arrayOfString4);
          continue;
          CommandActivity.this.startProgram();
          continue;
          CommandActivity.SendTask localSendTask3 = new CommandActivity.SendTask(CommandActivity.this, null);
          String[] arrayOfString3 = new String[1];
          arrayOfString3[0] = "2";
          localSendTask3.execute(arrayOfString3);
          continue;
          CommandActivity.this.startSound();
          continue;
          CommandActivity.SendTask localSendTask2 = new CommandActivity.SendTask(CommandActivity.this, null);
          String[] arrayOfString2 = new String[1];
          arrayOfString2[0] = "4";
          localSendTask2.execute(arrayOfString2);
          continue;
          CommandActivity.SendTask localSendTask1 = new CommandActivity.SendTask(CommandActivity.this, null);
          String[] arrayOfString1 = new String[1];
          arrayOfString1[0] = "5";
          localSendTask1.execute(arrayOfString1);
        }
      }
    });
    SharedPreferences localSharedPreferences = getSharedPreferences("prefs", 0);
    this.deviceAddress = localSharedPreferences.getString("deviceaddress", "");
    this.deviceName = localSharedPreferences.getString("devicename", "");
    while (true)
    {
      if (!this.deviceAddress.equals(""))
      {
        Toast.makeText(this, "Using device " + this.deviceName, 0).show();
        return;
      }
      startActivityForResult(new Intent(this, DeviceListActivity.class), 1);
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

  private class SendTask extends AsyncTask<String, String, String>
  {
    private SendTask()
    {
    }

    protected String doInBackground(String[] paramArrayOfString)
    {
      SendCommand localSendCommand = new SendCommand(CommandActivity.this.deviceAddress);
      Object localObject = "";
      switch (new Integer(paramArrayOfString[0]).intValue())
      {
      default:
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      }
      while (true)
      {
        return localObject;
        localObject = localSendCommand.sendStop();
        continue;
        localObject = localSendCommand.sendStart(paramArrayOfString[1]);
        continue;
        localObject = localSendCommand.sendGetBatteryLevel();
        try
        {
          double d = Integer.parseInt((String)localObject) / 1000.0D;
          DecimalFormat localDecimalFormat = new DecimalFormat();
          localDecimalFormat.setMaximumFractionDigits(2);
          String str = "Battery level is " + localDecimalFormat.format(d) + "V";
          localObject = str;
          continue;
          localObject = localSendCommand.sendPlaySound(paramArrayOfString[1]);
          continue;
          localObject = localSendCommand.sendStopSound();
          continue;
          localObject = localSendCommand.sendGetProgramName();
        }
        catch (NumberFormatException localNumberFormatException)
        {
        }
      }
    }

    protected void onPostExecute(String paramString)
    {
      CommandActivity.this.dialog.cancel();
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(CommandActivity.this);
      localBuilder.setMessage(paramString);
      localBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
        }
      });
      localBuilder.show();
    }

    protected void onPreExecute()
    {
      CommandActivity.this.dialog = new ProgressDialog(CommandActivity.this);
      CommandActivity.this.dialog.setMessage("Sending...");
      CommandActivity.this.dialog.setIndeterminate(true);
      CommandActivity.this.dialog.setCancelable(true);
      CommandActivity.this.dialog.show();
    }
  }
}

/* Location:           /home/daniel/nxt/com.bytemesoftware.adfree/classes_dex2jar.jar
 * Qualified Name:     com.bytemesoftware.nxtmessengeradfree.CommandActivity
 * JD-Core Version:    0.6.0
 */