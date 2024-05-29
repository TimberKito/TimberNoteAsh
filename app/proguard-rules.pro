# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keepclassmembers class * {
  @androidx.room.Query <methods>;
}

-keepclassmembers class com.sunling.softapp.timbernoteash.tools.AppConstString{
  public static final java.lang.String DATABASE_NAME;
  public static final int DATABASE_VERSION;
}

-keep class com.sunling.softapp.timbernoteash.db.NotesDatabase { *; }
-keep class com.sunling.softapp.timbernoteash.dao.NoteDao { *; }
-keep class com.sunling.softapp.timbernoteash.entity.Note { *; }
