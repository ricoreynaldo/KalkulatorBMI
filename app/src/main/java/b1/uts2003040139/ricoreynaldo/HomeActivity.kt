package b1.uts2003040139.ricoreynaldo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var beratBadan: EditText
    private lateinit var tinggiBadan: EditText
    private lateinit var buttonHitung: Button
    private lateinit var hasil: TextView
    private lateinit var hasilKedua: TextView
    private lateinit var spinnerJenisKelamin: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        buttonHitung = findViewById(R.id.btnHitung)
        hasil = findViewById(R.id.textviewHasil)
        hasilKedua = findViewById(R.id.textviewHasilKedua)

        val spinner = findViewById<Spinner>(R.id.spinnerJenisKelamin)

        ArrayAdapter.createFromResource(this, R.array.jenis_kelamin, android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(target: AdapterView<*>?, element: View?, position: Int, length: Long) {
                spinnerJenisKelamin = target?.getItemAtPosition(position).toString()
            }
        }

        buttonHitung.setOnClickListener(this)
    }

    override fun onClick(button: View?) {
        beratBadan = findViewById(R.id.beratBadan)
        tinggiBadan = findViewById(R.id.tinggiBadan)

        if(button?.id == R.id.btnHitung) {
            if(beratBadan.text.toString() == "") {
                Toast.makeText(applicationContext, "Silahkan masukkan berat badan!", Toast.LENGTH_LONG).show()
            } else if(tinggiBadan.text.toString() == "") {
                Toast.makeText(applicationContext, "Silahkan masukkan tinggi badan!", Toast.LENGTH_LONG).show()
            } else {
                val newBeratBadan = beratBadan.text.toString().toFloat()
                val newTinggiBadan = tinggiBadan.text.toString().toFloat()
                val tinggiM = newTinggiBadan / 100
                val hasilHitung = newBeratBadan / (tinggiM * tinggiM)

                hasil.text = "BMI anda " + hasilHitung

                if (spinnerJenisKelamin == "Pria") {
                    if (hasilHitung < 17) {
                        hasilKedua.text = "Anda masuk kategori KURUS"
                    } else if (hasilHitung in 17.0..23.0) {
                        hasilKedua.text = "Anda masuk kategori NORMAL"
                    } else if (hasilHitung in 23.0..27.0) {
                        hasilKedua.text = "Anda masuk kategori KEGEMUKAN"
                    } else {
                        hasilKedua.text = "Anda masuk kategori OBESITAS"
                    }
                } else {
                    if (hasilHitung < 18) {
                        hasilKedua.text = "Anda masuk kategori KURUS"
                    } else if (hasilHitung in 18.0..25.0) {
                        hasilKedua.text = "Anda masuk kategori NORMAL"
                    } else if (hasilHitung in 25.0..27.0) {
                        hasilKedua.text = "Anda masuk kategori KEGEMUKAN"
                    } else {
                        hasilKedua.text = "Anda masuk kategori OBESITAS"
                    }
                }
            }
        }
    }
}