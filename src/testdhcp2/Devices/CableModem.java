/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testdhcp2.Devices;

import testdhcp2.Protocol.DHCPDetails;
import testdhcp2.Protocol.DHCPOptions;
import testdhcp2.Protocol.DHCPProtocol;

/**
 *
 * @author root
 */
public class CableModem {


    
    private DHCPDetails dhcpDetails;
    private final   DHCPOptions options;
    byte[] optionsAray;
    byte[] detailsArray;
    byte[] discoverPacket;
    
    public CableModem ()
    {
        

   
/*
             (byte bootRequest, 
              byte hwdType, 
              byte hwdAaddrsLength, 
              byte hops, 
              byte txID, 
              byte secondsElapsed, 
              String bootTPFlags, 
              byte clientiPAddress, 
              byte yourClientiPAddress, 
              byte nxtServerIPAddress, 
              byte delimiter, 
              byte relayAgentIPaddress, 
              String clientMacAddress, 
              byte clientHWDDAddressMapping, 
              byte severHostname, 
              byte bootfile, 
              byte magicCookie, 
              DHCPOptions options)
        
        
        */        
        dhcpDetails = new DHCPDetails(
             (byte)1,  //byte bootRequest, 
              (byte)1,//byte hwdType, 
             (byte)6,// byte hwdAaddrsLength, 
             (byte)1,//byte hops, 
              //byte txID, 
             (byte)0, //byte secondsElapsed, 
              "00", //String bootTPFlags, 
              "0.0.0.0", //byte clientiPAddress, 
              "0.0.0.0",//byte yourClientiPAddress, 
              "0.0.0.0",//byte nxtServerIPAddress, 
             (byte)0,//byte delimiter, 
             "192.168.0.1" ,// relayAgentIPaddress, 
              "00:ac:e0:45:6f:82",//String clientMacAddress, 
              (byte)0,//byte clientHWDDAddressMapping, 
              (byte)0,//byte severHostname, 
              (byte)0,//byte bootfile, 
              "DHCP"//byte magicCookie, 
            );
       
        
         options = new DHCPOptions();
        
        
        /*
        option53[240] = (byte) 0x35; 
        option53[241] = (byte) 0x01;  
        option53[242] = (byte) 0x01;  
        */
        options.setOption53((byte)0x35,(byte)0x01,(byte)0x01);
        
        /*
        discoverPacket[243] = (byte) 0x3c; 
        discoverPacket[244] = (byte)0x0a;
        String vendorClassId = "docsis3.0";
        */
        
        options.setOption60((byte)0x3c, (byte)0x0a , "docsis3.0");
        
        /*
        discoverPacket[255] = (byte)0x0;
        */
        
        options.setOption0((byte)0x0);
        
        /*
        
        */
        
        options.setOption61((byte)0x0, (byte)0x0, "IAID", "DUIDTypeLink", "bc:3e:07:5c:26:40");
        
        
      
     
     
     
     
     
     

        
        
        
    }
    
    
    
    
    
    

    public void setOfferOptions(DHCPProtocol offerPacket) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setACKOptions(DHCPProtocol ACKPacket) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
        public DHCPDetails getDhcpDetails() {
        return dhcpDetails;
    }
        
        
    public DHCPOptions getDHCPOptions()
    {
        
        return options;
        
    }    

    public void setDhcpDetails(DHCPDetails dhcpDetails) {
        this.dhcpDetails = dhcpDetails;
    }
    
}
