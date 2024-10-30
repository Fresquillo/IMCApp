package com.example.imcapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat

lateinit var tvImcResult: TextView
lateinit var tvImc: TextView
lateinit var tvDescription: TextView
lateinit var btnRecalcular: AppCompatButton


class ImcResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponent()
        initListeners()
        initUI()
    }

    fun initComponent(){
        tvImcResult = findViewById(R.id.tvImcResult)
        tvImc = findViewById(R.id.tvImc)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalcular = findViewById(R.id.btnRecalcular)


    }

    fun initListeners(){
        btnRecalcular.setOnClickListener {
            navigateToResult()

        }
    }

    fun initUI(){
        val df = DecimalFormat("#.##")
        val result: Double = intent.extras?.getDouble("IMC_RESULT") ?: -1.0
        tvImc.text = df.format(result)

        tvDescription.text = when {
            result < 18.5 -> getString(R.string.pinferiordesc)
            result in 18.5..24.9 -> getString(R.string.normaldesc)
            result in 25.0..29.9 -> getString(R.string.psuperiordesc)
            else -> getString(R.string.obesidaddesc)
            }

        tvImcResult.text = when {
            result < 18.5 -> getString(R.string.pinferior)
            result in 18.5..24.9 -> getString(R.string.normal)
            result in 25.0..29.9 -> getString(R.string.psuperior)
            else -> getString(R.string.obesidad)
        }
    }

    fun navigateToResult(){
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)

    }

}