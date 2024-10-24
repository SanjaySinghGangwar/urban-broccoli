-keep class dev.sanjaygangwar.tempproject.models.** { *; }

# Glide classes
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** { **[] $VALUES; public *; }

# Keep Glide-generated APIs (optional, but recommended for efficiency)
-keep class com.bumptech.glide.GeneratedRequestManagerFactory { *; }

# Keep metadata classes for AppGlideModules (optional, but recommended)
-keep class * extends com.bumptech.glide.annotation.GlideModule

# Retrofit
-keep class com.squareup.retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# OkHttp
-keep class com.squareup.okhttp3.** { *; }

# Gson (customize if needed)
-keep class com.google.gson.** { *; }


# Room
-keep class * extends androidx.room.** { *; }
@Keep # Keep annotation
-keepclassmembers class * extends androidx.datastore.preferences.protobuf.GeneratedMessageLite {
    <fields>;
}

