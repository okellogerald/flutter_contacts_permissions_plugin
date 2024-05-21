import Flutter
import UIKit
import Contacts

public class ContactsPermissionHandlerPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "contacts_permission_handler", binaryMessenger: registrar.messenger())
    let instance = ContactsPermissionHandlerPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

    public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    switch call.method {
    case "getPlatformVersion":
         result("iOS " + UIDevice.current.systemVersion)
    case "checkStatus":
        let authorizationStatus = CNContactStore.authorizationStatus(for: .contacts)
        result(authorizationStatus.rawValue)
    case "requestAccess":
        let contactStore = CNContactStore()
        contactStore.requestAccess(for: .contacts) {(g, e) in }
        let authorizationStatus = CNContactStore.authorizationStatus(for: .contacts)
        result(authorizationStatus.rawValue)
    default:
      result(FlutterMethodNotImplemented)
    }
  }
}
