/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testdhcp2.Protocol;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author root
 */
public class OfferPacket extends DHCPProtocol {
    
    private InetAddress clientIpAddress;
    private InetAddress nextServerIpAddress;
    private InetAddress relayAgentIpAddress;
    private byte[] data;
    
     public OfferPacket() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     
   public void setOfferBytesPacket(byte data[])
   {
       this.data=data;
       
   }
   
    private static String byteArrayToIpAddress(byte[] array) {
        try {
            return (array[0] & 0xFF) + "." + (array[1] & 0xFF) + "." + (array[2] & 0xFF) + "." + (array[3] & 0xFF);
        } catch (Exception e) {
            return "Invalid IP address";
        }
    }
   
   public void decodeDhcpOfferPacket()
   {
       
         // Example DHCP offer packet data (replace with actual packet data)
       // byte[] dhcpOfferPacket = { /* Your DHCP offer packet data here */ };

        // Decode the DHCP offer packet
        Map<Integer, byte[]> options = decodeDhcpOptions(data);
        System.out.println("Decoded DHCP Offer Options:");
        // Decode Option 1 (Subnet Mask)
        if (options.containsKey(1)) {
            byte[] subnetMask = options.get(1);
            System.out.println("Option 1 (Subnet Mask): " + byteArrayToIpAddress(subnetMask));
        }
        // Decode Option 53 (DHCP Message Type)
        if (options.containsKey(53)) {
            byte[] dhcpMessageType = options.get(53);
            int messageType = dhcpMessageType[0] & 0xFF; // Extract the message type value
            System.out.println("Option 53 (DHCP Message Type): " + messageType);
        }
        // Decode Option 3 (Router)
        if (options.containsKey(3)) {
            byte[] router = options.get(3);
            System.out.println("Option 3 (Router): " + byteArrayToIpAddress(router));
        }
        
          // Decode Option 54
        if (options.containsKey(54)) {
            byte[] DHCPId = options.get(54);
            System.out.println("Option 54 (DHCp server Identifier): " + byteArrayToIpAddress(DHCPId));
        }
        
          // Decode Option 51 (Lease Time)
        // Decode Option 51 (IP Address Lease Time)
        if (options.containsKey(51)) {
            byte[] leaseTime = options.get(51);
            int leaseSeconds = ((leaseTime[0] & 0xFF) << 24) | ((leaseTime[1] & 0xFF) << 16) | ((leaseTime[2] & 0xFF) << 8) | (leaseTime[3] & 0xFF);
            System.out.println("Option 51 (IP Address Lease Time): " + leaseSeconds + " seconds");
        }
        
               // Decode Option 59 (Lease Time)
        // Decode Option 59 (Rebinding Time value)
        if (options.containsKey(59)) {
            byte[] leaseTime = options.get(59);
            int leaseSeconds = ((leaseTime[0] & 0xFF) << 24) | ((leaseTime[1] & 0xFF) << 16) | ((leaseTime[2] & 0xFF) << 8) | (leaseTime[3] & 0xFF);
            System.out.println("Option 59 (Rebinding Time value)): " + leaseSeconds + " seconds");
        }
        
                     // Decode Option 58 (Lease Time)
        // Decode Option 58 (Renewal Time value)
        if (options.containsKey(58)) {
            byte[] leaseTime = options.get(58);
            int leaseSeconds = ((leaseTime[0] & 0xFF) << 24) | ((leaseTime[1] & 0xFF) << 16) | ((leaseTime[2] & 0xFF) << 8) | (leaseTime[3] & 0xFF);
            System.out.println("Option 58 (Renewal Time value)): " + leaseSeconds + " seconds");
        }
        
           // Decode Option 82 (Relay Agent Information)
        if (options.containsKey(82)) {
            byte[] relayAgentInfo = options.get(82);
            // Parse and decode the relay agent information here
            System.out.println("Option 82 (Relay Agent Information): " + byteArrayToHexString(relayAgentInfo));
        } else {
            System.out.println("Option 82 (Relay Agent Information) not present in the DHCP offer packet.");
        }
        
         // Check if it's the end option (Option 255)
        if (options.containsKey(255)) {
              System.out.println("Option 255 (END): ff");
        }
        
        
        //Extract other options
        
         // Extract the Transaction ID from the DHCP offer packet
        String transactionID = extractTransactionID(data);
        System.out.println("Transaction ID: " + transactionID);
        
        // Extract DHCP header fields from the DHCP offer packet
        int messageType = extractMessageType(data);
        int hardwareType = extractHardwareType(data);
        int hardwareAddressLength = extractHardwareAddressLength(data);

   
       

        // Print the extracted DHCP header fields
        System.out.println("Message Type: " + messageType);
        System.out.println("Hardware Type: " + hardwareType);
        System.out.println("Hardware Address Length: " + hardwareAddressLength);
        
        int hops = extractHops(data);
        int secondsElapsed = extractSecondsElapsed(data);

        // Print the extracted DHCP header fields
        System.out.println("Hops: " + hops);
        System.out.println("Seconds Elapsed: " + secondsElapsed);
       
        // Extract DHCP header fields from the DHCP offer packet
        int bootpFlags = extractBootpFlags(data);

        // Print the extracted DHCP header field
        System.out.println("Bootp Flags: " + bootpFlags);
        
        // Extract DHCP header fields from the DHCP offer packet
        String clientIpAddress = extractClientIpAddress(data);

        // Print the extracted DHCP header field
        System.out.println("Client IP Address: " + clientIpAddress);
        
             // Extract DHCP header fields from the DHCP offer packet
        String relayAgentIpAddress = extractRelayAgentIpAddress(data);

        // Print the extracted DHCP header field
        System.out.println("Relay Agent IP Address: " + relayAgentIpAddress);
        
           // Extract DHCP header fields from the DHCP offer packet
        String clientMacAddress = extractClientMacAddress(data);

        // Print the extracted DHCP header field
        System.out.println("Client MAC Address: " + clientMacAddress);
        
         // Extract server hostname from the DHCP offer packet
        String serverHostname = extractServerHostname(data);

        // Print the extracted server hostname
        System.out.println("Server Hostname: " + serverHostname);
     
       
   }
   
