package com.example.checksumlib

import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.util.Log

object Checksumlib{
    @Volatile
    lateinit var appContext: Context
    val packageName = appContext.packageName
    val pm = appContext.packageManager
    val ai = pm.getApplicationInfo(packageName, 0)
    val srcDir = ai.publicSourceDir

    fun primary(){
        checkSignatures(srcDir)
    }

     fun checkSignatures(srcDir: String) {
        Log.d("srcDir", srcDir.toString())
        val sig: Signature = appContext.getPackageManager()
            .getPackageInfo(appContext.getPackageName(), PackageManager.GET_SIGNATURES).signatures.get(
                0
            )
        val releaseSig: Signature = appContext.getPackageManager().getPackageArchiveInfo(
            srcDir,
            PackageManager.GET_SIGNATURES
        )!!.signatures[0]

        Log.d("checkSignatures", sig.hashCode().toString() + " , "  +  releaseSig.hashCode().toString())

        if(sig.hashCode().toString() == releaseSig.hashCode().toString() ){
            Log.d("signs", "iffffff")
        } else Log.d("signs", "elseeeeeee")

    }
}