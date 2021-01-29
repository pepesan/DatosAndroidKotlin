package com.cursosdedesarrollo.datosandroidkotlin

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.pm.PackageManager
import android.Manifest.permission
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat
import android.os.Environment.getExternalStorageDirectory
import android.content.Context.MODE_PRIVATE
import android.os.Environment
import android.util.Log
import android.view.View
import java.io.*


class FicherosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ficheros)
    }

    fun crea(v: View) {
        try {
            val fout = OutputStreamWriter(
                    openFileOutput("prueba_int.txt", Context.MODE_PRIVATE))

            fout.write("Texto de prueba.")
            fout.close()
            Toast.makeText(this, "Fichero creado internamente", Toast.LENGTH_LONG).show()
        } catch (ex: Exception) {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna")
        }

    }

    fun lee(v: View) {
        try {
            val fin = BufferedReader(
                    InputStreamReader(
                            openFileInput("prueba_int.txt")))

            val texto = fin.readLine()
            Toast.makeText(this, texto, Toast.LENGTH_LONG).show()
            fin.close()
        } catch (ex: Exception) {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna")
        }

    }

    fun creaExterno(v: View?) {
        try {
            val ruta_sd = Environment.getExternalStorageDirectory()

            val f = File(ruta_sd.getAbsolutePath(), "prueba_sd.txt")

            val fout = OutputStreamWriter(
                    FileOutputStream(f))

            fout.write("Texto de prueba.")
            fout.close()
            Toast.makeText(this, "Fichero creado externo", Toast.LENGTH_LONG).show()
        } catch (ex: Exception) {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD")
        }

    }

    fun leeExterno(v: View) {
        try {
            val ruta_sd = Environment.getExternalStorageDirectory()

            val f = File(ruta_sd.getAbsolutePath(), "prueba_sd.txt")

            val fin = BufferedReader(
                    InputStreamReader(
                            FileInputStream(f)))

            val texto = fin.readLine()
            Toast.makeText(this, texto, Toast.LENGTH_LONG).show()
            fin.close()
        } catch (ex: Exception) {
            Log.e("Ficheros", "Error al leer fichero desde tarjeta SD")
        }

    }

    private val MY_PERMISSIONS_WRITE_FILE = 1
    fun requestAudioPermissions(v: View) {
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            //When permission is not granted by user, show them message why this permission is needed.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "Please grant permissions to save file", Toast.LENGTH_LONG).show()

                //Give user option to still opt-in the permissions
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        MY_PERMISSIONS_WRITE_FILE)

            } else {
                // Show user dialog to grant permission to record audio
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        MY_PERMISSIONS_WRITE_FILE)
            }
        } else if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            //Go ahead with recording audio now
            Toast.makeText(this, "Permissions granted to record audio", Toast.LENGTH_LONG).show()
            creaExterno(null)
        }//If permission is granted, then go ahead recording audio
    }

    //Handling callback
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_WRITE_FILE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay!
                    creaExterno(null)
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permissions Denied to save file", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }
}
