package com.example.checksumlib

import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.util.Log

object Checksumlib{

     fun primary(context: Context): Boolean {
        val packageName = context.packageName
        val pm = context.packageManager
        val ai = pm.getApplicationInfo(packageName, 0)
        val srcDir = ai.publicSourceDir
        return checkSignatures(srcDir,context)
    }

     fun checkSignatures(srcDir: String, context: Context): Boolean {
        Log.d("srcDir", srcDir.toString())
        val signature = getSignature(context);
        val releaseSignature = getReleasedSignature(srcDir, context)
        return compareSignature(signature, releaseSignature)
    }


     fun getSignature(context: Context): Signature {
        val sig: Signature = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES).signatures.get(0)
        return sig
    }

     fun getReleasedSignature(srcDir: String,context: Context): Signature {
        val releaseSig: Signature = context.getPackageManager().getPackageArchiveInfo(srcDir, PackageManager.GET_SIGNATURES)!!.signatures[0]
        return releaseSig
    }

    fun compareSignature(sig: Signature, releaseSig: Signature): Boolean {
        if(sig.hashCode().toString() == releaseSig.hashCode().toString() ){
            Log.d("signs", "true")
            return true
        } else return false
    }
}