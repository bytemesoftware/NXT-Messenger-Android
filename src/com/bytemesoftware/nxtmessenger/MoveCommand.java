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

public class MoveCommand
{
  public static final String STATUS = "status";
  public final int FAILURE = 0;
  public final int SUCCESS = 1;
  private BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
  private BluetoothDevice device;
  private InputStream inStream;
  private boolean isOpen = false;
  private OutputStream outStream;
  private BluetoothSocket socket;

  public MoveCommand(String paramString)
  {
    if (this.adapter != null)
      this.device = this.adapter.getRemoteDevice(paramString);
  }

  public boolean close()
  {
    this.isOpen = false;
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

  public boolean isOpen()
  {
    return this.isOpen;
  }

  public boolean open()
  {
    int i = 0;
    if (this.device != null);
    try
    {
      this.socket = this.device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
      this.socket.connect();
      Log.d("NXT", "Socket Created");
      this.isOpen = true;
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
          this.isOpen = true;
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
            this.isOpen = true;
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
          i = 0;
          Log.e("NXT", localIOException2.getMessage());
        }
      }
    }
  }

  public void sendMove(int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = MoveFormatter.goFormatter(paramInt1, paramInt2);
    try
    {
      this.outStream.write(arrayOfByte.length);
      this.outStream.write(arrayOfByte.length >> 8);
      this.outStream.write(arrayOfByte, 0, arrayOfByte.length);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        Log.e("NXT", "Could not write to stream OutputStream");
    }
  }

  public void sendStop()
  {
    byte[] arrayOfByte = MoveFormatter.stopFormatter();
    try
    {
      this.outStream.write(arrayOfByte.length);
      this.outStream.write(arrayOfByte.length >> 8);
      this.outStream.write(arrayOfByte, 0, arrayOfByte.length);
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        Log.e("NXT", "Could not write to stream OutputStream");
        localIOException.printStackTrace();
      }
    }
  }
}

/* Location:           /home/daniel/nxt/com.bytemesoftware.adfree/classes_dex2jar.jar
 * Qualified Name:     com.bytemesoftware.nxtmessengeradfree.MoveCommand
 * JD-Core Version:    0.6.0
 */