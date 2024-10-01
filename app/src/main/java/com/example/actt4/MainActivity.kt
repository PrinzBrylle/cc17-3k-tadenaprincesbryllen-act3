package com.example.actt4


import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.act4.R
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val costInput = findViewById<EditText>(R.id.InputTip)
        val radioGroupTip = findViewById<RadioGroup>(R.id.radioGroupTip)
        val radioButton20 = findViewById<RadioButton>(R.id.radioButton20)
        val radioButton18 = findViewById<RadioButton>(R.id.radioButton18)
        val radioButton15 = findViewById<RadioButton>(R.id.radioButton15)
        val roundUpSwitch = findViewById<Switch>(R.id.switch1)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val tipAmountView = findViewById<TextView>(R.id.TipAmount)

        calculateButton.setOnClickListener {
            val cost = costInput.text.toString().toDoubleOrNull()

            if (cost == null || cost <= 0) {
                Toast.makeText(this, "Please enter a valid service cost", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tipPercentage = when (radioGroupTip.checkedRadioButtonId) {
                R.id.radioButton20 -> 0.20
                R.id.radioButton18 -> 0.18
                R.id.radioButton15 -> 0.15
                else -> {
                    Toast.makeText(this, "Please select a tip percentage", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            var tipAmount = cost * tipPercentage

            if (roundUpSwitch.isChecked) {
                tipAmount = ceil(tipAmount)
            }

            val totalAmount = cost + tipAmount

            tipAmountView.text = String.format("Tip Amount: $%.2f", tipAmount)

            Toast.makeText(this, "Total Amount: $%.2f".format(totalAmount), Toast.LENGTH_SHORT).show()
        }

    }
}