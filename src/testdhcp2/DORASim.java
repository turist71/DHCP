/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testdhcp2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import testdhcp2.Devices.*;
import testdhcp2.Protocol.*;

/**
 *
 * @author root
 */
public class DORASim {
    
    
    public static void main (String args[])
    {
        
        CableModem cm = new CableModem();
        
        DiscoverPacket discoverPacket = new DiscoverPacket(cm);
        
        Connection connectionDiscover = null;
        try {
            connectionDiscover = new Connection(discoverPacket);
        } catch (Exception ex) {
            Logger.getLogger(DORASim.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        connectionDiscover.open();
        boolean statusDiscover;
        statusDiscover = connectionDiscover.send();
        connectionDiscover.close();
        
        
        if (statusDiscover)
        {
            
            OfferPacket offerPacket = new OfferPacket();
            Connection connectionOffer = null;
            
            try { 
                 connectionOffer = new Connection(offerPacket);
            } catch (Exception ex) {
                Logger.getLogger(DORASim.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            connectionOffer.open();
            byte[] offerPacketByteArray = null;
            try {
                offerPacketByteArray = connectionOffer.receive();
            } catch (IOException ex) {
                Logger.getLogger(DORASim.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            offerPacket.setOfferBytesPacket(offerPacketByteArray);
            offerPacket.decodeDhcpOfferPacket();

            
            //display offer
             StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < offerPacketByteArray.length; i++) {
            hexString.append(String.format("%02X ", offerPacketByteArray[i]));
        }
       // System.out.println("Received Offer Packet: " + hexString.toString());
            
            
            
        }
        
     /* 
        if (statusDiscover)
        {
          
            Connection connectionOffer = new Connection();
            connectionOffer.open();
            DHCPProtocol offerPacket = connectionOffer.receive();
            if (offerPacket != null )
            {
                 cm.setOfferOptions(offerPacket);
                
            } else {                
               
            }
            
            connectionOffer.close();
            
            
        } //end    if (status)
            
       /* 
       DHCPProtocol requestPacket = new RequestPacket(); 
       Connection connectionRequest = new Connection();
       connectionRequest.open();
       boolean statusRequest;
       statusRequest = connectionRequest.send(requestPacket);
       
       if (statusRequest)
        {
            
          Connection connectionACK = new Connection();  
          connectionACK.open();
          DHCPProtocol ACKPacket = connectionACK.receive();
          
          if (ACKPacket != null)
         {
                 cm.setACKOptions(ACKPacket);
                
            } else {                
               
            }
          
          connectionACK.close();
            
        } //end    if (status)
       
       
        
    */    
    }
    
    
}
