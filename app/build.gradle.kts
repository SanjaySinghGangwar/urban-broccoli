import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

plugins {
    id("com.android.application")
    kotlin("kapt")
    kotlin("android")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    id ("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "dev.sanjaygangwar.tempproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.sanjaygangwar.tempproject"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

    configure<BaseAppModuleExtension> {
        signingConfigs {
            create("release") {  // Or use getByName("release") if it already exists
                storeFile = file("key")
                storePassword = project.properties["APP_KEYSTORE_PASSWORD"]?.toString()
                keyAlias = project.properties["APP_ALIAS"]?.toString()
                keyPassword = project.properties["APP_KEY_PASSWORD"]?.toString()
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("com.google.android.play:review-ktx:2.0.1")
    implementation("com.google.firebase:firebase-messaging-ktx:23.4.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    //Gson
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Room
    implementation("androidx.room:room-runtime:2.4.1")
    implementation("androidx.room:room-ktx:2.4.1")
    kapt("androidx.room:room-compiler:2.4.1")


    //
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    //lottie

    implementation("com.airbnb.android:lottie:5.2.0")

    //glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")
    implementation ("jp.wasabeef:glide-transformations:4.3.0")


    //in app update
    implementation ("com.google.android.play:app-update-ktx:2.1.0")

    //admob
    implementation ("com.google.android.gms:play-services-ads:22.6.0")

    //facebook ads network
    implementation ("androidx.annotation:annotation:1.7.1")
    implementation ("com.facebook.android:audience-network-sdk:6.14.0")

    //proto
    implementation("androidx.datastore:datastore-core:1.0.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

}

kapt {
    correctErrorTypes = true
}