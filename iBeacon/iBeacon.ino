#include <ArduinoBLE.h>

//BLE service to send RSSI value
BLEService sendRSSI("180FF");

//BLE RSSI Level Characteristic
BLEUnsignedCharCharacteristic sendRSSIChar("2A19",  // standard 16-bit characteristic UUID
    BLERead | BLENotify);  //remote clients will be able to get notifications if this characteristic changes

int oldRSSI = 0;  // last rssi reading from analog input 
long previousMillis = 0;  // last time the rssi was checked

void setup() {
  Serial.begin(9600);    // initialize serial communication
  while (!Serial);

  pinMode(LED_BUILTIN, OUTPUT); // initialize the built-in LED pin to indicate when a central is connected

  // begin initialization
  if (!BLE.begin()) {
    Serial.println("starting BLE failed!");

    while (1);
  }
  
  BLE.setDeviceName("iBeacon"); // set the bluetooth to show up as iBeacon
  BLE.setLocalName("Beacon1"); // set the local name of the iBeacon to Beacon1
  BLE.setAdvertisedService(sendRSSI); // add the service UUID
  sendRSSI.addCharacteristic(sendRSSIChar); // add the rssi level characteristic
  BLE.addService(sendRSSI); // add the sendRSSI service
  sendRSSIChar.writeValue(oldRSSI); // set the initial rssi for this characteristic

  //Start advertising BLE. It will start continously transmitting BLE
  //advertising packets and will be visible to remote BLE central devices
  //until it receives a new connection

  // start the advertisement
  BLE.advertise();
  Serial.println("Bluetooth beacon active, waiting for connections...");
  
}

void loop() {
  // wait for a BLE central
  BLEDevice central = BLE.central();

  // if a central is connected to the peripheral:
  if (central) {
    Serial.print("Connected to central: ");
    // print the central's BT address:
    Serial.println(central.address());
    // turn on the LED to indicate the connection:
    digitalWrite(LED_BUILTIN, HIGH);

    // check the RSSI every 200ms
    // while the central is connected:
    while (central.connected()) {
      long currentMillis = millis();
      // if 200ms have passed, check the RSSI:
      if (currentMillis - previousMillis >= 200) {
        previousMillis = currentMillis;
        updateRSSI();
      }
    }
    // when the central disconnects, turn off the LED:
    digitalWrite(LED_BUILTIN, LOW);
    Serial.print("Disconnected from central: ");
    Serial.println(central.address());
  }
  loop();
}

void updateRSSI() {
  //Read the RSSI value from the central
  int rssiValue =  BLE.rssi();
  int rssiLevel = map(rssiValue, -128, 128, -128, 128);

  if(rssiValue != oldRSSI){
    Serial.print("RSSI value is now: "); // print the rssi value
    Serial.println(rssiValue); 
    sendRSSIChar.writeValue(rssiLevel); // update the rssi characteristic
    oldRSSI = rssiLevel; // save the level for comparison
  }
}
