package com.techmedia.pdfconverter

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import com.techmedia.pdfconverter.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            PackageManager.PERMISSION_GRANTED
        )

        binding.saveBtn.setOnClickListener { createPdf() }


    }

    private fun createPdf() {
        // create a new document
        val document = Document()

        val fileName = SimpleDateFormat(
            "dd-MMMM-yyyy_ss",
            Locale.getDefault()
        ).format(System.currentTimeMillis()).toString() + ".pdf"

        val filePath = File(getExternalFilesDir("Notes"), fileName)

        try {
//            Output the file
            PdfWriter.getInstance(document, FileOutputStream(filePath))
//      Open the document to write something inside it
            document.open()

//      Convert the editText text to a String
            val text = binding.pdfEditText.text.toString()

            //Condition
            if (text.isNotEmpty()) {
                document.add(Paragraph(text))
//            Close the document after writing
                document.close()
//            Show Toast message
                Toast.makeText(this, "File saved to $filePath successfully", Toast.LENGTH_LONG)
                    .show()
                binding.pdfEditText.text?.clear()
            } else {
                binding.pdfEditText.error = "Your field cannot be blank"
            }
            binding.pdfEditText.clearFocus()

        } catch (e: Exception) {
            e.printStackTrace()
            //            Show Toast message
            Toast.makeText(this, "" + e.message, Toast.LENGTH_LONG).show()
        }
    }

}