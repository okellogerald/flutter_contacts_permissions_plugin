import 'package:contacts_permission_handler/permission_status.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'contacts_permission_handler_platform_interface.dart';

/// An implementation of [ContactsPermissionHandlerPlatform] that uses method channels.
class MethodChannelContactsPermissionHandler extends ContactsPermissionHandlerPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('contacts_permission_handler');

  @override
  Future<PermissionStatus?> checkStatus() async {
    final result = await methodChannel.invokeMethod<int>('checkStatus');
    if(result == null) return null;
    return PermissionStatusExtension.fromInt(result);
  }

  @override
  Future<PermissionStatus?> requestAccess() async {
    final result = await methodChannel.invokeMethod<int>('requestAccess');
    if(result == null) return null;
    return PermissionStatusExtension.fromInt(result);
  }
}
