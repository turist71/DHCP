/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testdhcp2.Protocol;

import java.util.Random;

/**
 *
 * @author root
 */
public class DHCPDetails {

    

  
    byte[] detailsPacket;
    private byte bootRequest;
    private byte hwdType;
    private byte hwdAaddrsLength;
    private byte hops;
    private int txID;
    private byte secondsElapsed;
    private String bootTPFlags;
    private String clientiPAddress;
    private String yourClientiPAddress;
    private String nxtServerIPAddress;
    private byte delimiter;
    private String relayAgentIPaddress;
    private String clientMacAddress;
    private byte clientHWDDAddressMapping;
    private byte severHostname;
    private byte bootfile;
    private String magicCookie;
    private DHCPOptions options;
    private int len;
    
    public DHCPDetails ()
    {
        this.detailsPacket = new byte[604];
        this.len=0;
        
    }

      public DHCPDetails(
              byte bootRequest, 
              byte hwdType, 
              byte hwdAaddrsLength, 
              byte hops, 
              //byte txID, 
              byte secondsElapsed, 
              String bootTPFlags, 
              String clientiPAddress, 
              String yourClientiPAddress, 
              String nxtServerIPAddress, 
              byte delimiter, 
              String relayAgentIPaddress, 
              String clientMacAddress, 
              byte clientHWDDAddressMapping, 
              byte severHostname, 
              byte bootfile, 
              String magicCookie 
              ) {
        
        this.detailsPacket = new byte[604];  
          
        
       
       
       // this.clientHWDDAddressMapping = clientHWDDAddressMapping;
        //this.severHostname = severHostname;
        //this.bootfile = bootfile;
        //this.magicCookie = magicCookie;
        //this.options = options;
        
        
        
        this.bootRequest = bootRequest;
        detailsPacket[0]=bootRequest;
        
        this.hwdType = hwdType;
        detailsPacket[1]=hwdType;
        
        this.hwdType = hwdType;
        detailsPacket[2]=hwdAaddrsLength;
        
        this.hops = hops;
        detailsPacket[3]=hops;
        
        //this.txID = txID;
        this.generateTxID();
        
        
        this.secondsElapsed = secondsElapsed;
        detailsPacket[8]=secondsElapsed;
        
        
        // Set the bootp flags (Broadcast, Unicast, Reserved)
        this.bootTPFlags = bootTPFlags;
        detailsPacket[9]=(byte)bootTPFlags.charAt(0);
        detailsPacket[10]=(byte)bootTPFlags.charAt(0);
        
         // Set the client IP address as 0.0.0.0
 
      
        detailsPacket[11] =(byte)0;
        detailsPacket[12] =(byte)0;
        detailsPacket[13] =(byte)0;
        detailsPacket[14] =(byte)0;
        this.clientiPAddress = detailsPacket[11]+"."+detailsPacket[12]+"."+detailsPacket[13]+"."+detailsPacket[14];        
        
         // your  client IP address as 0.0.0.0
    
        
        detailsPacket[15] =(byte)0;
        detailsPacket[16] =(byte)0;
        detailsPacket[17] =(byte)0;
        detailsPacket[18] =(byte)0;
       this.yourClientiPAddress = detailsPacket[15]+"."+detailsPacket[16]+"."+detailsPacket[17]+"."+detailsPacket[18];

         
          // Set the next server IP address as 0.0.0.0    
           
        detailsPacket[19] =(byte)0;
        detailsPacket[20] =(byte)0;
        detailsPacket[21] =(byte)0;
        detailsPacket[22] =(byte)0;  
        this.nxtServerIPAddress = detailsPacket[19]+"."+detailsPacket[20]+"."+detailsPacket[21]+"."+detailsPacket[22];

        
        //Delimiter 
        detailsPacket[23] =delimiter;
        this.delimiter = delimiter;
       
        
        // Set the relay agent IP address as 10.28.160.1
        
        detailsPacket[24] =(byte)192;
        detailsPacket[25] =(byte)168;
        detailsPacket[26] =(byte)7;
        detailsPacket[27] =(byte)1; 
       this.relayAgentIPaddress = detailsPacket[24]+"."+detailsPacket[25]+"."+detailsPacket[26]+"."+detailsPacket[27];

        
        
        //String macAddress = "00:ac:e0:45:6f:82";
        this.clientMacAddress = clientMacAddress;
        this.setClientMacAddress(clientMacAddress);
           
        //client hardware address padding
 
 for (int i = 0; i < 10; i++) {
        detailsPacket[34 + i] = (byte) 0x0;
    }
 
 
 //server host name not given
 
  for (int i = 0; i < 64; i++) {
        detailsPacket[44 + i] = (byte) 0x0;
    }
  
//bootfile not given

  for (int i = 0; i < 128; i++) {
        detailsPacket[108 + i] = (byte) 0x0;
    }

 //magic cookie: DHCP
 
 
 
 
  // Set the magic cookie
    detailsPacket[236] = (byte)0x63;  // 'D'
    detailsPacket[237] = (byte) 0x82;  // 'H'
    detailsPacket[238] = (byte)0x53;  // 'C'
    detailsPacket[239] = (byte) 0x63;  // 'P'
    this.magicCookie = detailsPacket[236]+" "+detailsPacket[237]+" "+detailsPacket[238]+" "+detailsPacket[239];
    
    
 
    len=detailsPacket.length;
    
//OPTIONS

//OPtiON 53

    detailsPacket[240] = (byte) 0x35; 
    detailsPacket[241] = (byte) 0x01;  
    detailsPacket[242] = (byte) 0x01;  

//OPTION 60

detailsPacket[243] = (byte) 0x3c; 
detailsPacket[244] = (byte)0x0a;

 String vendorClassId = "docsis3.0";
    byte[] vendorClassIdBytes = vendorClassId.getBytes();
    System.arraycopy(vendorClassIdBytes, 0, detailsPacket, 245, vendorClassIdBytes.length);

//OPTION 0 , PADDING

detailsPacket[255] = (byte)0x0;

//OPTION 61 , client identifier

   detailsPacket[256] = (byte) 0x3d; 
   detailsPacket[257] = (byte)0x0f;  
   detailsPacket[258] = (byte) 0xff;
   
   detailsPacket[259] = (byte) 0x07;
    detailsPacket[260] = (byte) 0x5c;
    detailsPacket[261] = (byte) 0x26;
detailsPacket[262] = (byte) 0x40;

detailsPacket[263] = (byte) 0x00;
detailsPacket[264] = (byte) 0x03;

detailsPacket[265] = (byte) 0x00;
detailsPacket[266] = (byte) 0x01;


    String linkLayerAddress;  // Replace with your desired MAC address
         linkLayerAddress = "bc:3e:07:5c:26:40";
    String[] linkLayerAddressParts = linkLayerAddress.split(":");
    for (int i = 0; i < linkLayerAddressParts.length; i++) {
        int macPartlinkLayerAddress = Integer.parseInt(linkLayerAddressParts[i], 16);
        detailsPacket[267 + i] = (byte) macPartlinkLayerAddress;

    }

    
//opTION 125 VI Vendor specific information


detailsPacket[273]=(byte)0x7d;
detailsPacket[274]=(byte)0x9a;
detailsPacket[275]=(byte)0x00;
detailsPacket[276]=(byte)0x00;
detailsPacket[277]=(byte)0x11;
detailsPacket[278]=(byte)0x8b;
detailsPacket[279]=(byte)0x95;
detailsPacket[280]=(byte)0x01;
detailsPacket[281]=(byte)0x01;
detailsPacket[282]=(byte)0x02;
detailsPacket[283]=(byte)0x05;
detailsPacket[284]=(byte)0x90;
detailsPacket[285]=(byte)0x01;
detailsPacket[286]=(byte)0x01;
detailsPacket[287]=(byte)0x01;
detailsPacket[288]=(byte)0x02;
detailsPacket[289]=(byte)0x01;
detailsPacket[290]=(byte)0x03;
detailsPacket[291]=(byte)0x03;
detailsPacket[292]=(byte)0x01;
detailsPacket[293]=(byte)0x01;
detailsPacket[294]=(byte)0x04;
detailsPacket[295]=(byte)0x01;
detailsPacket[296]=(byte)0x01;
detailsPacket[297]=(byte)0x05;
detailsPacket[298]=(byte)0x01;
detailsPacket[299]=(byte)0x01;
detailsPacket[300]=(byte)0x06;
detailsPacket[301]=(byte)0x01;
detailsPacket[302]=(byte)0x01;
detailsPacket[303]=(byte)0x07;
detailsPacket[304]=(byte)0x01;
detailsPacket[305]=(byte)0x40;
detailsPacket[306]=(byte)0x08;
detailsPacket[307]=(byte)0x01;
detailsPacket[308]=(byte)0x08;
detailsPacket[309]=(byte)0x09;
detailsPacket[310]=(byte)0x01;
detailsPacket[311]=(byte)0x03;
detailsPacket[312]=(byte)0x0a;
detailsPacket[313]=(byte)0x01;
detailsPacket[314]=(byte)0x01;
detailsPacket[315]=(byte)0x0b;
detailsPacket[316]=(byte)0x01;
detailsPacket[317]=(byte)0x18;
detailsPacket[318]=(byte)0x0c;
detailsPacket[319]=(byte)0x01;
detailsPacket[320]=(byte)0x01;
detailsPacket[321]=(byte)0x0d;
detailsPacket[322]=(byte)0x02;
detailsPacket[323]=(byte)0x00;
detailsPacket[324]=(byte)0x40;
detailsPacket[325]=(byte)0x0e;
detailsPacket[326]=(byte)0x02;
detailsPacket[327]=(byte)0x00;
detailsPacket[328]=(byte)0x10;
detailsPacket[329]=(byte)0x0f;
detailsPacket[330]=(byte)0x01;
detailsPacket[331]=(byte)0x01;
detailsPacket[332]=(byte)0x10;
detailsPacket[333]=(byte)0x04;
detailsPacket[334]=(byte)0x00;
detailsPacket[335]=(byte)0x00;
detailsPacket[336]=(byte)0x00;
detailsPacket[337]=(byte)0x06;
detailsPacket[338]=(byte)0x11;
detailsPacket[339]=(byte)0x01;
detailsPacket[340]=(byte)0x01;
detailsPacket[341]=(byte)0x13;
detailsPacket[342]=(byte)0x01;
detailsPacket[343]=(byte)0x01;
detailsPacket[344]=(byte)0x14;
detailsPacket[345]=(byte)0x01;
detailsPacket[346]=(byte)0x01;
detailsPacket[347]=(byte)0x15;
detailsPacket[348]=(byte)0x01;
detailsPacket[349]=(byte)0x38;
detailsPacket[350]=(byte)0x16;
detailsPacket[351]=(byte)0x01;
detailsPacket[352]=(byte)0x01;
detailsPacket[353]=(byte)0x17;
detailsPacket[354]=(byte)0x01;
detailsPacket[355]=(byte)0x01;
detailsPacket[356]=(byte)0x18;
detailsPacket[357]=(byte)0x01;
detailsPacket[358]=(byte)0x08;
detailsPacket[359]=(byte)0x19;
detailsPacket[360]=(byte)0x01;
detailsPacket[361]=(byte)0x08;
detailsPacket[362]=(byte)0x1a;
detailsPacket[363]=(byte)0x01;
detailsPacket[364]=(byte)0x08;
detailsPacket[365]=(byte)0x1b;
detailsPacket[366]=(byte)0x01;
detailsPacket[367]=(byte)0x28;
detailsPacket[368]=(byte)0x1c;
detailsPacket[369]=(byte)0x01;
detailsPacket[370]=(byte)0x02;
detailsPacket[371]=(byte)0x1d;
detailsPacket[372]=(byte)0x01;
detailsPacket[373]=(byte)0x18;
detailsPacket[374]=(byte)0x1e;
detailsPacket[375]=(byte)0x01;
detailsPacket[376]=(byte)0x40;
detailsPacket[377]=(byte)0x1f;
detailsPacket[378]=(byte)0x01;
detailsPacket[379]=(byte)0x30;
detailsPacket[380]=(byte)0x20;
detailsPacket[381]=(byte)0x01;
detailsPacket[382]=(byte)0x3f;
detailsPacket[383]=(byte)0x21;
detailsPacket[384]=(byte)0x01;
detailsPacket[385]=(byte)0x02;
detailsPacket[386]=(byte)0x22;
detailsPacket[387]=(byte)0x01;
detailsPacket[388]=(byte)0x01;
detailsPacket[389]=(byte)0x23;
detailsPacket[390]=(byte)0x01;
detailsPacket[391]=(byte)0x01;
detailsPacket[392]=(byte)0x24;
detailsPacket[393]=(byte)0x01;
detailsPacket[394]=(byte)0x18;
detailsPacket[395]=(byte)0x25;
detailsPacket[396]=(byte)0x01;
detailsPacket[397]=(byte)0x01;
detailsPacket[398]=(byte)0x26;
detailsPacket[399]=(byte)0x02;
detailsPacket[400]=(byte)0x00;
detailsPacket[401]=(byte)0x40;
detailsPacket[402]=(byte)0x27;
detailsPacket[403]=(byte)0x01;
detailsPacket[404]=(byte)0x01;
detailsPacket[405]=(byte)0x28;
detailsPacket[406]=(byte)0x01;
detailsPacket[407]=(byte)0xd8;
detailsPacket[408]=(byte)0x2e;
detailsPacket[409]=(byte)0x01;
detailsPacket[410]=(byte)0x01;
detailsPacket[411]=(byte)0x12;
detailsPacket[412]=(byte)0x07;
detailsPacket[413]=(byte)0x10;
detailsPacket[414]=(byte)0xbc;
detailsPacket[415]=(byte)0x3e;
detailsPacket[416]=(byte)0x07;
detailsPacket[417]=(byte)0x5c;
detailsPacket[418]=(byte)0x26;
detailsPacket[419]=(byte)0x41;
detailsPacket[420]=(byte)0x12;
detailsPacket[421]=(byte)0x07;
detailsPacket[422]=(byte)0x01;
detailsPacket[423]=(byte)0xbc;
detailsPacket[424]=(byte)0x3e;
detailsPacket[425]=(byte)0x07;
detailsPacket[426]=(byte)0x5c;
detailsPacket[427]=(byte)0x26;
detailsPacket[428]=(byte)0x43;


//option 43 vendor specific information


detailsPacket[429]=(byte)0x2b;
detailsPacket[430]=(byte)0x87;
detailsPacket[431]=(byte)0x02;
detailsPacket[432]=(byte)0x03;
detailsPacket[433]=(byte)0x45;
detailsPacket[434]=(byte)0x43;
detailsPacket[435]=(byte)0x4d;
detailsPacket[436]=(byte)0x03;
detailsPacket[437]=(byte)0x10;
detailsPacket[438]=(byte)0x45;
detailsPacket[439]=(byte)0x43;
detailsPacket[440]=(byte)0x4d;
detailsPacket[441]=(byte)0x3a;
detailsPacket[442]=(byte)0x45;
detailsPacket[443]=(byte)0x4d;
detailsPacket[444]=(byte)0x54;
detailsPacket[445]=(byte)0x41;
detailsPacket[446]=(byte)0x3a;
detailsPacket[447]=(byte)0x45;
detailsPacket[448]=(byte)0x52;
detailsPacket[449]=(byte)0x4f;
detailsPacket[450]=(byte)0x55;
detailsPacket[451]=(byte)0x54;
detailsPacket[452]=(byte)0x45;
detailsPacket[453]=(byte)0x52;
detailsPacket[454]=(byte)0x04;
detailsPacket[455]=(byte)0x0c;
detailsPacket[456]=(byte)0x32;
detailsPacket[457]=(byte)0x35;
detailsPacket[458]=(byte)0x32;
detailsPacket[459]=(byte)0x31;
detailsPacket[460]=(byte)0x39;
detailsPacket[461]=(byte)0x32;
detailsPacket[462]=(byte)0x31;
detailsPacket[463]=(byte)0x33;
detailsPacket[464]=(byte)0x39;
detailsPacket[465]=(byte)0x35;
detailsPacket[466]=(byte)0x39;
detailsPacket[467]=(byte)0x35;
detailsPacket[468]=(byte)0x05;
detailsPacket[469]=(byte)0x02;
detailsPacket[470]=(byte)0x30;
detailsPacket[471]=(byte)0x41;
detailsPacket[472]=(byte)0x06;
detailsPacket[473]=(byte)0x12;
detailsPacket[474]=(byte)0x36;
detailsPacket[475]=(byte)0x2e;
detailsPacket[476]=(byte)0x31;
detailsPacket[477]=(byte)0x2e;
detailsPacket[478]=(byte)0x35;
detailsPacket[479]=(byte)0x2e;
detailsPacket[480]=(byte)0x30;
detailsPacket[481]=(byte)0x2e;
detailsPacket[482]=(byte)0x31;
detailsPacket[483]=(byte)0x62;
detailsPacket[484]=(byte)0x35;
detailsPacket[485]=(byte)0x5f;
detailsPacket[486]=(byte)0x32;
detailsPacket[487]=(byte)0x2d;
detailsPacket[488]=(byte)0x4d;
detailsPacket[489]=(byte)0x47;
detailsPacket[490]=(byte)0x43;
detailsPacket[491]=(byte)0x50;
detailsPacket[492]=(byte)0x07;
detailsPacket[493]=(byte)0x1b;
detailsPacket[494]=(byte)0x43;
detailsPacket[495]=(byte)0x47;
detailsPacket[496]=(byte)0x4e;
detailsPacket[497]=(byte)0x56;
detailsPacket[498]=(byte)0x35;
detailsPacket[499]=(byte)0x2d;
detailsPacket[500]=(byte)0x31;
detailsPacket[501]=(byte)0x2e;
detailsPacket[502]=(byte)0x37;
detailsPacket[503]=(byte)0x2e;
detailsPacket[504]=(byte)0x32;
detailsPacket[505]=(byte)0x2d;
detailsPacket[506]=(byte)0x67;
detailsPacket[507]=(byte)0x37;
detailsPacket[508]=(byte)0x34;
detailsPacket[509]=(byte)0x39;
detailsPacket[510]=(byte)0x32;
detailsPacket[511]=(byte)0x36;
detailsPacket[512]=(byte)0x31;
detailsPacket[513]=(byte)0x64;
detailsPacket[514]=(byte)0x33;
detailsPacket[515]=(byte)0x2d;
detailsPacket[516]=(byte)0x64;
detailsPacket[517]=(byte)0x69;
detailsPacket[518]=(byte)0x72;
detailsPacket[519]=(byte)0x74;
detailsPacket[520]=(byte)0x79;
detailsPacket[521]=(byte)0x08;
detailsPacket[522]=(byte)0x06;
detailsPacket[523]=(byte)0x30;
detailsPacket[524]=(byte)0x30;
detailsPacket[525]=(byte)0x30;
detailsPacket[526]=(byte)0x35;
detailsPacket[527]=(byte)0x43;
detailsPacket[528]=(byte)0x41;
detailsPacket[529]=(byte)0x09;
detailsPacket[530]=(byte)0x05;
detailsPacket[531]=(byte)0x43;
detailsPacket[532]=(byte)0x47;
detailsPacket[533]=(byte)0x4e;
detailsPacket[534]=(byte)0x56;
detailsPacket[535]=(byte)0x35;
detailsPacket[536]=(byte)0x0a;
detailsPacket[537]=(byte)0x13;
detailsPacket[538]=(byte)0x48;
detailsPacket[539]=(byte)0x69;
detailsPacket[540]=(byte)0x74;
detailsPacket[541]=(byte)0x72;
detailsPacket[542]=(byte)0x6f;
detailsPacket[543]=(byte)0x6e;
detailsPacket[544]=(byte)0x20;
detailsPacket[545]=(byte)0x54;
detailsPacket[546]=(byte)0x65;
detailsPacket[547]=(byte)0x63;
detailsPacket[548]=(byte)0x68;
detailsPacket[549]=(byte)0x6e;
detailsPacket[550]=(byte)0x6f;
detailsPacket[551]=(byte)0x6c;
detailsPacket[552]=(byte)0x6f;
detailsPacket[553]=(byte)0x67;
detailsPacket[554]=(byte)0x69;
detailsPacket[555]=(byte)0x65;
detailsPacket[556]=(byte)0x73;
detailsPacket[557]=(byte)0x0f;
detailsPacket[558]=(byte)0x07;
detailsPacket[559]=(byte)0x45;
detailsPacket[560]=(byte)0x52;
detailsPacket[561]=(byte)0x4f;
detailsPacket[562]=(byte)0x55;
detailsPacket[563]=(byte)0x54;
detailsPacket[564]=(byte)0x45;
detailsPacket[565]=(byte)0x52;

//OPTION 55


detailsPacket[566]=(byte)0x37;
detailsPacket[567]=(byte)0x0c;
detailsPacket[568]=(byte)0x01;
detailsPacket[569]=(byte)0x02;
detailsPacket[570]=(byte)0x03;
detailsPacket[571]=(byte)0x04;
detailsPacket[572]=(byte)0x06;
detailsPacket[573]=(byte)0x07;
detailsPacket[574]=(byte)0x36;
detailsPacket[575]=(byte)0x42;
detailsPacket[576]=(byte)0x43;
detailsPacket[577]=(byte)0x64;
detailsPacket[578]=(byte)0x7d;
detailsPacket[579]=(byte)0x7a;


//option 82

detailsPacket[580]=(byte)0x52;
detailsPacket[581]=(byte)0x15;
detailsPacket[582]=(byte)0x02;
detailsPacket[583]=(byte)0x06;
detailsPacket[584]=(byte)0xbc;
detailsPacket[585]=(byte)0x3e;
detailsPacket[586]=(byte)0x07;
detailsPacket[587]=(byte)0x5c;
detailsPacket[588]=(byte)0x26;
detailsPacket[589]=(byte)0x40;
detailsPacket[590]=(byte)0x09;
detailsPacket[591]=(byte)0x0b;
detailsPacket[592]=(byte)0x00;
detailsPacket[593]=(byte)0x00;
detailsPacket[594]=(byte)0x11;
detailsPacket[595]=(byte)0x8b;
detailsPacket[596]=(byte)0x06;
detailsPacket[597]=(byte)0x01;
detailsPacket[598]=(byte)0x04;
detailsPacket[599]=(byte)0x01;
detailsPacket[600]=(byte)0x02;
detailsPacket[601]=(byte)0x03;
detailsPacket[602]=(byte)0x00;


//END

detailsPacket[603]=(byte)0xff;


//
        
        
        
        
        
        
    }
      
      
    /**
     * @return the bootRequest
     */
    public byte getBootRequest() {
        return bootRequest;
    }

