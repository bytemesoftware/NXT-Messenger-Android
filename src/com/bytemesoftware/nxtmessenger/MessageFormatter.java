package com.bytemesoftware.nxtmessengeradfree;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.IOException;

public class MessageFormatter
{
  public static byte[] formatMessage(String paramString, int paramInt)
  {
    byte[] arrayOfByte1 = paramString.concat("��").getBytes();
    byte[] arrayOfByte2 = new byte[4 + arrayOfByte1.length];
    arrayOfByte2[0] = 0;
    arrayOfByte2[1] = 9;
    arrayOfByte2[2] = (byte)(paramInt - 1);
    arrayOfByte2[3] = (byte)arrayOfByte1.length;
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfByte1.length)
        return arrayOfByte2;
      arrayOfByte2[(i + 4)] = arrayOfByte1[i];
    }
  }

  public static byte[] readMessage(BufferedInputStream paramBufferedInputStream)
  {
    byte[] arrayOfByte = new byte[5];
    try
    {
      paramBufferedInputStream.read(arrayOfByte, 0, arrayOfByte.length);
      return arrayOfByte;
    }
    catch (IOException localIOException)
    {
      while (true)
        Log.e("SEND MESSAGE", "Could not read stream");
    }
  }

  public static String status(byte paramByte)
  {
    String str = "Invalid Status";
    switch (paramByte)
    {
    default:
    case 0:
    case 32:
    case 64:
    case -67:
    case -66:
    case -65:
    case -64:
    case -35:
    case -34:
    case -33:
    case -32:
    case -20:
    case -19:
    case -18:
    case -17:
    case -16:
    case -5:
    case -1:
    }
    while (true)
    {
      return str;
      str = "Success";
      continue;
      str = "Pending communication transaction in progress";
      continue;
      str = "Specified mailbox queue is empty";
      continue;
      str = "Request failed (i.e. specified file not found)";
      continue;
      str = "Unknown command opcode";
      continue;
      str = "Insane packet";
      continue;
      str = "Data contains out-of-range values";
      continue;
      str = "Communication bus error";
      continue;
      str = "No free memory in communication buffer";
      continue;
      str = "Specified channel/connection is not valid";
      continue;
      str = "Specified channel/connection not configured or busy";
      continue;
      str = "No active program";
      continue;
      str = "Illegal size specified";
      continue;
      str = "Illegal mailbox queue ID specified";
      continue;
      str = "Attempted to access invalid field of a structure";
      continue;
      str = "Bad input or output specified";
      continue;
      str = "Insufficient memory available";
      continue;
      str = "Bad arguments";
    }
  }
}

/* Location:           /home/daniel/nxt/com.bytemesoftware.adfree/classes_dex2jar.jar
 * Qualified Name:     com.bytemesoftware.nxtmessengeradfree.MessageFormatter
 * JD-Core Version:    0.6.0
 */