   private static String extractServerHostname(byte[] data) {
        Map<Integer, byte[]> options = decodeDhcpOptions(data);
        if (options.containsKey(12)) { // Option code for Server Hostname
            byte[] hostnameBytes = options.get(12);
            return new String(hostnameBytes);
        } else {
            return "Server Hostname not found in DHCP offer packet";
        }
    }

    
   
   private static String extractClientMacAddress(byte[] data) {
        // Client MAC Address field is located at bytes 28-33 in the DHCP header
        StringBuilder macAddress = new StringBuilder();
        for (int i = 28; i < 34; i++) {
            macAddress.append(String.format("%02X", data[i]));
            if (i < 33) {
                macAddress.append(":");
            }
        }
        return macAddress.toString();
    }
   
   private static String extractRelayAgentIpAddress(byte[] data) {
        // Relay Agent IP Address field is located at bytes 24-27 in the DHCP header
        StringBuilder ipAddress = new StringBuilder();
        for (int i = 24; i < 28; i++) {
            ipAddress.append(data[i] & 0xFF);
            if (i < 27) {
                ipAddress.append(".");
            }
        }
        return ipAddress.toString();
    }
   
   private static String extractClientIpAddress(byte[] data) {
        // Client IP Address field is located at bytes 16-19 in the DHCP header
        StringBuilder ipAddress = new StringBuilder();
        for (int i = 16; i < 20; i++) {
            ipAddress.append(data[i] & 0xFF);
            if (i < 19) {
                ipAddress.append(".");
            }
        }
        return ipAddress.toString();
    }
   
       private static int extractHops(byte[] data) {
        // Hops field is located at byte 3 in the DHCP header
        return data[3] & 0xFF;
    }
       
           private static int extractBootpFlags(byte[] data) {
        // Bootp Flags field is located at bytes 10-11 in the DHCP header
        return ((data[10] & 0xFF) << 8) | (data[11] & 0xFF);
    }

    private static int extractSecondsElapsed(byte[] data) {
        // Seconds Elapsed field is located at bytes 8-9 in the DHCP header
        return ((data[8] & 0xFF) << 8) | (data[9] & 0xFF);
    }
   
    private static int extractMessageType(byte[] data) {
        // Message Type field is located at byte 242 in the DHCP header
        return data[242] & 0xFF;
    }

    private static int extractHardwareType(byte[] data) {
        // Hardware Type field is located at byte 240 in the DHCP header
        return data[240] & 0xFF;
    }

    private static int extractHardwareAddressLength(byte[] data) {
        // Hardware Address Length field is located at byte 241 in the DHCP header
        return data[241] & 0xFF;
    }
   
   
   
  private static String extractTransactionID(byte[] data) {
        // Transaction ID is located at bytes 4-7 in the DHCP header
        byte[] transactionIDBytes = new byte[4];
        System.arraycopy(data, 4, transactionIDBytes, 0, 4);
        // Convert bytes to hexadecimal string
        StringBuilder transactionIDHex = new StringBuilder();
        for (byte b : transactionIDBytes) {
            // Convert each byte to a 2-digit hexadecimal string representation
            String hexByte = Integer.toHexString(b & 0xFF);
            if (hexByte.length() == 1) {
                // Pad with leading zero if necessary
                transactionIDHex.append("0");
            }
            transactionIDHex.append(hexByte);
        }
        return transactionIDHex.toString();
    }
   
   // Helper method to convert byte array to hex string
    private static String byteArrayToHexString(byte[] array) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : array) {
            hexString.append(String.format("%02X ", b));
        }
        return hexString.toString();
    }
   
  
   
   private static Map<Integer, byte[]> decodeDhcpOptions(byte[] data) {
        Map<Integer, byte[]> options = new HashMap<>();

        // Skip the DHCP header and go to the beginning of options
        int optionsIndex = 240;

        // Iterate through the packet bytes and decode DHCP options
        while (optionsIndex < data.length - 1) {
            int optionCode = data[optionsIndex] & 0xFF;
            int optionLength = data[optionsIndex + 1] & 0xFF;
            byte[] optionValue = new byte[optionLength];
            System.arraycopy(data, optionsIndex + 2, optionValue, 0, optionLength);
            options.put(optionCode, optionValue);
            optionsIndex += 2 + optionLength;
        }

        return options;
    }


    
  
    
}