    /**
     * @param bootRequest the bootRequest to set
     */
    public void setBootRequest(byte bootRequest) {
        this.bootRequest = bootRequest;
    }

    /**
     * @return the hwdType
     */
    public byte getHwdType() {
        return hwdType;
    }

    /**
     * @param hwdType the hwdType to set
     */
    public void setHwdType(byte hwdType) {
        this.hwdType = hwdType;
    }

    /**
     * @return the hwdAaddrsLength
     */
    public byte getHwdAaddrsLength() {
        return hwdAaddrsLength;
    }

    /**
     * @param hwdAaddrsLength the hwdAaddrsLength to set
     */
    public void setHwdAaddrsLength(byte hwdAaddrsLength) {
        this.hwdAaddrsLength = hwdAaddrsLength;
    }

    /**
     * @return the hops
     */
    public byte getHops() {
        return hops;
    }

    /**
     * @param hops the hops to set
     */
    public void setHops(byte hops) {
        this.hops = hops;
    }

    /**
     * @return the txID
     */
    public  int  getTxID() {
        
      return this.txID;
        
        
    }

    /**
     * @param txID the txID to set
     */
    public void generateTxID() {
        
    Random random = new Random();
    int transactionId = random.nextInt();  // Generate a random integer value
    detailsPacket[4] = (byte) (transactionId >> 24);
    detailsPacket[5] = (byte) (transactionId >> 16);
    detailsPacket[6] = (byte) (transactionId >> 8);
    detailsPacket[7] = (byte) transactionId;

        
        
        this.txID = transactionId;
    }

