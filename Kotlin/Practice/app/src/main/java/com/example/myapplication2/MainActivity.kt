package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var memo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerBtn.setOnClickListener {
            try {
                val intent = Intent(this, ItemDataActivity::class.java)
                intent.putExtra("data", binding.editMain.text.toString())
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("MainActivity", "Error occurred: ${e.message}")
                e.printStackTrace()
            }
        }


        val create = Toast.makeText(this.applicationContext, "onCreate", Toast.LENGTH_SHORT)
        create.show()
    }

    override fun onPause() {
        super.onPause()
        memo = binding.editMain.text.toString()
//        binding.editMain.text.clear()

        val pause = Toast.makeText(this.applicationContext, "onPause", Toast.LENGTH_SHORT)
        pause.show()
    }

    override fun onRestart() {
        super.onRestart()

        val restart = Toast.makeText(this.applicationContext, "onRestart", Toast.LENGTH_SHORT)
        restart.show()

        MaterialAlertDialogBuilder(this)
            .setMessage("이어서 작성하시겠습니까?")
            .setPositiveButton("예") {dialog, which ->
                binding.editMain.setText(memo)
            }
            .setNegativeButton("아니요") {dialog, which ->
                binding.editMain.text.clear()
                memo = ""
            }
            .create()
            .show()
    }

}