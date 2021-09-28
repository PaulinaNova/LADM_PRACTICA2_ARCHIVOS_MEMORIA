package mx.tecnm.tepic.ladm_u1_practica2_archivosmemoria


import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.activity_main3.titulo
import java.io.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        atras2.setOnClickListener{
            /*val intent = Intent(this, MainActivity ::class.java)
            startActivity(intent)*/
            finish()
        }
        botonA.setOnClickListener{
            val opcion = findViewById<Spinner>(R.id.opcionL)
            var op = opcion.selectedItemPosition
            when(op){
                0->{
                    leerDesdeArchivoInterno()
                }
                1->{
                    leerDesdeArchivoExterno()
                }
            }
        }
    }

    private fun leerDesdeArchivoExterno() {
        val tarjeta = getExternalFilesDir(null);
        val file = File(tarjeta?.absolutePath, archivo2.text.toString())
        try {
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
            titulo.setText(archivo2.text.toString())
            contenidoL.text = todo
        } catch (e: IOException) {
            Toast.makeText(this, "No se pudo leer", Toast.LENGTH_SHORT).show()
        }
    }

    private fun leerDesdeArchivoInterno() {
        try{
            val archivo = BufferedReader(InputStreamReader(openFileInput("${archivo2.text}.txt")))
            findViewById<TextView>(R.id.contenidoL).text = archivo.readLine()
            titulo.setText("${archivo2.text}")
            archivo.close()
        }catch (io: IOException){
            AlertDialog.Builder(this)
                .setTitle("ERROR")
                .setMessage(io.message)
                .setNegativeButton("ACEPTAR"){dialog,i->
                    dialog.cancel()
                }.show()
        }
    }
}