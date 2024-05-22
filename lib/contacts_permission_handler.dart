import 'contacts_permission_handler_platform_interface.dart';
import 'permission_status.dart';

class ContactsPermissionHandler {
  Future<PermissionStatus?> checkStatus() {
    return ContactsPermissionHandlerPlatform.instance.checkStatus();
  }

  Future<PermissionStatus?> requestAccess() {
    return ContactsPermissionHandlerPlatform.instance.requestAccess();
  }
}
