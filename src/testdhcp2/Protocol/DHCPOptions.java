/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testdhcp2.Protocol;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author root
 */



public class DHCPOptions {
    
    private byte[] optionsPacket;
    private byte[] option53;
    private byte[] option60;
    private byte option0;
    private byte[] option61;
    private byte[] option125;
    private int len;
    
    
    
    public DHCPOptions()
    {
         len=0;
         option53 = new byte[3];
         option60 = new byte[12];
         option0 = 0;
         option61 =new byte[18];
         optionsPacket = new byte[34];
        
    }

    public byte[] getOptionsPacket() {

       // int optionsPacketLenght= option53.length + option60.length + option0.length + option61.length;
        
       //240
        optionsPacket[0] = option53[0];
        optionsPacket[1] = option53[1];
        optionsPacket[2] = option53[2];
        
        optionsPacket[3] = option60[0];
        optionsPacket[4] = option60[1];
        optionsPacket[5] = option60[2];
        optionsPacket[6] = option60[3];
        optionsPacket[7] = option60[4];
        optionsPacket[8] = option60[5];
        optionsPacket[9] = option60[6];
        optionsPacket[10] = option60[7];
        optionsPacket[11] = option60[8];
        optionsPacket[12] = option60[9];
        optionsPacket[13] = option60[10];
        optionsPacket[14] = option60[11];
        
        optionsPacket[15] = option0;
        
        optionsPacket[16] = option61[0];
        optionsPacket[17] = option61[1];
        optionsPacket[18] = option61[2];
        optionsPacket[19] = option61[3];
        optionsPacket[20] = option61[4];
        optionsPacket[21] = option61[5];
        optionsPacket[22] = option61[6];
        optionsPacket[23] = option61[7];
        optionsPacket[24] = option61[8];
        optionsPacket[25] = option61[9];
        optionsPacket[26] = option61[10];
        optionsPacket[27] = option61[11];
        optionsPacket[28] = option61[12];
        optionsPacket[29] = option61[13];
        optionsPacket[30] = option61[14];
        optionsPacket[31] = option61[15];
        optionsPacket[32] = option61[16];
        optionsPacket[33] = option61[17];
        //273
        
        len=optionsPacket.length;
        
        return optionsPacket;
    }

    public void setOptionsPacket(byte[] optionsPacket) {
        
        
        
        this.optionsPacket = optionsPacket;
    }

    public byte[] getOption53() {
        return option53;
    }

    public void setOption53(byte option, byte lenght, byte data) {
        
    /*
    option53[240] = (byte) 0x35; 
    option53[241] = (byte) 0x01;  
    option53[242] = (byte) 0x01;  
     */
    
    option53[0] = option; 
    option53[1] = lenght;  
    option53[2] = data;  
         
    //this.option53 = option53;
    
    }

    public byte[] getOption60() {
        return option60;
    }

    public void setOption60(byte option, byte lenght, String vendorClassId) {
        
     option60[0] = option; 
     option60[1] = lenght;

 //String vendorClassId = "docsis3.0";
    byte[] vendorClassIdBytes = vendorClassId.getBytes();
    System.arraycopy(vendorClassIdBytes, 0, option60, 3, vendorClassIdBytes.length);
    
    }

    public byte getOption0() {
        return option0;
    }

    public void setOption0(byte padding) {
        option0 = padding;
    }

    public byte[] getOption61() {
        return option61;
    }

    public void setOption61(byte option, byte lenght, String IAID, String DUIDTypeLink, String linkLayerAddress) {
   
        
   /*
        
   option61[256] = (byte) 0x3d; 
   option61[257] = (byte)0x0f;  
   option61[258] = (byte) 0xff;
   
   option61[259] = (byte) 0x07;
   option61[260] = (byte) 0x5c;
   option61[261] = (byte) 0x26;
   option61[262] = (byte) 0x40;

   option61[263] = (byte) 0x00;
   option61[264] = (byte) 0x03;

   option61[265] = (byte) 0x00;
   option61[266] = (byte) 0x01;


    String linkLayerAddress;  // Replace with your desired MAC address
         linkLayerAddress = "bc:3e:07:5c:26:40";
    String[] linkLayerAddressParts = linkLayerAddress.split(":");
    for (int i = 0; i < linkLayerAddressParts.length; i++) {
        int macPartlinkLayerAddress = Integer.parseInt(linkLayerAddressParts[i], 16);
        discoverPacket[267 + i] = (byte) macPartlinkLayerAddress;

    }
*/
   
   
   option61[0] = (byte) 0x3d; 
   option61[1] = (byte) 0x0f;  
   option61[2] = (byte) 0xff;
   
   option61[3] = (byte) 0x07;
   option61[4] = (byte) 0x5c;
   option61[5] = (byte) 0x26;
   option61[6] = (byte) 0x40;

   option61[7] = (byte) 0x00;
   option61[8] = (byte) 0x03;

   option61[8] = (byte) 0x00;
   option61[9] = (byte) 0x01;


    //String linkLayerAddress;  // Replace with your desired MAC address
        // linkLayerAddress = "bc:3e:07:5c:26:40";
    String[] linkLayerAddressParts = linkLayerAddress.split(":");
    for (int i = 0; i < linkLayerAddressParts.length; i++) {
        int macPartlinkLayerAddress = Integer.parseInt(linkLayerAddressParts[i], 16);
        option61[10 + i] = (byte) macPartlinkLayerAddress;

    }
   

    
   
  }

    public byte[] getOption125() {
        return option125;
    }

    public void setOption125(byte[] option125) {
        this.option125 = option125;
    }
    
    
     public int getLenOptionsPacket()
     {
         
         return len;
         
     }
    
    
}
