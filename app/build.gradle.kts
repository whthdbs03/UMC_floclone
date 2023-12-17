plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.flo_clone"
    compileSdk = 34 //얘도 33에서 34로 바꿪무
//뷰바인딩 이것만 쓴것임... 씽크나우해주고 depadencies 도씀 이건 viewpager2쓰려고
    viewBinding {
        enable = true
    }

    dependencies {
        implementation("androidx.viewpager2:viewpager2:1.0.0")
    }

    defaultConfig {
        applicationId = "com.example.flo_clone"
        minSdk = 24
        targetSdk = 34 //33에서 34로바ㅜ꺼줌
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    val navversion = "2.7.3"

    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$navversion")
    implementation("androidx.navigation:navigation-ui-ktx:$navversion")
}