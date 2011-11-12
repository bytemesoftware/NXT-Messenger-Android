package com.bytemesoftware.nxtmessengeradfree;

public class MoveFormatter
{
  public static byte[] goFormatter(int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[13];
    arrayOfByte[0] = new Integer(128).byteValue();
    arrayOfByte[1] = 4;
    arrayOfByte[2] = new Integer(paramInt2).byteValue();
    arrayOfByte[3] = new Integer(paramInt1).byteValue();
    arrayOfByte[4] = 1;
    arrayOfByte[5] = 0;
    arrayOfByte[6] = 0;
    arrayOfByte[7] = 32;
    arrayOfByte[8] = 0;
    arrayOfByte[9] = 0;
    arrayOfByte[10] = 0;
    arrayOfByte[11] = 0;
    arrayOfByte[12] = 0;
    return arrayOfByte;
  }

  public static byte[] stopFormatter()
  {
    byte[] arrayOfByte = new byte[13];
    arrayOfByte[0] = new Integer(128).byteValue();
    arrayOfByte[1] = 4;
    arrayOfByte[2] = new Integer(255).byteValue();
    arrayOfByte[3] = 0;
    arrayOfByte[4] = 2;
    arrayOfByte[5] = 0;
    arrayOfByte[6] = 0;
    arrayOfByte[7] = 0;
    arrayOfByte[8] = 0;
    arrayOfByte[9] = 0;
    arrayOfByte[10] = 0;
    arrayOfByte[11] = 0;
    arrayOfByte[12] = 0;
    return arrayOfByte;
  }
}

/* Location:           /home/daniel/nxt/com.bytemesoftware.adfree/classes_dex2jar.jar
 * Qualified Name:     com.bytemesoftware.nxtmessengeradfree.MoveFormatter
 * JD-Core Version:    0.6.0
 */