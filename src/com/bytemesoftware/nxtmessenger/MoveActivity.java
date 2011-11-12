package com.bytemesoftware.nxtmessengeradfree;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MoveActivity extends Activity
{
  private static final int REQUEST_DEVICE = 1;
  private boolean adShowen;
  private String deviceAddress;
  private String deviceName;
  private ProgressDialog dialog;
  private int direction;
  private int imageWidth;
  private int leftMotorPort;
  private MoveCommand move;
  private int power;
  private int rightMotorPort;
  private int turn;

  private double distance(double paramDouble1, double paramDouble2)
  {
    double d1 = this.imageWidth / 2;
    double d2 = this.imageWidth / 2;
    return Math.sqrt(Math.pow(d1 - paramDouble1, 2.0D) + Math.pow(d2 - paramDouble2, 2.0D));
  }

  private void motorDialog()
  {
    Dialog localDialog = new Dialog(this);
    localDialog.setContentView(2130903048);
    localDialog.setTitle("Select the motor ports");
    ((Button)localDialog.findViewById(2131099677)).setOnClickListener(new View.OnClickListener(localDialog)
    {
      public void onClick(View paramView)
      {
        RadioGroup localRadioGroup1 = (RadioGroup)this.val$dialog.findViewById(2131099668);
        RadioGroup localRadioGroup2 = (RadioGroup)this.val$dialog.findViewById(2131099673);
        switch (localRadioGroup1.getCheckedRadioButtonId())
        {
        default:
          switch (localRadioGroup2.getCheckedRadioButtonId())
          {
          default:
          case 2131099674:
          case 2131099675:
          case 2131099676:
          }
        case 2131099669:
        case 2131099670:
        case 2131099671:
        }
        while (true)
        {
          this.val$dialog.dismiss();
          return;
          MoveActivity.this.leftMotorPort = 0;
          break;
          MoveActivity.this.leftMotorPort = 1;
          break;
          MoveActivity.this.leftMotorPort = 2;
          break;
          MoveActivity.this.rightMotorPort = 0;
          continue;
          MoveActivity.this.rightMotorPort = 1;
          continue;
          MoveActivity.this.rightMotorPort = 2;
        }
      }
    });
    localDialog.show();
  }

  private void moveDevice(double paramDouble1, double paramDouble2)
  {
    double d1 = distance(paramDouble1, paramDouble2);
    double d2 = this.imageWidth / 2;
    if (d1 <= d2)
    {
      if (paramDouble2 <= d2)
        break label154;
      this.power = new Double(-1L * Math.round(100.0D * (d1 / d2))).intValue();
      if (paramDouble1 <= d2)
        break label188;
      this.turn = new Double(1L * Math.round(100.0D * ((paramDouble1 - d2) / d2))).intValue();
      label103: if (this.turn <= 0)
        break label224;
      this.move.sendMove(this.direction * this.power, this.leftMotorPort);
      this.move.sendMove(this.direction * (100 - this.turn), this.rightMotorPort);
    }
    while (true)
    {
      return;
      label154: this.power = new Double(1L * Math.round(100.0D * (d1 / d2))).intValue();
      break;
      label188: this.turn = new Double(-1L * Math.round(100.0D * ((d2 - paramDouble1) / d2))).intValue();
      break label103;
      label224: this.move.sendMove(this.direction * this.power, this.rightMotorPort);
      this.move.sendMove(this.direction * (100 + this.turn), this.leftMotorPort);
    }
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
        if (arrayOfString.length == 2)
        {
          this.deviceName = arrayOfString[0];
          this.deviceAddress = arrayOfString[1];
          Toast.makeText(this, "Using: " + this.deviceName, 0).show();
        }
        this.move = new MoveCommand(this.deviceAddress);
        this.move.open();
        SharedPreferences.Editor localEditor = getSharedPreferences("prefs", 0).edit();
        localEditor.putString("devicename", this.deviceName);
        localEditor.putString("deviceaddress", this.deviceAddress);
        localEditor.commit();
        continue;
      }
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903047);
    SharedPreferences localSharedPreferences = getSharedPreferences("prefs", 0);
    this.deviceAddress = localSharedPreferences.getString("deviceaddress", "");
    this.deviceName = localSharedPreferences.getString("devicename", "");
    while (true)
    {
      if (!this.deviceAddress.equals(""))
      {
        this.adShowen = false;
        this.direction = 1;
        this.power = 0;
        this.turn = 0;
        this.move = new MoveCommand(this.deviceAddress);
        ((ImageView)findViewById(2131099666)).setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
          {
            switch (0xFF & paramMotionEvent.getAction())
            {
            case 1:
            default:
            case 2:
            case 0:
            }
            while (true)
            {
              return true;
              MoveActivity.this.moveDevice(paramMotionEvent.getX(), paramMotionEvent.getY());
              continue;
              MoveActivity.this.moveDevice(paramMotionEvent.getX(), paramMotionEvent.getY());
            }
          }
        });
        ((Button)findViewById(2131099665)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            MoveActivity.this.move.sendStop();
          }
        });
        ((Button)findViewById(2131099664)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            MoveActivity.this.direction = (-1 * MoveActivity.this.direction);
            MoveActivity.this.move.sendStop();
            if (MoveActivity.this.turn > 0)
            {
              MoveActivity.this.move.sendMove(MoveActivity.this.direction * MoveActivity.this.power, MoveActivity.this.leftMotorPort);
              MoveActivity.this.move.sendMove(MoveActivity.this.direction * (100 - MoveActivity.this.turn), MoveActivity.this.rightMotorPort);
            }
            while (true)
            {
              return;
              MoveActivity.this.move.sendMove(MoveActivity.this.direction * MoveActivity.this.power, MoveActivity.this.rightMotorPort);
              MoveActivity.this.move.sendMove(MoveActivity.this.direction * (100 + MoveActivity.this.turn), MoveActivity.this.leftMotorPort);
            }
          }
        });
        return;
      }
      startActivityForResult(new Intent(this, DeviceListActivity.class), 1);
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2130903049, paramMenu);
    return true;
  }

  public void onDestroy()
  {
    super.onDestroy();
    if (this.move.isOpen())
      this.move.close();
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    boolean bool;
    switch (paramMenuItem.getItemId())
    {
    default:
      bool = super.onOptionsItemSelected(paramMenuItem);
    case 2131099655:
    case 2131099678:
    }
    while (true)
    {
      return bool;
      startActivityForResult(new Intent(this, DeviceListActivity.class), 1);
      bool = true;
      continue;
      motorDialog();
      bool = true;
    }
  }

  public void onPause()
  {
    super.onPause();
    if (this.move.isOpen())
      this.move.close();
  }

  public void onResume()
  {
    super.onResume();
    ConnectTask localConnectTask = new ConnectTask(null);
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "";
    localConnectTask.execute(arrayOfString);
  }

  public void onStart()
  {
    super.onStart();
    this.imageWidth = getWindowManager().getDefaultDisplay().getWidth();
    this.imageWidth = (100 * (this.imageWidth / 100));
    Log.d("NXT", "width " + this.imageWidth);
    motorDialog();
  }

  private class ConnectTask extends AsyncTask<String, String, String>
  {
    private ConnectTask()
    {
    }

    protected String doInBackground(String[] paramArrayOfString)
    {
      if (!MoveActivity.this.move.isOpen())
        MoveActivity.this.move.open();
      return "";
    }

    protected void onPostExecute(String paramString)
    {
      MoveActivity.this.dialog.cancel();
    }

    protected void onPreExecute()
    {
      MoveActivity.this.dialog = new ProgressDialog(MoveActivity.this);
      MoveActivity.this.dialog.setMessage("Connecting...");
      MoveActivity.this.dialog.setIndeterminate(true);
      MoveActivity.this.dialog.setCancelable(true);
      MoveActivity.this.dialog.show();
    }
  }
}

/* Location:           /home/daniel/nxt/com.bytemesoftware.adfree/classes_dex2jar.jar
 * Qualified Name:     com.bytemesoftware.nxtmessengeradfree.MoveActivity
 * JD-Core Version:    0.6.0
 */