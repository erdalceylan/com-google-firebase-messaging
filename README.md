
## How to use
- Import gradle dependency:
````
allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
}
````
````      
        dependencies {
            compile 'com.google.firebase:firebase-core:11.2.0'
            compile 'com.github.erdalceylan:com-google-firebase-messaging:ver11.2.0'
        }

````

- your app is in background or foreground all time calling 

````
@WorkerThread
    public void onMessageReceived(RemoteMessage var1) {
    }
````

**Documentation**

https://firebase.google.com/docs/cloud-messaging/android/client