package com.nokia.atf.tef.deviceSimulator;


import com.nokia.atf.tef.smp.uiflow.scenarios.Sqlrowset;
import com.nokia.mtaf.lib.common.annotation.DataBind;
import com.nokia.mtaf.lib.common.exception.TestDataException;
import com.nokia.mtaf.lib.common.testdata.IImportTestDataExt;

import junit.framework.Assert;
import net.thucydides.core.annotations.Step;
//import oracle.jdbc.pool.OracleDataSource;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class DefaultSimulatorProperties implements IImportTestDataExt<DefaultSimulatorProperties> {
  
  @DataBind
  public String FIRMWARE_VERSION = null;
  
  @DataBind
  public String DEVICE_ID = null;
  
  @DataBind
  public String LRG_OBJ = null;
  
  @DataBind
  public String URI_MAX_SEG_LEN = null;
  
  @DataBind
  public String DEV_TYP = null;
  
  @DataBind
  public String CLIENT_NONCE = null;
  
  @DataBind
  public String ANNOUNCED_MODEL = null;
  
  @DataBind
  public String CLIENT_PASSWORD = null;
  
  @DataBind
  public String HOSTDEVICE_HARDWARE_VERSION = null;
   
  @DataBind
  public String URI_MAX_TOT_LEN = null;
  
  @DataBind
  public String URI_MAX_DEPTH = null;
  
  @DataBind
  public String SERVER_NONCE = null;
  
  @DataBind
  public String OEM = null;
  
  @DataBind
  public String SERVER_PASSWORD = null;
  
  
  @DataBind
  public String language = null;
  
  @DataBind
  public String server_username = null;
  
  @DataBind
  public String client_username = null;
  
  @DataBind
  public String ANNOUNCED_MANUFACTURER = null;
  
  @DataBind
  public String HOSTDEVICE_SOFTWARE_VERSION = null;
  
  @DataBind
  public String dm_version = null;

  @DataBind
  public String ICCID = null;

  private static final Logger logger = LoggerFactory.getLogger(DefaultSimulatorProperties.class);
  
  
  public DefaultSimulatorProperties(String deviceId) throws Exception {
    //rowSet.first();
   // importTestData(rowSet, this); 
    FIRMWARE_VERSION = "G360VVRE2BOF3";
    DEVICE_ID = deviceId;
    ANNOUNCED_MODEL= "SM-G360V";	
    CLIENT_NONCE = "Cj8kNDhbMWDpmFZtenrt+Q==";
    ANNOUNCED_MANUFACTURER = "SAMSUNG";
    CLIENT_PASSWORD = generateMD5(DEVICE_ID);
    HOSTDEVICE_HARDWARE_VERSION = "1.0";
    URI_MAX_TOT_LEN = "2";
    URI_MAX_DEPTH = "2";
    SERVER_NONCE = "inz3YIFObAR9qmjiGCTXBQ==";
    OEM = "oem1.0";
    SERVER_PASSWORD = "0000000000000000";
    language = "UA";
    server_username = "com.vzwdmserver";
    client_username = DEVICE_ID;
    HOSTDEVICE_SOFTWARE_VERSION = "1.0";
     dm_version = "DM1.2";
     ICCID = DEVICE_ID+"00000";
    LRG_OBJ= "1000";
    URI_MAX_SEG_LEN="32";
    DEV_TYP = "palm";
    URI_MAX_TOT_LEN = "127";
     URI_MAX_DEPTH = "12";
     OEM = "oem1.0";
     language = "English";
     server_username = "com.vzwdmserver";
     client_username = DEVICE_ID;
     dm_version = "1.2";
  }
  
  public  String generateMD5(String deviceID) throws Exception{
	  
  
	  logger.info("Client password is" + DigestUtils.md5Hex( deviceID ));
	  return DigestUtils.md5Hex( deviceID ) ;
	
  }

  public static void main(String args[]) throws Exception{
		 
	 
		 Sqlrowset s = new Sqlrowset();
		 SqlRowSet s1= s.doExecute();
		 
		 String deviceid="990000750004565";
		 DefaultSimulatorProperties d = new DefaultSimulatorProperties(deviceid);
		 System.out.println("IMEI"+ d.DEVICE_ID);
		 
		System.out.println("IMEI"+ d.ICCID);
		
		System.out.println("FIRMWARE_VERSION"+ d.FIRMWARE_VERSION);
		System.out.println("LRG_OBJ"+ d.LRG_OBJ);
		System.out.println("URI_MAX_SEG_LEN"+ d.URI_MAX_SEG_LEN);
		System.out.println("DEV_TYP"+ d.DEV_TYP);
		System.out.println("CLIENT_NONCE"+ d.CLIENT_NONCE);
		System.out.println("ANNOUNCED_MODEL"+ d.ANNOUNCED_MODEL);
		System.out.println("CLIENT_PASSWORD"+ d.CLIENT_PASSWORD);
		System.out.println("HOSTDEVICE_HARDWARE_VERSION"+ d.HOSTDEVICE_HARDWARE_VERSION);
		System.out.println("URI_MAX_TOT_LEN"+ d.URI_MAX_TOT_LEN);
		System.out.println("URI_MAX_DEPTH"+ d.URI_MAX_DEPTH);
		System.out.println("SERVER_NONCE"+ d.SERVER_NONCE);
		System.out.println("OEM"+ d.OEM);
		
		System.out.println("SERVER_PASSWORD"+ d.SERVER_PASSWORD);
		System.out.println("language"+ d.language);
		System.out.println("server_username"+ d.server_username);
		System.out.println("client_username"+ d.client_username);
		System.out.println("ANNOUNCED_MANUFACTURER"+ d.ANNOUNCED_MANUFACTURER);
		
		System.out.println("HOSTDEVICE_SOFTWARE_VERSION"+ d.HOSTDEVICE_SOFTWARE_VERSION);
		System.out.println("dm_version"+ d.dm_version);
		System.out.println("ICCID"+ d.ICCID);
	
		
	 }
}
