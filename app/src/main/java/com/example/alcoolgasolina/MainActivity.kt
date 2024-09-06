package com.example.alcoolgasolina

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etPrecoAlcool: EditText
    private lateinit var etPrecoGasolina: EditText
    private lateinit var btnCalcular: Button
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPrecoAlcool = findViewById(R.id.etPrecoAlcool)
        etPrecoGasolina = findViewById(R.id.etPrecoGasolina)
        btnCalcular = findViewById(R.id.btnCalcular)
        tvResultado = findViewById(R.id.tvResultado)

        btnCalcular.setOnClickListener {
            calcularMelhorCombustivel()
        }
    }

    private fun calcularMelhorCombustivel() {
        val precoAlcool = etPrecoAlcool.text.toString().toDoubleOrNull()
        val precoGasolina = etPrecoGasolina.text.toString().toDoubleOrNull()

        if (precoAlcool == null || precoGasolina == null || precoAlcool <= 0 || precoGasolina <= 0) {
            Toast.makeText(this, "Por favor, insira valores válidos e maiores que zero.", Toast.LENGTH_SHORT).show()
            return
        }

        etPrecoAlcool.setBackgroundColor(Color.TRANSPARENT)
        etPrecoGasolina.setBackgroundColor(Color.TRANSPARENT)

        // Comparação combustível é mais caro
        if (precoAlcool > precoGasolina) {
            etPrecoAlcool.setBackgroundColor(Color.parseColor("#006400"))
        } else if (precoGasolina > precoAlcool) {
            etPrecoGasolina.setBackgroundColor(Color.parseColor("#006400"))
        }

        val resultado = precoAlcool / precoGasolina

        if (resultado <= 0.7) {
            tvResultado.text = "É melhor usar Álcool!"
            etPrecoAlcool.requestFocus()
        } else {
            tvResultado.text = "É melhor usar Gasolina!"
            etPrecoGasolina.requestFocus()
        }
    }
}