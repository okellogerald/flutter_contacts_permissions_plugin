import 'contacts_permission_handler_platform_interface.dart';
import 'permission_status.dart';

class ContactsPermissionHandler {
  Future<String?> getPlatformVersion() {
    return ContactsPermissionHandlerPlatform.instance.getPlatformVersion();
  }

  Future<PermissionStatus?> checkStatus() {
    return ContactsPermissionHandlerPlatform.instance.checkStatus();
  }

  Future<PermissionStatus?> requestAccess() {
    return ContactsPermissionHandlerPlatform.instance.requestAccess();
  }
}
