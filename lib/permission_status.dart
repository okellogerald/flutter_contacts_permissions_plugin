enum PermissionStatus {
  notDetermined,
  restricted,
  denied,
  authorized,
  ;
}

extension PermissionStatusExtension on PermissionStatus {
  static PermissionStatus fromInt(int value) {
    switch (value) {
      case 1:
        return PermissionStatus.restricted;
      case 2:
        return PermissionStatus.denied;
      case 3:
        return PermissionStatus.authorized;
      default:
        return PermissionStatus.notDetermined;
    }
  }

  bool get granted => this == PermissionStatus.authorized;

  bool get denied => this == PermissionStatus.denied;
}
