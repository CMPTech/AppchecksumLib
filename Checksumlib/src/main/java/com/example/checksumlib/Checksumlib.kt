package com.example.checksumlib

import android.content.pm.PackageManager
import android.content.pm.Signature
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

object Checksumlib : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val packageName = applicationContext.packageName
        val pm = applicationContext.packageManager
        val ai = pm.getApplicationInfo(packageName, 0)
        val srcDir = ai.publicSourceDir
        checkSignatures(srcDir)
    }

     fun checkSignatures(srcDir: String) {
        Log.d("srcDir", srcDir.toString())
        val sig: Signature = applicationContext.getPackageManager()
            .getPackageInfo(applicationContext.getPackageName(), PackageManager.GET_SIGNATURES).signatures.get(
                0
            )
        val releaseSig: Signature = applicationContext.getPackageManager().getPackageArchiveInfo(
            srcDir,
            PackageManager.GET_SIGNATURES
        )!!.signatures[0]

        Log.d("checkSignatures", sig.hashCode().toString() + " , "  +  releaseSig.hashCode().toString())

        if(sig.hashCode().toString() == releaseSig.hashCode().toString() ){
            Log.d("signs", "iffffff")
        } else Log.d("signs", "elseeeeeee")

    }
}