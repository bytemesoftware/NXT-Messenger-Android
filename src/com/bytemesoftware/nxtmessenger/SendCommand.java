package com.bytemesoftware.nxtmessengeradfree;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class SendCommand
{
  public static final String STATUS = "status";
  public final int FAILURE = 0;
  public final int SUCCESS = 1;
  private BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
  private BluetoothDevice device;
  private InputStream inStream;
  private OutputStream outStream;
  private BluetoothSocket socket;

  public SendCommand(String paramString)
  {
    if (this.adapter != null)
      this.device = this.adapter.getRemoteDevice(paramString);
  }

  private boolean closeSocket()
  {
    try
    {
      this.inStream.close();
    }
    catch (IOException localIOException2)
    {
      try
      {
        this.outStream.close();
      }
      catch (IOException localIOException2)
      {
        try
        {
          while (true)
          {
            this.socket.close();
            return false;
            localIOException1 = localIOException1;
            Log.e("NXT", "Could not close input stream");
            continue;
            localIOException2 = localIOException2;
            Log.e("NXT", "Could not close output stream");
          }
        }
        catch (IOException localIOException3)
        {
          while (true)
            Log.e("NXT", "Could not close socket");
        }
      }
    }
  }

  private boolean setupSocket()
  {
    int i = 0;
    if (this.device != null);
    try
    {
      this.socket = this.device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
      this.socket.connect();
      Log.d("NXT", "Socket Created");
      this.outStream = null;
      this.inStream = null;
    }
    catch (IOException localIOException1)
    {
      try
      {
        while (true)
        {
          this.outStream = this.socket.getOutputStream();
          this.inStream = this.socket.getInputStream();
          i = 1;
          Log.d("NXT", "Streams created message");
          return i;
          localIOException1 = localIOException1;
          try
          {
            Class localClass = this.device.getClass();
            Class[] arrayOfClass = new Class[1];
            arrayOfClass[0] = Integer.TYPE;
            Method localMethod = localClass.getMethod("createRfcommSocket", arrayOfClass);
            BluetoothDevice localBluetoothDevice = this.device;
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = Integer.valueOf(1);
            this.socket = ((BluetoothSocket)localMethod.invoke(localBluetoothDevice, arrayOfObject));
            this.socket.connect();
            Log.d("NXT", "Socket Created");
          }
          catch (SecurityException localSecurityException)
          {
            Log.e("NXT", localSecurityException.getMessage());
          }
          catch (NoSuchMethodException localNoSuchMethodException)
          {
            Log.e("NXT", localNoSuchMethodException.getMessage());
          }
          catch (IOException localIOException3)
          {
            Log.e("NXT", "Socket Creation Failed");
            localIOException1.printStackTrace();
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            Log.e("NXT", localIllegalArgumentException.getMessage());
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
            Log.e("NXT", localIllegalAccessException.getMessage());
          }
          catch (InvocationTargetException localInvocationTargetException)
          {
            Log.e("NXT", localInvocationTargetException.getMessage());
          }
        }
      }
      catch (IOException localIOException2)
      {
        while (true)
        {
          Log.e("NXT", "Could not get streams");
          i = 1;
          Log.e("NXT", localIOException2.getMessage());
        }
      }
    }
  }

  public String sendGetBatteryLevel()
  {
    String str = "";
    byte[] arrayOfByte1;
    if (setupSocket())
      arrayOfByte1 = CommandFormatter.batteryLevelFormatter();
    try
    {
      this.outStream.write(arrayOfByte1.length);
      this.outStream.write(arrayOfByte1.length >> 8);
      this.outStream.write(arrayOfByte1, 0, arrayOfByte1.length);
    }
    catch (IOException localIOException1)
    {
      try
      {
        while (true)
        {
          int i = this.inStream.read() + (this.inStream.read() >> 8);
          Log.d("NXT", "Getting mesage of length: " + i);
          byte[] arrayOfByte2 = new byte[i];
          this.inStream.read(arrayOfByte2);
          str = MessageFormatter.status(arrayOfByte2[2]);
          if (str.equals("Success"))
          {
            int j = 0xFF & arrayOfByte2[3] | (0xFF & arrayOfByte2[4]) << 8;
            str = j;
          }
          Log.d("NXT", "Reply status: " + str);
          closeSocket();
          return str;
          localIOException1 = localIOException1;
          Log.e("NXT", "Could not write to stream OutputStream");
          localIOException1.printStackTrace();
        }
      }
      catch (IOException localIOException2)
      {
        while (true)
        {
          Log.e("NXT", "Could not read stream");
          localIOException2.printStackTrace();
        }
      }
    }
  }

  public String sendGetProgramName()
  {
    String str = "";
    byte[] arrayOfByte1;
    if (setupSocket())
      arrayOfByte1 = CommandFormatter.programNameFormatter();
    try
    {
      this.outStream.write(arrayOfByte1.length);
      this.outStream.write(arrayOfByte1.length >> 8);
      this.outStream.write(arrayOfByte1, 0, arrayOfByte1.length);
    }
    catch (IOException localIOException1)
    {
      try
      {
        while (true)
        {
          int i = this.inStream.read() + (this.inStream.read() >> 8);
          Log.d("NXT", "Getting mesage of length: " + i);
          byte[] arrayOfByte2 = new byte[i];
          this.inStream.read(arrayOfByte2);
          str = MessageFormatter.status(arrayOfByte2[2]);
          if (str.equals("Success"))
            str = new String(arrayOfByte2, 3, arrayOfByte2.length - 4);
          Log.d("NXT", "Reply status: " + str);
          closeSocket();
          return str;
          localIOException1 = localIOException1;
          Log.e("NXT", "Could not write to stream OutputStream");
          localIOException1.printStackTrace();
        }
      }
      catch (IOException localIOException2)
      {
        while (true)
        {
          Log.e("NXT", "Could not read stream");
          localIOException2.printStackTrace();
        }
      }
    }
  }

  public String sendPlaySound(String paramString)
  {
    String str = "";
    byte[] arrayOfByte1;
    if (setupSocket())
      arrayOfByte1 = CommandFormatter.playSoundFormatter(paramString);
    try
    {
      this.outStream.write(arrayOfByte1.length);
      this.outStream.write(arrayOfByte1.length >> 8);
      this.outStream.write(arrayOfByte1, 0, arrayOfByte1.length);
    }
    catch (IOException localIOException1)
    {
      try
      {
        while (true)
        {
          int i = this.inStream.read() + (this.inStream.read() >> 8);
          Log.d("NXT", "Getting mesage of length: " + i);
          byte[] arrayOfByte2 = new byte[i];
          this.inStream.read(arrayOfByte2);
          str = MessageFormatter.status(arrayOfByte2[2]);
          Log.d("NXT", "Reply status: " + str);
          closeSocket();
          return str;
          localIOException1 = localIOException1;
          Log.e("NXT", "Could not write to stream OutputStream");
          localIOException1.printStackTrace();
        }
      }
      catch (IOException localIOException2)
      {
        while (true)
        {
          Log.e("NXT", "Could not read stream");
          localIOException2.printStackTrace();
        }
      }
    }
  }

  public String sendStart(String paramString)
  {
    String str = "";
    byte[] arrayOfByte1;
    if (setupSocket())
      arrayOfByte1 = CommandFormatter.startProgramFormatter(paramString);
    try
    {
      this.outStream.write(arrayOfByte1.length);
      this.outStream.write(arrayOfByte1.length >> 8);
      this.outStream.write(arrayOfByte1, 0, arrayOfByte1.length);
    }
    catch (IOException localIOException1)
    {
      try
      {
        while (true)
        {
          int i = this.inStream.read() + (this.inStream.read() >> 8);
          Log.d("NXT", "Getting mesage of length: " + i);
          byte[] arrayOfByte2 = new byte[i];
          this.inStream.read(arrayOfByte2);
          str = MessageFormatter.status(arrayOfByte2[2]);
          Log.d("NXT", "Reply status: " + str);
          closeSocket();
          return str;
          localIOException1 = localIOException1;
          Log.e("NXT", "Could not write to stream OutputStream");
          localIOException1.printStackTrace();
        }
      }
      catch (IOException localIOException2)
      {
        while (true)
        {
          Log.e("NXT", "Could not read stream");
          localIOException2.printStackTrace();
        }
      }
    }
  }

  public String sendStop()
  {
    String str = "";
    byte[] arrayOfByte1;
    if (setupSocket())
      arrayOfByte1 = CommandFormatter.stopProgramFormatter();
    try
    {
      this.outStream.write(arrayOfByte1.length);
      this.outStream.write(arrayOfByte1.length >> 8);
      this.outStream.write(arrayOfByte1, 0, arrayOfByte1.length);
    }
    catch (IOException localIOException1)
    {
      try
      {
        while (true)
        {
          int i = this.inStream.read() + (this.inStream.read() >> 8);
          Log.d("NXT", "Getting mesage of length: " + i);
          byte[] arrayOfByte2 = new byte[i];
          this.inStream.read(arrayOfByte2);
          str = MessageFormatter.status(arrayOfByte2[2]);
          Log.d("NXT", "Reply status: " + str);
          closeSocket();
          return str;
          localIOException1 = localIOException1;
          Log.e("NXT", "Could not write to stream OutputStream");
          localIOException1.printStackTrace();
        }
      }
      catch (IOException localIOException2)
      {
        while (true)
        {
          Log.e("NXT", "Could not read stream");
          localIOException2.printStackTrace();
        }
      }
    }
  }

  public String sendStopSound()
  {
    String str = "";
    byte[] arrayOfByte1;
    if (setupSocket())
      arrayOfByte1 = CommandFormatter.stopSoundFormatter();
    try
    {
      this.outStream.write(arrayOfByte1.length);
      this.outStream.write(arrayOfByte1.length >> 8);
      this.outStream.write(arrayOfByte1, 0, arrayOfByte1.length);
    }
    catch (IOException localIOException1)
    {
      try
      {
        while (true)
        {
          int i = this.inStream.read() + (this.inStream.read() >> 8);
          Log.d("NXT", "Getting mesage of length: " + i);
          byte[] arrayOfByte2 = new byte[i];
          this.inStream.read(arrayOfByte2);
          str = MessageFormatter.status(arrayOfByte2[2]);
          Log.d("NXT", "Reply status: " + str);
          closeSocket();
          return str;
          localIOException1 = localIOException1;
          Log.e("NXT", "Could not write to stream OutputStream");
          localIOException1.printStackTrace();
        }
      }
      catch (IOException localIOException2)
      {
        while (true)
        {
          Log.e("NXT", "Could not read stream");
          localIOException2.printStackTrace();
        }
      }
    }
  }
}

/* Location:           /home/daniel/nxt/com.bytemesoftware.adfree/classes_dex2jar.jar
 * Qualified Name:     com.bytemesoftware.nxtmessengeradfree.SendCommand
 * JD-Core Version:    0.6.0
 */