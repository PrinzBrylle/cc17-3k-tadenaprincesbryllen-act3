package com.example.actt4

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enables edge-to-edge display (optional)
        setContentView(R.layout.activity_main)

        // Initialize views from the XML layout
        val costInput = findViewById<EditText>(R.id.InputTip)
        val radioGroupTip = findViewById<RadioGroup>(R.id.radioGroupTip)
        val roundUpSwitch = findViewById<Switch>(R.id.switch1)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val tipAmountView = findViewById<TextView>(R.id.TipAmount)

        // Set an onClickListener for the calculate button
        calculateButton.setOnClickListener {
            // Get the cost from the input field and ensure it's a valid number
            val cost = costInput.text.toString().toDoubleOrNull()

            // Validate the cost input
            if (cost == null || cost <= 0) {
                Toast.makeText(this, "Please enter a valid service cost", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Determine the selected tip percentage from the radio buttons
            val tipPercentage = when (radioGroupTip.checkedRadioButtonId) {
                R.id.radioButton20 -> 0.20  // 20% tip
                R.id.radioButton18 -> 0.18  // 18% tip
                R.id.radioButton15 -> 0.15  // 15% tip
                else -> {
                    // If no radio button is selected, show a toast message
                    Toast.makeText(this, "Please select a tip percentage", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            // Calculate the initial tip amount
            var tipAmount = cost * tipPercentage

            // Round up the tip if the switch is checked
            if (roundUpSwitch.isChecked) {
                tipAmount = ceil(tipAmount) // Round up to the nearest whole number
            }

            // Calculate the total amount including the tip
            val totalAmount = cost + tipAmount

            // Update the UI with the calculated tip
            tipAmountView.text = String.format("Tip Amount: $%.2f", tipAmount)

            // Show the total amount in a Toast message
            Toast.makeText(this, "Total Amount: $%.2f".format(totalAmount), Toast.LENGTH_SHORT).show()
        }
    }
}
