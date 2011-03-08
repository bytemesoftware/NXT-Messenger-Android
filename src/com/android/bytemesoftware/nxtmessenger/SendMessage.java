package com.android.bytemesoftware.nxtmessenger;

import java.io.BufferedInputStream;
import android.util.Log;

/**
 * The MIT License
 *
 * Copyright (c) 2011 Daniel Ward admin(at)bytemesoftware.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * No person, persons, or entity is allowed to sell or make any profit off this
 * software or variants of this software other than the author.
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 * 
 * This is a class to model a messaging object that communicates with a specific
 * NXT device.
 *  
 * @author Daniel Ward
 * @version 1.00
 * @date March 7 2011
 */

public class SendMessage {
        
    /**
     * Will create a object to send a message to the NXT device with the given
     * bluetooth MAC address of that device.
     * @param address The MAC address of the NXT device
     */
    protected SendMessage()
    {        
    }
    
    /**
     * This method will attempt to send a message to the NXT device using the
     * given mailbox number.
     * 
     * @param mailbox The mailbox on the device to send the message to
     * @param message The message to send to the device
     */
    public static byte[] formatMessage(String message, int mailbox)
    {
        byte[] result;
        
        ////////////////////////////////////////////////////////////////////
        //-------------------FORMAT THE MESSAGE---------------------------//
           
        message = message.concat("\0"); //must be null terminated
       
        //convert the message to byte array
        byte[] outMessage = message.getBytes(); 

        //The message length is the string plus 4 bytes for the bluetooth header
        int messageLength = outMessage.length + 4;        
        
        result = new byte[messageLength];

        result[0] = 0x00; //Command with reply
        result[1] = 0x09; //Command for a messageWrite operation
        result[2] = (byte)(mailbox-1); //The mailbox with adjustment of -1      
        result[3] = (byte)(outMessage.length); // the lenght of the given message

        //move the messsage into the toSend array
        for(int i = 0; i < outMessage.length; i++)
            result[i+4] = outMessage[i];
        
        
           
        return result;
        
    }
    
    public static byte[] readMessage(BufferedInputStream inputStream){
    	/////////////////////////////////////////////////
        //---------------GET THE REPLY----------------//
        
        /*
         * Reply for message write commnd
         * byte 0 : bluetooth header, length of message
         * byte 1 : bluetooth header, 0x00
         * byte 2 : 0x02
         * byte 3 : 0x09
         * byte 4 : status message form device
         */
    	//byte array to receive the reply
    	
        byte[] reply = new byte[5];
        
        //reply[4] = 0x01;
        
        try{
       
        	inputStream.read(reply, 0, reply.length);
              
        }
        catch(java.io.IOException e)
        {
            Log.e("SEND MESSAGE", "Could not read stream");
        }
        
        return reply;
    }
    
    //
    public static String status(byte statusByte){
    	
    	String result = "Invalid Status";
    	switch(statusByte)
    	{
	    	case 0x00:
				result = "Success";
				break;
    		case 0x20:
    			result = "Pending communication transaction in progress";
    			break;
    		case 0x40:
    			result = "Specified mailbox queue is empty";
    			break;
    		case (byte)0xBD:
    			result = "Request failed (i.e. specified file not found)";
    			break;
    		case (byte)0xBE:
    			result = "Unknown command opcode";
    			break;
    		case (byte)0xBF:
    			result = "Insane packet";
    			break;
    		case (byte)0xC0:
    			result = "Data contains out-of-range values";
    			break;
    		case (byte)0xDD:
    			result = "Communication bus error";
    			break;
    		case (byte)0xDE:
    			result = "No free memory in communication buffer";
    			break;
    		case (byte)0xDF:
    			result = "Specified channel/connection is not valid";
    			break;
    		case (byte)0xE0:
    			result = "Specified channel/connection not configured or busy";
    			break;
    		case (byte)0xEC:
    			result = "No active program";
    			break;
    		case (byte)0xED:
    			result = "Illegal size specified";
    			break;
    		case (byte)0xEE:
    			result = "Illegal mailbox queue ID specified";
    			break;
    		case (byte)0xEF:
    			result = "Attempted to access invalid field of a structure";
    			break;
    		case (byte)0xF0:
    			result = "Bad input or output specified";
    			break;
    		case (byte) 0xFB:
    			result = "Insufficient memory available";
    			break;
    		case (byte)0xFF:
    			result = "Bad arguments";
    			break;
    	
    	}
    	
    	return result;
    	
    }
}