package com.example.imcapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.internal.ViewUtils.getBackgroundColor

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
         lateinit var viewMale: CardView
         lateinit var viewFemale: CardView

        fun initComponents() {
            viewMale = findViewById(R.id.viewMale)
            viewFemale = findViewById(R.id.viewFemale)
        }

        initComponents()
        initListeners()
        initUI(setGenderColor())

        fun initListeners() {
            viewMale.setOnClickListener {
                changeGender()
                setGenderColor()
                viewMale.setCardBackgroundColor()

                viewFemale.setOnClickListener{
                    changeGender()
                    setGenderColor()
                    viewFemale.setCardBackgroundColor()

                }
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

        fun initUI() {

        }
    }
}

