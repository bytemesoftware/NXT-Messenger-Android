package com.bytemesoftware.nxtmessengeradfree;

public class CommandFormatter
{
  public static byte[] batteryLevelFormatter()
  {
    byte[] arrayOfByte = new byte[2];
    arrayOfByte[1] = 11;
    return arrayOfByte;
  }

  public static byte[] playSoundFormatter(String paramString)
  {
    String str = paramString.concat("��");
    byte[] arrayOfByte1 = new byte[3 + str.length()];
    byte[] arrayOfByte2 = str.getBytes();
    arrayOfByte1[0] = 0;
    arrayOfByte1[1] = 2;
    arrayOfByte1[2] = 0;
    for (int i = 3; ; i++)
    {
      if (i >= arrayOfByte2.length)
        return arrayOfByte1;
      arrayOfByte1[i] = arrayOfByte2[(i - 3)];
    }
  }

  public static byte[] programNameFormatter()
  {
    byte[] arrayOfByte = new byte[2];
    arrayOfByte[1] = 17;
    return arrayOfByte;
  }

  public static byte[] startProgramFormatter(String paramString)
  {
    String str = paramString.concat("��");
    byte[] arrayOfByte1 = new byte[2 + str.length()];
    byte[] arrayOfByte2 = str.getBytes();
    arrayOfByte1[0] = 0;
    arrayOfByte1[1] = 0;
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfByte2.length)
        return arrayOfByte1;
      arrayOfByte1[(i + 2)] = arrayOfByte2[i];
    }
  }

  public static byte[] stopProgramFormatter()
  {
    byte[] arrayOfByte = new byte[2];
    arrayOfByte[1] = 1;
    return arrayOfByte;
  }

  public static byte[] stopSoundFormatter()
  {
    byte[] arrayOfByte = new byte[2];
    arrayOfByte[1] = 12;
    return arrayOfByte;
  }
}

/* Location:           /home/daniel/nxt/com.bytemesoftware.adfree/classes_dex2jar.jar
 * Qualified Name:     com.bytemesoftware.nxtmessengeradfree.CommandFormatter
 * JD-Core Version:    0.6.0
 */