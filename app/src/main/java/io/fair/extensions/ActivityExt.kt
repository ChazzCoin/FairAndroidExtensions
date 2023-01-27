package io.fair.extensions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


fun Context.getWindowManager() : WindowManager {
    return getSystemService(Context.WINDOW_SERVICE) as WindowManager
}

@RequiresApi(Build.VERSION_CODES.R)
fun WindowManager.getWindowWidthAndHeight(): Pair<Int, Int> {
    return Pair(currentWindowMetrics.bounds.width(), currentWindowMetrics.bounds.height())
}

@RequiresApi(Build.VERSION_CODES.R)
fun Activity.getWindowWidthAndHeight(): Pair<Int, Int>? {
    return windowManager?.getWindowWidthAndHeight()
}

@RequiresApi(Build.VERSION_CODES.R)
fun Context.getWindowWidthAndHeight(): Pair<Int, Int> {
    return getWindowManager().getWindowWidthAndHeight()
}

fun Context.isConnectedToInternet(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val capabilities = connectivityManager.getNetworkCapabilities(network)
    return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}

/** startActivity **/
inline fun <reified TO> Activity.launchActivity() {
    startActivity(Intent(this, TO::class.java))
}

/** Register for Activity Result **/
//inline fun <reified CONTRACT> FragmentActivity.fairRegisterActivityResult(intent: Intent, crossinline block: (CONTRACT) -> Unit) {
//    this.registerForActivityResult(FirebaseAuthUIActivityResultContract()) { res ->
//        (res as? CONTRACT)?.let { block(it) }
//    }.launch(intent)
//}
//
///** Pick Image From Gallery Intent **/
//inline fun Fragment.fairPickImageFromGallery(crossinline block: (Uri?) -> Unit) {
//    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//        block(uri)
//    }
//    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
////        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.SingleMimeType("image/gif")))
//}

inline fun <reified T> isFragmentOrActivity() = when (T::class) {
    Fragment::class -> true
    Activity::class -> true
    else -> false
}

inline fun <reified T> isActivity(): Boolean {
    val tClass = T::class.javaClass.superclass
    if (tClass.isInstance(Activity::class)) return true
    return false
}

inline fun <reified T> isFragment(): Boolean {
    val tClass = T::class.javaClass.superclass
    if (tClass.isInstance(Fragment::class)) return true
    return false
}

inline fun <reified T> isFragmentActivity(): Boolean {
    val tClass = T::class.javaClass.superclass
    if (tClass.isInstance(FragmentActivity::class)) return true
    return false
}

//inline fun <reified T> T.fairPickImageFromGalleryTest(crossinline block: (Uri?) -> Unit) {
//    if (!isFragment<T>() && !isFragmentActivity<T>()) return
//    if (isFragment<T>()) {
//        val pickMedia = (this as? Fragment)?.registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//                block(uri)
//            }
//        pickMedia?.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//    }
//    else if (isFragmentActivity<T>()) {
//        val pickMedia = (this as? FragmentActivity)?.registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//            block(uri)
//        }
//        pickMedia?.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//    }
//}
//inline fun Fragment.fairGetPickImageFromGalleryIntent(crossinline block: (Uri?) -> Unit): ActivityResultLauncher<PickVisualMediaRequest> {
//    return registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//        block(uri)
//    }
//}

inline fun FragmentActivity.fairRequestPermission(permission:String= Manifest.permission.WRITE_EXTERNAL_STORAGE, crossinline block: (Boolean) -> Unit) {
    // Registers a photo picker activity launcher in single-select mode.
    val perms = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        block(isGranted)
    }
    perms.launch(permission)
}

inline fun Fragment.fairRequestPermissions(permissions:Array<String>, crossinline block: (Map<String,Boolean>) -> Unit) {
    // Registers a photo picker activity launcher in single-select mode.
    val perms = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        block(it)
    }
    perms.launch(permissions)
}

