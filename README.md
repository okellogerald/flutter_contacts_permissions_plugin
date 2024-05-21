# contacts_permission_handler

Helps with requesting access to user's contacts as well as checking contacts permission status.

## Android Setup
```dart
package {yourPackageName}

import com.okello.contacts_permission_handler.SmileIdentityMainActivity

class MainActivity: SmileIdentityMainActivity()
```

And in the Manifest xml file, make sure to include
```
 <uses-permission android:name="android.permission.READ_CONTACTS" />
```

## IOS setup
Only make sure to include `NSContactsUsageDescription` in your info.plist file

## Guide
>Initialize e.g `plugin = ContactsPermissionHandler();`
>Get status with: `plugin.checkStatus();`
>Request access with: `plugin.requestAccess();`

Both methods return `PermissionStatus`
```dart
enum PermissionStatus {
  notDetermined,
  restricted,
  denied,
  authorized,
}
```