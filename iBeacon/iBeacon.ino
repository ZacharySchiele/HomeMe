/*

*/
#include <ArduinoBLE.h>

BLEService IPC_Service("0x2AAD");
BLEService FN_Service("0x2AB2");
BLEService LN_Service("0x2AB5");
BLEService P3D_Service("0x2A30");
BLEService RN_Service("0x2A3D");

//Creates a gatt characteristic that holds the Indoor Position Configuration, both readable and writable by central
BLEUnsignedIntCharacteristic IndoorPositioningConfiguration ("0x2AAD", BLERead | BLEWrite);
//Creates a gatt characteristic that holds the floor number, both readable and writable by central
BLEUnsignedIntCharacteristic FloorNumber ("0x2AB2", BLERead | BLEWrite);
//Creates a gatt characteristic that holds the Location Name, both readable and writable by central
BLEUnsignedCharCharacteristic LocationName ("0x2AB5", BLERead | BLEWrite);
//Creates a gatt characteristic that holds the 3D Positioning, both readable and writable by central
BLEUnsignedIntCharacteristic Position3D ("0x2A30", BLERead | BLEWrite);
//Creates a gatt characteristic that holds the Routine Name, both readable and writable by central
BLEUnsignedCharCharacteristic RoutineName ("0x2A3D", BLERead | BLEWrite);

void setup() {
  Serial.begin(9600);    // initialize serial communication
  while (!Serial);

  pinMode(LED_BUILTIN, OUTPUT); // initialize the built-in LED pin to indicate when a central is connected
  //intializes the bluetooth ble beacon
  if (!BLE.begin()) {
    Serial.println("starting BLE failed!");

    while (1);
  }
  
  BLE.setDeviceName("Beacon");
  
  BLE.setAdvertisedService(IPC_Service);
  IPC_Service.addCharacteristic(IndoorPositioningConfiguration); 
  BLE.addService(IPC_Service);
  
  BLE.setAdvertisedService(FN_Service);
  FN_Service.addCharacteristic(FloorNumber); 
  BLE.addService(FN_Service);
  
  BLE.setAdvertisedService(LN_Service);
  LN_Service.addCharacteristic(LocationName); 
  BLE.addService(LN_Service);
  
  BLE.setAdvertisedService(P3D_Service);
  P3D_Service.addCharacteristic(Position3D); 
  BLE.addService(P3D_Service);
  
  BLE.setAdvertisedService(RN_Service);
  RN_Service.addCharacteristic(RoutineName); 
  BLE.addService(RN_Service); 
    
  //Start advertising BLE. It will start continously transmitting BLE
  //advertising packets and will be visible to remote BLE central devices
  //until it receives a new connection

  // start the advertisement
  BLE.advertise();
  Serial.println("Bluetooth beacon active, waiting for connections...");
}

void loop() {
  
  //initializes central devices to allow for connections to central devices
  BLEDevice central = BLE.central();
  
  dummyVals();
  
  //Wait for a connection to the centtral then do something
  if (central){
   
    Serial.print("Connected to central: ");
    // print the central's BT address:
    Serial.println(central.address());
    // turn on the LED to indicate the connection:
    digitalWrite(LED_BUILTIN, HIGH);
    
    while (central.connected()){
      
    }
    digitalWrite(LED_BUILTIN, LOW);
    Serial.print("Disconnected from central: ");
    Serial.println(central.address());
  }
  
}

void dummyVals(){
  int IPC = 0;
  int FloorNumberInt = 0;
  char LocationNameString = 'R';
  int ThreeDPosition = 0;
  char RoutineNameString = 'R';
  //Dummy values
  IndoorPositioningConfiguration.writeValue(IPC);
  FloorNumber.writeValue(FloorNumberInt);
  LocationName.writeValue(LocationNameString);
  Position3D.writeValue(ThreeDPosition);
  RoutineName.writeValue(RoutineNameString);
  
}
