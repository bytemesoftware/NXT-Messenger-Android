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

public class SendMessage
{
  public static final String STATUS = "status";
  public final int FAILURE = 0;
  public final int SUCCESS = 1;
  private BluetoothAdapter adapter;
  private String address = "";
  private BluetoothDevice device;
  private int mailbox;
  private String message;
  private String result = "";
  private BluetoothSocket socket;

  public SendMessage(String paramString1, String paramString2, String paramString3)
  {
    this.address = paramString1;
    this.message = paramString2;
    this.mailbox = new Integer(paramString3).intValue();
    this.adapter = BluetoothAdapter.getDefaultAdapter();
    if (this.adapter != null)
      this.device = this.adapter.getRemoteDevice(this.address);
    while (true)
    {
      return;
      this.result = "Could not open the Bluetooth Adapter";
    }
  }

  protected String send()
  {
    if (this.device != null);
    while (true)
    {
      try
      {
        this.socket = this.device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
        this.socket.connect();
        Log.d("NXT", "Socket Created");
        Log.d("NXT", "Sending message");
        arrayOfByte1 = MessageFormatter.formatMessage(this.message, this.mailbox);
        localOutputStream = null;
        localObject = null;
      }
      catch (IOException localIOException6)
      {
        try
        {
          localOutputStream = this.socket.getOutputStream();
          InputStream localInputStream = this.socket.getInputStream();
          localObject = localInputStream;
        }
        catch (IOException localIOException6)
        {
          try
          {
            byte[] arrayOfByte1;
            localOutputStream.write(arrayOfByte1.length);
            localOutputStream.write(arrayOfByte1.length >> 8);
            localOutputStream.write(arrayOfByte1, 0, arrayOfByte1.length);
          }
          catch (IOException localIOException6)
          {
            try
            {
              int i = localObject.read() + (localObject.read() >> 8);
              Log.d("NXT", "Getting mesage of length: " + i);
              byte[] arrayOfByte2 = new byte[i];
              localObject.read(arrayOfByte2);
              this.result = MessageFormatter.status(arrayOfByte2[2]);
              Log.d("NXT", "Reply status: " + this.result);
            }
            catch (IOException localIOException6)
            {
              try
              {
                Object localObject;
                localObject.close();
              }
              catch (IOException localIOException6)
              {
                try
                {
                  OutputStream localOutputStream;
                  localOutputStream.close();
                }
                catch (IOException localIOException6)
                {
                  try
                  {
                    this.socket.close();
                    return this.result;
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
                      localSecurityException.printStackTrace();
                    }
                    catch (NoSuchMethodException localNoSuchMethodException)
                    {
                      localNoSuchMethodException.printStackTrace();
                    }
                    catch (IOException localIOException8)
                    {
                      Log.e("NXT", "Socket Creation Failed");
                      localIOException1.printStackTrace();
                    }
                    catch (IllegalArgumentException localIllegalArgumentException)
                    {
                      localIOException1.printStackTrace();
                    }
                    catch (IllegalAccessException localIllegalAccessException)
                    {
                      localIOException1.printStackTrace();
                    }
                    catch (InvocationTargetException localInvocationTargetException)
                    {
                      localIOException1.printStackTrace();
                    }
                    continue;
                    localIOException2 = localIOException2;
                    Log.e("NXT", "Could not get streams");
                    localIOException2.printStackTrace();
                    continue;
                    localIOException3 = localIOException3;
                    Log.e("NXT", "Could not write to stream OutputStream");
                    localIOException3.printStackTrace();
                    continue;
                    localIOException4 = localIOException4;
                    Log.e("NXT", "Could not read stream");
                    localIOException4.printStackTrace();
                    continue;
                    localIOException5 = localIOException5;
                    Log.e("NXT", "Could not close input stream");
                    continue;
                    localIOException6 = localIOException6;
                    Log.e("NXT", "Could not close output stream");
                    continue;
                  }
                  catch (IOException localIOException7)
                  {
                    Log.e("NXT", "Could not close socket");
                    continue;
                  }
                }
              }
            }
          }
        }
      }
      this.result = "Could not open bluetooth device";
    }
  }
}

/* Location:           /home/daniel/nxt/com.bytemesoftware.adfree/classes_dex2jar.jar
 * Qualified Name:     com.bytemesoftware.nxtmessengeradfree.SendMessage
 * JD-Core Version:    0.6.0
 */