    /**
     * @return the secondsElapsed
     */
    public byte getSecondsElapsed() {
        return secondsElapsed;
    }

    /**
     * @param secondsElapsed the secondsElapsed to set
     */
    public void setSecondsElapsed(byte secondsElapsed) {
        this.secondsElapsed = secondsElapsed;
    }

    /**
     * @return the bootTPFlags
     */
    public String  getBootTPFlags() {
        return bootTPFlags;
    }

    /**
     * @param bootTPFlags the bootTPFlags to set
     */
    public void setBootTPFlags(String bootTPFlags) {
        this.bootTPFlags = bootTPFlags;
    }

    /**
     * @return the clientiPAddress
     */
    public String getClientiPAddress() {
        return clientiPAddress;
    }

    /**
     * @param n1
     * @param n2
     * @param n3
     * @param n4
     * @param clientiPAddress the clientiPAddress to set
     */
    public void setClientiPAddress(byte n1, byte n2, byte n3, byte n4) {
    
        detailsPacket[11] =(byte)n1;
        detailsPacket[12] =(byte)n2;
        detailsPacket[13] =(byte)n3;
        detailsPacket[14] =(byte)n4;
    
        this.clientiPAddress = n1+"."+n2+"."+n3+"."+n4;
    }

    /**
     * @return the yourClientiPAddress
     */
    public String getYourClientiPAddress() {
        

        return yourClientiPAddress;
                
    }

