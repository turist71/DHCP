/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testdhcp2;
import java.net.*;
import java.util.Random;
/**
 *
 * @author root
 */
public class TestDHCP2 {

     private static final int DHCP_SERVER_PORT = 67;
    private static final int DHCP_CLIENT_PORT = 67;
    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        try {
            
            
            

         

            // Create a DHCP discover packet
            byte[] discoverPacket = createDiscoverPacket();

            //Origin IP
            InetAddress originIp = InetAddress.getByName("192.168.7.1"); // Replace this with your actual origin IP
             System.out.println("originIp "+originIp.getHostAddress());
               // Create a DatagramSocket for sending and receiving UDP packets
            DatagramSocket socket = new DatagramSocket(DHCP_CLIENT_PORT,originIp);
            
            // Set the destination address (DHCP server) and port
            InetAddress serverAddress = InetAddress.getByName("192.168.7.128");
            int serverPort = DHCP_SERVER_PORT;

            // Create a DatagramPacket with the DHCP discover packet and send it
            DatagramPacket packet = new DatagramPacket(discoverPacket, discoverPacket.length, serverAddress, serverPort);
            socket.send(packet);

            System.out.println("DHCP Discover packet sent to " + serverAddress.getHostAddress() + ":" + serverPort);

            // Close the socket
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
         try {
            // Create a DatagramSocket to listen for incoming UDP packets on port 68
            DatagramSocket socket = new DatagramSocket(DHCP_CLIENT_PORT);

            // Buffer to hold incoming data
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Receive the DHCP offer packet
            socket.receive(packet);

            // Process the received packet (extract information from the offer packet)
            processOfferPacket(packet.getData(), packet.getLength());

            // Close the socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
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

    private static byte[] createDiscoverPacket() {
        // TODO: Implement your DHCP discover packet creation logic here
        // Return the byte array representing the DHCP discover packet
        
        
    byte[] discoverPacket = new byte[604];  // Adjust the size as per your requirements

    // Set the opcode to 1 (BootRequest)
    discoverPacket[0] = 1;
    
    // Set hardware type to Ethernet (1)
    discoverPacket[1] = 1;

       // Set hardware address length
     discoverPacket[2] = 6;
        
         // Set hops
     discoverPacket[3] = 1;
     
     
  // Generate a random Transaction ID
    Random random = new Random();
    int transactionId = random.nextInt();  // Generate a random integer value
    discoverPacket[4] = (byte) (transactionId >> 24);
    discoverPacket[5] = (byte) (transactionId >> 16);
    discoverPacket[6] = (byte) (transactionId >> 8);
    discoverPacket[7] = (byte) transactionId;
     
    // TODO: Set other fields and options of the DHCP discover packet
    
     //seconds elapse    
          discoverPacket[8] =0;
         // Set the bootp flags (Broadcast, Unicast, Reserved)
        
          discoverPacket[9] =0;
          discoverPacket[10] =0;
         
          
          // Set the client IP address as 0.0.0.0
 
       
        discoverPacket[11] =(byte)0;
        discoverPacket[12] =(byte)0;
        discoverPacket[13] =(byte)0;
        discoverPacket[14] =(byte)0;
       
       
      
            
          // your  client IP address as 0.0.0.0
    
        
        discoverPacket[15] =(byte)0;
        discoverPacket[16] =(byte)0;
        discoverPacket[17] =(byte)0;
        discoverPacket[18] =(byte)0;
         
          // Set the next server IP address as 0.0.0.0    
           
        discoverPacket[19] =(byte)0;
        discoverPacket[20] =(byte)0;
        discoverPacket[21] =(byte)0;
        discoverPacket[22] =(byte)0;  
        
        
        //Delimiter 
        discoverPacket[23] =(byte)0;
        

// Set the relay agent IP address as 10.28.160.1
        
        discoverPacket[24] =(byte)192;
        discoverPacket[25] =(byte)168;
        discoverPacket[26] =(byte)7;
        discoverPacket[27] =(byte)1; 
           
           
 //set the client mac address
 
 // Set the client MAC address
    String macAddress = "00:ac:e0:45:6f:82";  // Replace with your desired MAC address
    String[] macParts = macAddress.split(":");
    for (int i = 0; i < macParts.length; i++) {
        int macPart = Integer.parseInt(macParts[i], 16);
        System.out.println("macPart "+macPart);
        discoverPacket[28 + i] = (byte) macPart;

    }
    
 //client hardware address padding
 
 for (int i = 0; i < 10; i++) {
        discoverPacket[34 + i] = (byte) 0x0;
    }
 
 
 //server host name not given
 
  for (int i = 0; i < 64; i++) {
        discoverPacket[44 + i] = (byte) 0x0;
    }
  
//bootfile not given

  for (int i = 0; i < 128; i++) {
        discoverPacket[108 + i] = (byte) 0x0;
    }

 //magic cookie: DHCP
 
 
 
 
  // Set the magic cookie
    discoverPacket[236] = (byte)0x63;  // 'D'
    discoverPacket[237] = (byte) 0x82;  // 'H'
    discoverPacket[238] = (byte)0x53;  // 'C'
    discoverPacket[239] = (byte) 0x63;  // 'P'
 
//OPTIONS

//OPtiON 53

    discoverPacket[240] = (byte) 0x35; 
    discoverPacket[241] = (byte) 0x01;  
    discoverPacket[242] = (byte) 0x01;  

//OPTION 60

discoverPacket[243] = (byte) 0x3c; 
discoverPacket[244] = (byte)0x0a;

 String vendorClassId = "docsis3.0";
    byte[] vendorClassIdBytes = vendorClassId.getBytes();
    System.arraycopy(vendorClassIdBytes, 0, discoverPacket, 245, vendorClassIdBytes.length);

//OPTION 0 , PADDING

discoverPacket[255] = (byte)0x0;

//OPTION 61 , client identifier

   discoverPacket[256] = (byte) 0x3d; 
   discoverPacket[257] = (byte)0x0f;  
   discoverPacket[258] = (byte) 0xff;
   
   discoverPacket[259] = (byte) 0x07;
    discoverPacket[260] = (byte) 0x5c;
    discoverPacket[261] = (byte) 0x26;
discoverPacket[262] = (byte) 0x40;

discoverPacket[263] = (byte) 0x00;
discoverPacket[264] = (byte) 0x03;

discoverPacket[265] = (byte) 0x00;
discoverPacket[266] = (byte) 0x01;


    String linkLayerAddress;  // Replace with your desired MAC address
         linkLayerAddress = "bc:3e:07:5c:26:40";
    String[] linkLayerAddressParts = linkLayerAddress.split(":");
    for (int i = 0; i < linkLayerAddressParts.length; i++) {
        int macPartlinkLayerAddress = Integer.parseInt(linkLayerAddressParts[i], 16);
        discoverPacket[267 + i] = (byte) macPartlinkLayerAddress;

    }

    
//opTION 125 VI Vendor specific information


discoverPacket[273]=(byte)0x7d;
discoverPacket[274]=(byte)0x9a;
discoverPacket[275]=(byte)0x00;
discoverPacket[276]=(byte)0x00;
discoverPacket[277]=(byte)0x11;
discoverPacket[278]=(byte)0x8b;
discoverPacket[279]=(byte)0x95;
discoverPacket[280]=(byte)0x01;
discoverPacket[281]=(byte)0x01;
discoverPacket[282]=(byte)0x02;
discoverPacket[283]=(byte)0x05;
discoverPacket[284]=(byte)0x90;
discoverPacket[285]=(byte)0x01;
discoverPacket[286]=(byte)0x01;
discoverPacket[287]=(byte)0x01;
discoverPacket[288]=(byte)0x02;
discoverPacket[289]=(byte)0x01;
discoverPacket[290]=(byte)0x03;
discoverPacket[291]=(byte)0x03;
discoverPacket[292]=(byte)0x01;
discoverPacket[293]=(byte)0x01;
discoverPacket[294]=(byte)0x04;
discoverPacket[295]=(byte)0x01;
discoverPacket[296]=(byte)0x01;
discoverPacket[297]=(byte)0x05;
discoverPacket[298]=(byte)0x01;
discoverPacket[299]=(byte)0x01;
discoverPacket[300]=(byte)0x06;
discoverPacket[301]=(byte)0x01;
discoverPacket[302]=(byte)0x01;
discoverPacket[303]=(byte)0x07;
discoverPacket[304]=(byte)0x01;
discoverPacket[305]=(byte)0x40;
discoverPacket[306]=(byte)0x08;
discoverPacket[307]=(byte)0x01;
discoverPacket[308]=(byte)0x08;
discoverPacket[309]=(byte)0x09;
discoverPacket[310]=(byte)0x01;
discoverPacket[311]=(byte)0x03;
discoverPacket[312]=(byte)0x0a;
discoverPacket[313]=(byte)0x01;
discoverPacket[314]=(byte)0x01;
discoverPacket[315]=(byte)0x0b;
discoverPacket[316]=(byte)0x01;
discoverPacket[317]=(byte)0x18;
discoverPacket[318]=(byte)0x0c;
discoverPacket[319]=(byte)0x01;
discoverPacket[320]=(byte)0x01;
discoverPacket[321]=(byte)0x0d;
discoverPacket[322]=(byte)0x02;
discoverPacket[323]=(byte)0x00;
discoverPacket[324]=(byte)0x40;
discoverPacket[325]=(byte)0x0e;
discoverPacket[326]=(byte)0x02;
discoverPacket[327]=(byte)0x00;
discoverPacket[328]=(byte)0x10;
discoverPacket[329]=(byte)0x0f;
discoverPacket[330]=(byte)0x01;
discoverPacket[331]=(byte)0x01;
discoverPacket[332]=(byte)0x10;
discoverPacket[333]=(byte)0x04;
discoverPacket[334]=(byte)0x00;
discoverPacket[335]=(byte)0x00;
discoverPacket[336]=(byte)0x00;
discoverPacket[337]=(byte)0x06;
discoverPacket[338]=(byte)0x11;
discoverPacket[339]=(byte)0x01;
discoverPacket[340]=(byte)0x01;
discoverPacket[341]=(byte)0x13;
discoverPacket[342]=(byte)0x01;
discoverPacket[343]=(byte)0x01;
discoverPacket[344]=(byte)0x14;
discoverPacket[345]=(byte)0x01;
discoverPacket[346]=(byte)0x01;
discoverPacket[347]=(byte)0x15;
discoverPacket[348]=(byte)0x01;
discoverPacket[349]=(byte)0x38;
discoverPacket[350]=(byte)0x16;
discoverPacket[351]=(byte)0x01;
discoverPacket[352]=(byte)0x01;
discoverPacket[353]=(byte)0x17;
discoverPacket[354]=(byte)0x01;
discoverPacket[355]=(byte)0x01;
discoverPacket[356]=(byte)0x18;
discoverPacket[357]=(byte)0x01;
discoverPacket[358]=(byte)0x08;
discoverPacket[359]=(byte)0x19;
discoverPacket[360]=(byte)0x01;
discoverPacket[361]=(byte)0x08;
discoverPacket[362]=(byte)0x1a;
discoverPacket[363]=(byte)0x01;
discoverPacket[364]=(byte)0x08;
discoverPacket[365]=(byte)0x1b;
discoverPacket[366]=(byte)0x01;
discoverPacket[367]=(byte)0x28;
discoverPacket[368]=(byte)0x1c;
discoverPacket[369]=(byte)0x01;
discoverPacket[370]=(byte)0x02;
discoverPacket[371]=(byte)0x1d;
discoverPacket[372]=(byte)0x01;
discoverPacket[373]=(byte)0x18;
discoverPacket[374]=(byte)0x1e;
discoverPacket[375]=(byte)0x01;
discoverPacket[376]=(byte)0x40;
discoverPacket[377]=(byte)0x1f;
discoverPacket[378]=(byte)0x01;
discoverPacket[379]=(byte)0x30;
discoverPacket[380]=(byte)0x20;
discoverPacket[381]=(byte)0x01;
discoverPacket[382]=(byte)0x3f;
discoverPacket[383]=(byte)0x21;
discoverPacket[384]=(byte)0x01;
discoverPacket[385]=(byte)0x02;
discoverPacket[386]=(byte)0x22;
discoverPacket[387]=(byte)0x01;
discoverPacket[388]=(byte)0x01;
discoverPacket[389]=(byte)0x23;
discoverPacket[390]=(byte)0x01;
discoverPacket[391]=(byte)0x01;
discoverPacket[392]=(byte)0x24;
discoverPacket[393]=(byte)0x01;
discoverPacket[394]=(byte)0x18;
discoverPacket[395]=(byte)0x25;
discoverPacket[396]=(byte)0x01;
discoverPacket[397]=(byte)0x01;
discoverPacket[398]=(byte)0x26;
discoverPacket[399]=(byte)0x02;
discoverPacket[400]=(byte)0x00;
discoverPacket[401]=(byte)0x40;
discoverPacket[402]=(byte)0x27;
discoverPacket[403]=(byte)0x01;
discoverPacket[404]=(byte)0x01;
discoverPacket[405]=(byte)0x28;
discoverPacket[406]=(byte)0x01;
discoverPacket[407]=(byte)0xd8;
discoverPacket[408]=(byte)0x2e;
discoverPacket[409]=(byte)0x01;
discoverPacket[410]=(byte)0x01;
discoverPacket[411]=(byte)0x12;
discoverPacket[412]=(byte)0x07;
discoverPacket[413]=(byte)0x10;
discoverPacket[414]=(byte)0xbc;
discoverPacket[415]=(byte)0x3e;
discoverPacket[416]=(byte)0x07;
discoverPacket[417]=(byte)0x5c;
discoverPacket[418]=(byte)0x26;
discoverPacket[419]=(byte)0x41;
discoverPacket[420]=(byte)0x12;
discoverPacket[421]=(byte)0x07;
discoverPacket[422]=(byte)0x01;
discoverPacket[423]=(byte)0xbc;
discoverPacket[424]=(byte)0x3e;
discoverPacket[425]=(byte)0x07;
discoverPacket[426]=(byte)0x5c;
discoverPacket[427]=(byte)0x26;
discoverPacket[428]=(byte)0x43;


//option 43 vendor specific information


discoverPacket[429]=(byte)0x2b;
discoverPacket[430]=(byte)0x87;
discoverPacket[431]=(byte)0x02;
discoverPacket[432]=(byte)0x03;
discoverPacket[433]=(byte)0x45;
discoverPacket[434]=(byte)0x43;
discoverPacket[435]=(byte)0x4d;
discoverPacket[436]=(byte)0x03;
discoverPacket[437]=(byte)0x10;
discoverPacket[438]=(byte)0x45;
discoverPacket[439]=(byte)0x43;
discoverPacket[440]=(byte)0x4d;
discoverPacket[441]=(byte)0x3a;
discoverPacket[442]=(byte)0x45;
discoverPacket[443]=(byte)0x4d;
discoverPacket[444]=(byte)0x54;
discoverPacket[445]=(byte)0x41;
discoverPacket[446]=(byte)0x3a;
discoverPacket[447]=(byte)0x45;
discoverPacket[448]=(byte)0x52;
discoverPacket[449]=(byte)0x4f;
discoverPacket[450]=(byte)0x55;
discoverPacket[451]=(byte)0x54;
discoverPacket[452]=(byte)0x45;
discoverPacket[453]=(byte)0x52;
discoverPacket[454]=(byte)0x04;
discoverPacket[455]=(byte)0x0c;
discoverPacket[456]=(byte)0x32;
discoverPacket[457]=(byte)0x35;
discoverPacket[458]=(byte)0x32;
discoverPacket[459]=(byte)0x31;
discoverPacket[460]=(byte)0x39;
discoverPacket[461]=(byte)0x32;
discoverPacket[462]=(byte)0x31;
discoverPacket[463]=(byte)0x33;
discoverPacket[464]=(byte)0x39;
discoverPacket[465]=(byte)0x35;
discoverPacket[466]=(byte)0x39;
discoverPacket[467]=(byte)0x35;
discoverPacket[468]=(byte)0x05;
discoverPacket[469]=(byte)0x02;
discoverPacket[470]=(byte)0x30;
discoverPacket[471]=(byte)0x41;
discoverPacket[472]=(byte)0x06;
discoverPacket[473]=(byte)0x12;
discoverPacket[474]=(byte)0x36;
discoverPacket[475]=(byte)0x2e;
discoverPacket[476]=(byte)0x31;
discoverPacket[477]=(byte)0x2e;
discoverPacket[478]=(byte)0x35;
discoverPacket[479]=(byte)0x2e;
discoverPacket[480]=(byte)0x30;
discoverPacket[481]=(byte)0x2e;
discoverPacket[482]=(byte)0x31;
discoverPacket[483]=(byte)0x62;
discoverPacket[484]=(byte)0x35;
discoverPacket[485]=(byte)0x5f;
discoverPacket[486]=(byte)0x32;
discoverPacket[487]=(byte)0x2d;
discoverPacket[488]=(byte)0x4d;
discoverPacket[489]=(byte)0x47;
discoverPacket[490]=(byte)0x43;
discoverPacket[491]=(byte)0x50;
discoverPacket[492]=(byte)0x07;
discoverPacket[493]=(byte)0x1b;
discoverPacket[494]=(byte)0x43;
discoverPacket[495]=(byte)0x47;
discoverPacket[496]=(byte)0x4e;
discoverPacket[497]=(byte)0x56;
discoverPacket[498]=(byte)0x35;
discoverPacket[499]=(byte)0x2d;
discoverPacket[500]=(byte)0x31;
discoverPacket[501]=(byte)0x2e;
discoverPacket[502]=(byte)0x37;
discoverPacket[503]=(byte)0x2e;
discoverPacket[504]=(byte)0x32;
discoverPacket[505]=(byte)0x2d;
discoverPacket[506]=(byte)0x67;
discoverPacket[507]=(byte)0x37;
discoverPacket[508]=(byte)0x34;
discoverPacket[509]=(byte)0x39;
discoverPacket[510]=(byte)0x32;
discoverPacket[511]=(byte)0x36;
discoverPacket[512]=(byte)0x31;
discoverPacket[513]=(byte)0x64;
discoverPacket[514]=(byte)0x33;
discoverPacket[515]=(byte)0x2d;
discoverPacket[516]=(byte)0x64;
discoverPacket[517]=(byte)0x69;
discoverPacket[518]=(byte)0x72;
discoverPacket[519]=(byte)0x74;
discoverPacket[520]=(byte)0x79;
discoverPacket[521]=(byte)0x08;
discoverPacket[522]=(byte)0x06;
discoverPacket[523]=(byte)0x30;
discoverPacket[524]=(byte)0x30;
discoverPacket[525]=(byte)0x30;
discoverPacket[526]=(byte)0x35;
discoverPacket[527]=(byte)0x43;
discoverPacket[528]=(byte)0x41;
discoverPacket[529]=(byte)0x09;
discoverPacket[530]=(byte)0x05;
discoverPacket[531]=(byte)0x43;
discoverPacket[532]=(byte)0x47;
discoverPacket[533]=(byte)0x4e;
discoverPacket[534]=(byte)0x56;
discoverPacket[535]=(byte)0x35;
discoverPacket[536]=(byte)0x0a;
discoverPacket[537]=(byte)0x13;
discoverPacket[538]=(byte)0x48;
discoverPacket[539]=(byte)0x69;
discoverPacket[540]=(byte)0x74;
discoverPacket[541]=(byte)0x72;
discoverPacket[542]=(byte)0x6f;
discoverPacket[543]=(byte)0x6e;
discoverPacket[544]=(byte)0x20;
discoverPacket[545]=(byte)0x54;
discoverPacket[546]=(byte)0x65;
discoverPacket[547]=(byte)0x63;
discoverPacket[548]=(byte)0x68;
discoverPacket[549]=(byte)0x6e;
discoverPacket[550]=(byte)0x6f;
discoverPacket[551]=(byte)0x6c;
discoverPacket[552]=(byte)0x6f;
discoverPacket[553]=(byte)0x67;
discoverPacket[554]=(byte)0x69;
discoverPacket[555]=(byte)0x65;
discoverPacket[556]=(byte)0x73;
discoverPacket[557]=(byte)0x0f;
discoverPacket[558]=(byte)0x07;
discoverPacket[559]=(byte)0x45;
discoverPacket[560]=(byte)0x52;
discoverPacket[561]=(byte)0x4f;
discoverPacket[562]=(byte)0x55;
discoverPacket[563]=(byte)0x54;
discoverPacket[564]=(byte)0x45;
discoverPacket[565]=(byte)0x52;

//OPTION 55


discoverPacket[566]=(byte)0x37;
discoverPacket[567]=(byte)0x0c;
discoverPacket[568]=(byte)0x01;
discoverPacket[569]=(byte)0x02;
discoverPacket[570]=(byte)0x03;
discoverPacket[571]=(byte)0x04;
discoverPacket[572]=(byte)0x06;
discoverPacket[573]=(byte)0x07;
discoverPacket[574]=(byte)0x36;
discoverPacket[575]=(byte)0x42;
discoverPacket[576]=(byte)0x43;
discoverPacket[577]=(byte)0x64;
discoverPacket[578]=(byte)0x7d;
discoverPacket[579]=(byte)0x7a;


//option 82

discoverPacket[580]=(byte)0x52;
discoverPacket[581]=(byte)0x15;
discoverPacket[582]=(byte)0x02;
discoverPacket[583]=(byte)0x06;
discoverPacket[584]=(byte)0xbc;
discoverPacket[585]=(byte)0x3e;
discoverPacket[586]=(byte)0x07;
discoverPacket[587]=(byte)0x5c;
discoverPacket[588]=(byte)0x26;
discoverPacket[589]=(byte)0x40;
discoverPacket[590]=(byte)0x09;
discoverPacket[591]=(byte)0x0b;
discoverPacket[592]=(byte)0x00;
discoverPacket[593]=(byte)0x00;
discoverPacket[594]=(byte)0x11;
discoverPacket[595]=(byte)0x8b;
discoverPacket[596]=(byte)0x06;
discoverPacket[597]=(byte)0x01;
discoverPacket[598]=(byte)0x04;
discoverPacket[599]=(byte)0x01;
discoverPacket[600]=(byte)0x02;
discoverPacket[601]=(byte)0x03;
discoverPacket[602]=(byte)0x00;


//END

discoverPacket[603]=(byte)0xff;



    
  /*      // Set the relay agent IP address as 10.28.160.1
            buffer.put((byte) 192);
            buffer.put((byte) 168);
            buffer.put((byte) 7);
            buffer.put((byte) 1);*/
            

    return discoverPacket;
        
        
       
    }
    
    
      private static int generateTransactionID() {
        // Generate a random transaction ID
        return (int) (Math.random() * Integer.MAX_VALUE);
    }
    
    
}
