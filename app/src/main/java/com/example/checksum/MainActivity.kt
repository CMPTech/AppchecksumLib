package com.example.checksum

import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.Signature
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val packageName = applicationContext.packageName
//        val pm = applicationContext.packageManager
//        val ai = pm.getApplicationInfo(packageName, 0)
//        val srcDir = ai.publicSourceDir
//        checkSignatures(srcDir)
        primary(this)
    }
    private fun primary(context: Context){
        val packageName = context.packageName
        val pm = context.packageManager
        val ai = pm.getApplicationInfo(packageName, 0)
        val srcDir = ai.publicSourceDir
        checkSignatures(srcDir,this)
    }
    private fun checkSignatures(srcDir: String,context: Context ) {
        Log.d("srcDir", srcDir.toString())
        val sig: Signature = context.getPackageManager()
            .getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES).signatures.get(
                0
            )
        val releaseSig: Signature = context.getPackageManager().getPackageArchiveInfo(
            srcDir,
            PackageManager.GET_SIGNATURES
        )!!.signatures[0]

        Log.d("checkSignatures", sig.hashCode().toString() + " , "  +  releaseSig.hashCode().toString())

        if(sig.hashCode().toString() == releaseSig.hashCode().toString() ){
            Log.d("signs", "iffffff")
        } else Log.d("signs", "elseeeeeee")

    }
}