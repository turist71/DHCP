/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testdhcp2.Protocol;

import testdhcp2.Devices.CableModem;

/**
 *
 * @author root
 */
public class DiscoverPacket extends DHCPProtocol {
    
    private final CableModem cm;
    
    public DiscoverPacket (CableModem cm)
    {
        this.cm=cm;
        
    }
    
  public DHCPDetails getDiscoverPacketDetails()
  {
      
      return cm.getDhcpDetails();
      
  }
  
  
  public DHCPOptions getDiscoverPacketOptions()
  {
      
      return cm.getDHCPOptions();
  }
  
  
  public byte[] getDiscoverpacketBytes()
  {
      
      DHCPDetails details = cm.getDhcpDetails();
     
      
      DHCPOptions options = cm.getDHCPOptions();
     
      
      byte[] optionsArray = options.getOptionsPacket();
      byte[] detailsArray = details.getDetailsPacket();
       int optionsLen = options.getLenOptionsPacket();
       int detailsLen= details.getLenDetailsPacket();
      
      byte[] discoverPacketArray = new byte[detailsLen+optionsLen];
      
      int leng= detailsLen + optionsLen;
      
      System.out.println("detailsLen+optionsLen " +optionsLen);
    
      
      for (int i=0; i<detailsArray.length; i++)
      {
          discoverPacketArray[i]=detailsArray[i];
        System.out.println("discoverPacketArray ["+i+"]" +discoverPacketArray[i]);
          
      }
      
    
      
      int cont=0;
      
      for (int j=detailsArray.length; j< optionsArray.length+detailsArray.length; j++)
      {
          discoverPacketArray[j]=optionsArray[cont];
          cont++;
          System.out.println("discoverPacketArray ["+j+"]"+ discoverPacketArray[j]);
          
      }
      
      
      return discoverPacketArray;
      
  }
  
  /*
  
    optionsAray=options.getOptionsPacket();
        detailsArray=dhcpDetails.getDetailsPacket();
        
        
     optionsAray = new byte[options.getLenOptionsPacket()];
     detailsArray = new byte[dhcpDetails.getLenDetailsPacket()];
     discoverPacket = new byte[optionsAray.length+detailsArray.length];
        
  
  */
    
    
}
