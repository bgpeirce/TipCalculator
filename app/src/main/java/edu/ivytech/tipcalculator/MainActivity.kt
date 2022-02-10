package edu.ivytech.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import edu.ivytech.tipcalculator.databinding.ActivityMainBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var tipPercent = 0.15
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = resources.getStringArray(R.array.split_array)
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        binding.splitBillDropdown.setAdapter(adapter)
        binding.splitBillDropdown.setText(items[0], false)

        binding.calculateBtn.setOnClickListener { calculateAndDisplay() }
    }

    private fun calculateAndDisplay(){
        val billAmtStr = binding.billAmountEditText.text.toString()
        var billAmount = 0.0
        if (billAmtStr.isNotEmpty()){
            billAmount = billAmtStr!!.toDouble()
        }

        var tipAmount = billAmount * tipPercent
        var totalAmount = billAmount + tipAmount

        val currencyFormat = NumberFormat.getCurrencyInstance()
        val symbol = DecimalFormatSymbols()
        symbol.currencySymbol = ""
        (currencyFormat as DecimalFormat).decimalFormatSymbols = symbol

        binding.tipAmountEditText.setText(currencyFormat.format(tipAmount))
        binding.totalAmountEditText.setText(currencyFormat.format(totalAmount))
    }
}