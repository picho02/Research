package com.example.research

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.research.databinding.FragmentFuzzgerminationcountBinding
import com.example.research.db.DBBelice_PR
import java.text.SimpleDateFormat
import java.util.*


class FragmentFuzzGerminationCount : Fragment() {
    private lateinit var binding: FragmentFuzzgerminationcountBinding
    private var posicion = " "


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFuzzgerminationcountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Iniciar el espiner y obtener los datos de un xml de recursos
        val spinnerData = resources.getStringArray(R.array.spinner_fuzzactivity)
        // Adaptador que define la forma en el que se abre el spinner y se le pasan los datos
        val opciones = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            spinnerData
        )
        binding.spFuzzActivity.adapter = opciones
        binding.spFuzzActivity.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p2) {
                    0 -> {
                        posicion = spinnerData[0]
                    }
                    1 -> {
                        posicion = spinnerData[1]
                    }
                    2 -> {
                        posicion = spinnerData[2]
                    }
                    3 -> {
                        posicion = spinnerData[3]
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        // Se obtiene el calendario
        var cal = Calendar.getInstance()
        binding.tvFecha.setText(SimpleDateFormat("dd-MM-yyyy").format(System.currentTimeMillis()))
        // Formato y el item que va abrir
        val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_YEAR, day)

            val myFormat = "dd-MM-yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.tvFecha.setText(sdf.format(cal.time))
        }
        // Definir el EditText y darle un atributo on clic
        binding.ibCalendar.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_YEAR)
            ).show()
        }
        binding.btnGuardar.setOnClickListener {
            with(binding){

                val codigo = etCodigo.text.toString()
                val fecha = tvFecha.text.toString()
                val comentarios = tietComentarios.text.toString()
                val count = etCount.text.toString()
                // se guarda en base de datos
                val db = DBBelice_PR(requireContext())
                db.getFuzzGerminationCountLista()
                db.insertFuzzGerminationCount(codigo,posicion, count.toDouble(),fecha, comentarios)
            }
        }
    }

}