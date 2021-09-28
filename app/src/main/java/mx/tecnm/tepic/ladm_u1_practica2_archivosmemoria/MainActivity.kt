package mx.tecnm.tepic.ladm_u1_practica2_archivosmemoria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import android.R.id
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main3.*
import java.io.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1000)
        setTheme(R.style.SplashScreen)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        agregar.setOnClickListener{
            val intent = Intent(this, MainActivity2 ::class.java)
            startActivity(intent)
        }

        nota3.setOnClickListener{
            val notaNombre = EditText(this)
            notaNombre.setHint("Nombre nota")
            AlertDialog.Builder(this)
                .setTitle("ATENCION")
                .setMessage("Ingrese el nombre de la nota a fijar")
                .setView(notaNombre)
                .setPositiveButton("ACEPTAR"){d,i->
                    try{
                        val tarjeta = getExternalFilesDir(null);
                        val file = File(tarjeta?.getAbsolutePath(), notaNombre.text.toString())
                        val fIn = FileInputStream(file)
                        val archivo = InputStreamReader(fIn)
                        val br = BufferedReader(archivo)
                        var linea = br.readLine()
                        val todo = StringBuilder()
                        while (linea != null) {
                            todo.append(linea + "\n")
                            linea = br.readLine()
                        }
                        br.close()
                        archivo.close()
                        nota3.setText(notaNombre.text.toString() + "\n"+todo)
                        d.dismiss()
                    }catch (io:IOException){
                        Toast.makeText(this, "Esta nota no existe", Toast.LENGTH_SHORT).show()
                        d.dismiss()
                    }
                }.show()
        }

        nota2.setOnClickListener{
            val notaNombre = EditText(this)
            notaNombre.setHint("Nombre nota")
            AlertDialog.Builder(this)
                .setTitle("ATENCION")
                .setMessage("Ingrese el nombre de la nota a fijar")
                .setView(notaNombre)
                .setPositiveButton("ACEPTAR"){d,i->
                    try{
                        val tarjeta = getExternalFilesDir(null);
                        val file = File(tarjeta?.getAbsolutePath(), notaNombre.text.toString())
                        val fIn = FileInputStream(file)
                        val archivo = InputStreamReader(fIn)
                        val br = BufferedReader(archivo)
                        var linea = br.readLine()
                        val todo = StringBuilder()
                        while (linea != null) {
                            todo.append(linea + "\n")
                            linea = br.readLine()
                        }
                        br.close()
                        archivo.close()
                        nota2.setText(notaNombre.text.toString() + "\n"+todo)
                        d.dismiss()
                    }catch (io:IOException){
                        Toast.makeText(this, "Esta nota no existe", Toast.LENGTH_SHORT).show()
                        d.dismiss()
                    }
                }.show()
        }
        nota.setOnClickListener{
            val notaNombre = EditText(this)
            notaNombre.setHint("Nombre nota")
            AlertDialog.Builder(this)
                .setTitle("ATENCION")
                .setMessage("Ingrese el nombre de la nota a fijar")
                .setView(notaNombre)
                .setPositiveButton("ACEPTAR"){d,i->
                    try{
                        val tarjeta = getExternalFilesDir(null);
                        val file = File(tarjeta?.getAbsolutePath(), notaNombre.text.toString())
                        val fIn = FileInputStream(file)
                        val archivo = InputStreamReader(fIn)
                        val br = BufferedReader(archivo)
                        var linea = br.readLine()
                        val todo = StringBuilder()
                        while (linea != null) {
                            todo.append(linea + "\n")
                            linea = br.readLine()
                        }
                        br.close()
                        archivo.close()
                        nota.setText(notaNombre.text.toString() + "\n"+todo)
                        d.dismiss()
                    }catch (io:IOException){
                        Toast.makeText(this, "Esta nota no existe", Toast.LENGTH_SHORT).show()
                        d.dismiss()
                    }
                }.show()
        }

        abrir.setOnClickListener {
            val intent = Intent(this, MainActivity3 ::class.java)
            startActivity(intent)
        }

        borrar.setOnClickListener{
            val notaNombre = EditText(this)
            notaNombre.setHint("Nombre nota")
            AlertDialog.Builder(this)
                .setTitle("ATENCION")
                .setMessage("Ingrese el nombre de la nota a borrar")
                .setView(notaNombre)
                .setPositiveButton("BORRAR DE INTERNA"){d,i->
                    try{
                        deleteFile("${notaNombre.text}.txt")
                        Toast.makeText(this, "Se eliminó correctamente", Toast.LENGTH_SHORT).show()
                        d.dismiss()
                    }catch (io:IOException){
                        Toast.makeText(this, "Esta nota no existe", Toast.LENGTH_SHORT).show()
                        d.dismiss()
                    }

                }
                .setNegativeButton("BORRAR DE EXTERNA"){d,i->
                    try{
                        val tarjeta = getExternalFilesDir(null);
                        val file = File(tarjeta?.getAbsolutePath(), notaNombre.text.toString())
                        file.delete()
                        Toast.makeText(this, "Se eliminó correctamente", Toast.LENGTH_SHORT).show()
                        d.dismiss()
                    }catch (io:IOException){
                        Toast.makeText(this, "Esta nota no existe", Toast.LENGTH_SHORT).show()
                        d.dismiss()
                    }

                }.show()
        }
    }


}