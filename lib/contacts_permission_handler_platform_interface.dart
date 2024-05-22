import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'contacts_permission_handler_method_channel.dart';
import 'permission_status.dart';

abstract class ContactsPermissionHandlerPlatform extends PlatformInterface {
  /// Constructs a ContactsPermissionHandlerPlatform.
  ContactsPermissionHandlerPlatform() : super(token: _token);

  static final Object _token = Object();

  static ContactsPermissionHandlerPlatform _instance =
      MethodChannelContactsPermissionHandler();

  /// The default instance of [ContactsPermissionHandlerPlatform] to use.
  ///
  /// Defaults to [MethodChannelContactsPermissionHandler].
  static ContactsPermissionHandlerPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [ContactsPermissionHandlerPlatform] when
  /// they register themselves.
  static set instance(ContactsPermissionHandlerPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<PermissionStatus?> checkStatus() {
    throw UnimplementedError('checkStatus() has not been implemented.');
  }

  Future<PermissionStatus?> requestAccess() {
    throw UnimplementedError('requestAccess() has not been implemented.');
  }
}
