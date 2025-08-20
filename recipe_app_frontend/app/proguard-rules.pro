# Keep models and Kotlin metadata for kotlinx-serialization (if used later)
-keepclassmembers class kotlinx.serialization.** { *; }
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class **$$serializer { *; }
-keepattributes *Annotation*, InnerClasses, EnclosingMethod

# Keep Application class
-keep class org.example.app.App { *; }

# Keep Activities
-keep class org.example.app.ui.** { *; }
