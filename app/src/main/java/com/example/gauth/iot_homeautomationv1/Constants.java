package com.example.gauth.iot_homeautomationv1;

public class Constants {
    public static String username="";
    public static String usernameUtility="";
    public static String usernamePowerGenerator="";
   // public static String ROOT_URL = "http://192.168.1.130/Android/v1/";
    public static String ROOT_URL = "http://10.216.58.43/Android/v1/";
    public static String ROOT_URL_UTILITY= "http://192.168.1.145/Android/v1/";
    public static String ROOT_URL_POWERGENERATOR= "http://192.168.1.136/Android/v1/";
    public static String URL_REGISTER =ROOT_URL+"registerUser.php";
    public static String URL_LOGIN =ROOT_URL+"userLogin.php";
    public  static String  URL_SECURITY =ROOT_URL+"SecuritySystem.php";
    public  static  String URL_GARAGEDOOR=ROOT_URL+"GarageDoor.php";
    public  static  String URL_LIGHTS=ROOT_URL+"Light.php";
    public  static  String URL_MOTIONDETECTOR=ROOT_URL+"MotionDetection.php";
    public  static  String URL_WINDOW=ROOT_URL+"WindowSensor.php";
    public  static  String URL_LOCK=ROOT_URL+"Locks.php";
    public  static  String URL_THERMOSTAT=ROOT_URL+"Thermostat.php";
    public  static  String URL_ELECTRICAPPLIANCES=ROOT_URL+"ElectricAppliances.php";
    public static String URL_WEATHERFORECAST= ROOT_URL + "Weather.php";
    public static String URL_STATUS= ROOT_URL+ "Status.php";
    public static String URL_ApplianceStatus = ROOT_URL_UTILITY +"StatusUtility.php";
   public static String URL_PowerGeneratorStatus = ROOT_URL_POWERGENERATOR + "StatusPower.php";

}
