package mx.tecnm.tepic.ladm_u1_practica2_archivosmemoria

import android.R.id
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        atras.setOnClickListener{
            /*val intent = Intent(this, MainActivity ::class.java)
            startActivity(intent)*/
            finish()
        }
        val botonGuardar = findViewById<Button>(R.id.botonG)

        botonGuardar.setOnClickListener {
            val opcion = findViewById<Spinner>(R.id.opcion)
            var op = opcion.selectedItemPosition

            when(op){
                0->{
                    guardarEnArchivoInterno()
                    contenido.setText("")
                    Toast.makeText(this,"SE GUARDO CON EXITO", Toast.LENGTH_LONG).show()
                }
                1->{
                    guardarEnArchivoExterno()
                }
            }
        }
    }

    private fun guardarEnArchivoInterno() {
        try {
            val archivo = OutputStreamWriter(openFileOutput("${titulo.text}.txt", MODE_PRIVATE))
            var dataContenido = findViewById<EditText>(R.id.contenido).text.toString()
            archivo.write(dataContenido)
            archivo.flush()
            archivo.close()
        }catch (io:IOException){
            AlertDialog.Builder(this)
                .setTitle("ATENCIÓN, ERROR")
                .setMessage(io.message)
                .setPositiveButton("ACEPTAR"){dialog,exception->
                    dialog.dismiss();
                }.show()
        }
    }

    private fun guardarEnArchivoExterno() {
        try {
            val tarjeta = getExternalFilesDir(null);
            val file = File(tarjeta?.getAbsolutePath(), titulo.text.toString())
            val osw = OutputStreamWriter(FileOutputStream(file))
            osw.write(contenido.text.toString())

            osw.flush()
            osw.close()
            Toast.makeText(this, "Se guardó correctamente", Toast.LENGTH_SHORT).show()
            titulo.setText("")
            contenido.setText("")
        } catch (ioe: IOException) {
            Toast.makeText(this, "No se pudo guardar", Toast.LENGTH_SHORT).show()
        }
    }
}