    /**
     * @param n1
     * @param n2
     * @param n3
     * @param n4
     */
    public void setYourClientiPAddress(byte n1, byte n2, byte n3, byte n4) {
        
        detailsPacket[15] =(byte)n1;
        detailsPacket[16] =(byte)n2;
        detailsPacket[17] =(byte)n3;
        detailsPacket[18] =(byte)n4;
    
        this.clientiPAddress = n1+"."+n2+"."+n3+"."+n4;    }

    /**
     * @return the nxtServerIPAddress
     */
    public String getNxtServerIPAddress() {
        return nxtServerIPAddress;
    }

    /**
     * @param n1
     * @param n2
     * @param n3
     * @param n4
     */
    public void setNxtServerIPAddress(byte n1, byte n2, byte n3, byte n4) {
        
        detailsPacket[19] =(byte)0;
        detailsPacket[20] =(byte)0;
        detailsPacket[21] =(byte)0;
        detailsPacket[22] =(byte)0;  
        this.nxtServerIPAddress = detailsPacket[19]+"."+detailsPacket[20]+"."+detailsPacket[21]+"."+detailsPacket[22];
        
       
    }

    /**
     * @return the delimiter
     */
    public byte getDelimiter() {
        return delimiter;
    }

    /**
     * @param delimiter the delimiter to set
     */
    public void setDelimiter(byte delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * @return the relayAgentIPaddress
     */
    public String getRelayAgentIPaddress() {
        return relayAgentIPaddress;
    }

    /**
     */
    public void setRelayAgentIPaddress(byte n1, byte n2, byte n3, byte n4) {
        
        detailsPacket[24] =(byte)192;
        detailsPacket[25] =(byte)168;
        detailsPacket[26] =(byte)7;
        detailsPacket[27] =(byte)1; 
       this.relayAgentIPaddress = detailsPacket[24]+"."+detailsPacket[25]+"."+detailsPacket[26]+"."+detailsPacket[27];
    
    }

    /**
     * @return the clientMacAddress
     */
    public String getClientMacAddress() {
        return clientMacAddress;
    }

    /**
     * @param macAddress
     */
    public void setClientMacAddress(String macAddress) {

    //String macAddress = "00:ac:e0:45:6f:82";  // Replace with your desired MAC address
    String[] macParts = macAddress.split(":");
    for (int i = 0; i < macParts.length; i++) {
        int macPart = Integer.parseInt(macParts[i], 16);
        System.out.println("macPart "+macPart);
        detailsPacket[28 + i] = (byte) macPart;

    }


    }

    /**
     * @return the clientHWDDAddressMapping
     */
    public byte getClientHWDDAddressMapping() {
        return clientHWDDAddressMapping;
    }

    /**
     * @param clientHWDDAddressMapping the clientHWDDAddressMapping to set
     */
    public void setClientHWDDAddressMapping(byte clientHWDDAddressMapping) {
        this.clientHWDDAddressMapping = clientHWDDAddressMapping;
    }

    /**
     * @return the severHostname
     */
    public byte getSeverHostname() {
        return severHostname;
    }

    /**
     * @param severHostname the severHostname to set
     */
    public void setSeverHostname(byte severHostname) {
        this.severHostname = severHostname;
    }

    /**
     * @return the bootfile
     */
    public byte getBootfile() {
        return bootfile;
    }

    /**
     * @param bootfile the bootfile to set
     */
    public void setBootfile(byte bootfile) {
        this.bootfile = bootfile;
    }

    /**
     * @return the magicCookie
     */
    public String getMagicCookie() {
        return magicCookie;
    }

    /**
     * @return the options
     */
    public DHCPOptions getOptions() {
        return options;
    }

    /**
     * @param options the options to set
     */
    public void setOptions(DHCPOptions options) {
        this.options = options;
    }
    
    public byte[] getDetailsPacket() {
        return detailsPacket;
    }

    public void setDetailsPacket(byte[] detailsPacket) {
        this.detailsPacket = detailsPacket;
    }
    
    public int getLenDetailsPacket()
    {
        return len;
        
    }
    
    
}
