package com.example.imcapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.internal.ViewUtils
import com.google.android.material.internal.ViewUtils.getBackgroundColor
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

lateinit var viewMale: CardView
lateinit var viewFemale: CardView
lateinit var tvHeight: TextView
lateinit var rsHeight: RangeSlider
lateinit var viewPeso: CardView
lateinit var viewEdad: CardView
lateinit var btnSubtractWeight: FloatingActionButton
lateinit var btnAdditionWeight: FloatingActionButton
lateinit var btnSubtractAge: FloatingActionButton
lateinit var btnAdditionAge: FloatingActionButton
lateinit var tvWeight: TextView
lateinit var tvAge: TextView
lateinit var btnCalc: AppCompatButton
var weight: Int = 50
var age: Int = 0
var isMaleSelected = true


class ImcCalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
        initUI()

        rsHeight.addOnChangeListener { _, value, _ ->
            //tvHeight.text = value.toString()
            tvHeight.text = DecimalFormat("#.##").format(value) + " cm"
        }
    }

    fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(!isMaleSelected))
    }

    fun getBackgroundColor(isComponentSelected: Boolean): Int {
        val colorReference = if (isComponentSelected) {
            R.color.bg_comp_sel
        } else {
            R.color.bg_comp
        }
        return ContextCompat.getColor(this, colorReference)
    }

    fun initListeners() {
        viewMale.setOnClickListener {
            isMaleSelected = false
            setGenderColor()
        }


        viewFemale.setOnClickListener {
            isMaleSelected = false
            setGenderColor()

        }


        btnSubtractWeight.setOnClickListener {
            weight--
            setWeight()


        }

        btnAdditionWeight.setOnClickListener {
            weight++
            setWeight()
        }

        btnSubtractAge.setOnClickListener {
            age--
            setAge()
        }


        btnAdditionAge.setOnClickListener {
            age++
            setAge()
        }

        btnCalc.setOnClickListener {
            val imc = calculateIMC()
            navigateToResult(imc)
        }
    }


    fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        viewPeso = findViewById(R.id.viewPeso)
        viewEdad = findViewById(R.id.viewEdad)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnAdditionWeight = findViewById(R.id.btnAdditionWeight)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnAdditionAge = findViewById(R.id.btnAdditionAge)
        tvWeight = findViewById(R.id.tvWeight)
        tvAge = findViewById(R.id.tvAge)
        btnCalc = findViewById(R.id.btnCalc)
    }

    fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }

    fun setWeight(){
        tvWeight.text = weight.toString()


    }

    fun setAge(){
        tvAge.text = age.toString()

    }

    fun calculateIMC():Double{
        val heighInMeters = rsHeight.values[0] / 100
        return weight / Math.pow(heighInMeters.toDouble(), 2.0)
    }

    fun navigateToResult(result: Double){
        val intent = Intent(this, ImcResultActivity::class.java)
        intent.putExtra("IMC_RESULT", result)
        startActivity(intent)
    }

}








