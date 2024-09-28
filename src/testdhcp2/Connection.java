/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testdhcp2;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import testdhcp2.Protocol.DHCPDetails;
import testdhcp2.Protocol.DHCPOptions;
import testdhcp2.Protocol.DHCPProtocol;
import testdhcp2.Protocol.DiscoverPacket;
import testdhcp2.Protocol.OfferPacket;

/**
 *
 * @author root
 */



public class Connection {
    
private byte[] packet;    
private DHCPDetails DHCPDetailspacket;
private DHCPOptions DHCPOptions;
private InetAddress originIp;
private DatagramSocket socket;
private  InetAddress serverAddress;
private int serverPort;
private DatagramPacket datagramPacket;
private boolean isOfferPacket=false;

    private static final int DHCP_SERVER_PORT = 67;
    private static final int DHCP_CLIENT_PORT = 67;
    
     public Connection(OfferPacket DHCPProtocolPacket) throws Exception
    {
          originIp = InetAddress.getByName("192.168.7.1");
          System.out.println("originIp "+originIp.getHostAddress());
          socket = new DatagramSocket(DHCP_CLIENT_PORT,originIp);
          
          serverAddress = InetAddress.getByName("192.168.7.128");
          serverPort = DHCP_SERVER_PORT;
          isOfferPacket=true;
    }
    
    public Connection(DiscoverPacket DHCPProtocolPacket) throws Exception
    {
        
          originIp = InetAddress.getByName("192.168.7.1");
          System.out.println("originIp "+originIp.getHostAddress());
          socket = new DatagramSocket(DHCP_CLIENT_PORT,originIp);
          
          serverAddress = InetAddress.getByName("192.168.7.128");
          serverPort = DHCP_SERVER_PORT;
          
          this.DHCPDetailspacket = DHCPProtocolPacket.getDiscoverPacketDetails();
          int DHCPDetailspacketLen= DHCPDetailspacket.getLenDetailsPacket();
        
        //  this.DHCPDetailspacket.getDetailsPacket();
         this.DHCPOptions=DHCPProtocolPacket.getDiscoverPacketOptions();
         int DHCPOptionsLen=DHCPOptions.getLenOptionsPacket();
          
          
          this.packet= DHCPProtocolPacket.getDiscoverpacketBytes();
          
          
                  

        
        
    }
    
    
    
    public void open()
    {
        
        if (!isOfferPacket){
         datagramPacket = new DatagramPacket(packet, packet.length, serverAddress, serverPort);
        }
        else{
             // Buffer to hold incoming data
            byte[] buffer = new byte[1024];
            datagramPacket  = new DatagramPacket(buffer, buffer.length);
    }
    }
    
    public void close()
    {
        socket.close();
        
    }
    
    
     public boolean  send()
    {
    try {
        socket.send(datagramPacket);
        return true;
    } catch (IOException ex) {
      
        Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
        
    }

    byte[] receive() throws IOException {
        // Receive the DHCP offer packet
            socket.receive(datagramPacket);
            return datagramPacket.getData();
    }
    
    
    
    
    private static void processOfferPacket(byte[] data, int length) {
        // TODO: Implement your logic to extract and process information from the offer packet
        // You can parse the data byte array and extract relevant fields based on the DHCP protocol
        // For example, you can access the DHCP message type (Option 53) to check if it's an offer packet.
        // Other options like offered IP address (Option 50), server identifier (Option 54), etc., can also be extracted.

        // For illustration purposes, let's print the received data as a hex string
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            hexString.append(String.format("%02X ", data[i]));
        }
        System.out.println("Received Offer Packet: " + hexString.toString());
    
        
        
        
        
        
    }
    
    
    
    
    
}
