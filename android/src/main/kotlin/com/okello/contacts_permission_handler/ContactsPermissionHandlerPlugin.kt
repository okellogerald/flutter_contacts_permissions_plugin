package com.okello.contacts_permission_handler

import android.Manifest
import android.app.Activity

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.flutter.embedding.android.FlutterFragmentActivity
import io.flutter.embedding.engine.FlutterEngine

import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.PluginRegistry
import java.util.Date

class ContactsPermissionHandlerPlugin: FlutterPlugin, MethodCallHandler, ActivityAware, PluginRegistry.RequestPermissionsResultListener {
  private lateinit var channel : MethodChannel
  private var activity: Activity? = null
  private lateinit var result: Result

  companion object {
    private const val CONTACT_PERMISSION_REQUEST_CODE = 123
    private const val READ_CONTACTS_PERMISSION = Manifest.permission.READ_CONTACTS
  }

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "contacts_permission_handler")
    channel.setMethodCallHandler(this)
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  override fun onMethodCall(call: MethodCall, r: Result) {
    result = r

    when (call.method) {
        "checkStatus" -> {
          result.success(checkForContactsPermission())
        }
        "requestAccess" -> {
          requestContactsAccess()
        }
        else -> {
          result.notImplemented()
        }
    }
  }

  private fun checkForContactsPermission(): Int? {
    if(activity == null) return null

    val permission = ContextCompat.checkSelfPermission(activity!!, READ_CONTACTS_PERMISSION)
    return when(permission) {
      PackageManager.PERMISSION_GRANTED -> 3
      PackageManager.PERMISSION_DENIED -> 2
      else -> {null}
    }
  }

  private fun requestContactsAccess() {
    if (activity == null) return

    println("Started")
    println(Date())

    ActivityCompat.requestPermissions(
      activity!!,
      arrayOf(READ_CONTACTS_PERMISSION),
      CONTACT_PERMISSION_REQUEST_CODE,
    )

    println(Date())
    println("Ended")
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    println("on attached")
   activity = binding.activity
  }

  override fun onDetachedFromActivityForConfigChanges() {
    println("on detached")
    activity = null
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    println("on reattached to activity")
    activity = binding.activity
  }

  override fun onDetachedFromActivity() {
    println("on detached from activity")
   activity = null
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ): Boolean {
    println(requestCode)
    println(permissions)
    println(grantResults)
    println(requestCode == CONTACT_PERMISSION_REQUEST_CODE)
    if(requestCode == CONTACT_PERMISSION_REQUEST_CODE) {
        result.success(checkForContactsPermission())
    }

    return true
  }
}

open class SmileIdentityMainActivity : FlutterFragmentActivity(), MethodCallHandler {
  private lateinit var channel : MethodChannel
  private var activity: Activity? = null
  private lateinit var result: Result

  companion object {
    private const val CONTACT_PERMISSION_REQUEST_CODE = 123
    private const val READ_CONTACTS_PERMISSION = Manifest.permission.READ_CONTACTS
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activity = this
  }

  override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
    super.configureFlutterEngine(flutterEngine)
    val messenger = flutterEngine.dartExecutor.binaryMessenger
    channel = MethodChannel(messenger, "contacts_permission_handler")
    channel.setMethodCallHandler(this)
  }

  override fun onDetachedFromWindow() {
    channel.setMethodCallHandler(null)
  }

  override fun onMethodCall(call: MethodCall, r: Result) {
    result = r

    when (call.method) {
      "checkStatus" -> {
        result.success(checkForContactsPermission())
      }
      "requestAccess" -> {
        requestContactsAccess()
      }
      else -> {
        result.notImplemented()
      }
    }
  }

  private fun checkForContactsPermission(): Int? {
    if(activity == null) return null

    val permission = ContextCompat.checkSelfPermission(activity!!, READ_CONTACTS_PERMISSION)
    return when(permission) {
      PackageManager.PERMISSION_GRANTED -> 3
      PackageManager.PERMISSION_DENIED -> 2
      else -> {null}
    }
  }

  private fun requestContactsAccess() {
    if (activity == null) return

    println("Started")
    println(Date())

    ActivityCompat.requestPermissions(
      activity!!,
      arrayOf(READ_CONTACTS_PERMISSION),
      CONTACT_PERMISSION_REQUEST_CODE,
    )

    println(Date())
    println("Ended")
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    println(requestCode)
    println(permissions)
    println(grantResults)
    println(requestCode == CONTACT_PERMISSION_REQUEST_CODE)
    if(requestCode == CONTACT_PERMISSION_REQUEST_CODE) {
      result.success(checkForContactsPermission())
    }
  }
}