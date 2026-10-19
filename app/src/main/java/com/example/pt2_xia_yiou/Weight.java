package com.example.pt2_xia_yiou;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Weight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        EditText inputValue = findViewById(R.id.input_value);
        RadioGroup groupFrom = findViewById(R.id.group_from);
        RadioGroup groupTo = findViewById(R.id.group_to);
        TextView tvResult = findViewById(R.id.tv_result);
        Button btnConvert = findViewById(R.id.btn_convert);
        Button btnBack = findViewById(R.id.btn_back);

        btnConvert.setOnClickListener(v -> {
            // Compruebo el valor antes de hacer la conversión.
            String texto = inputValue.getText().toString().trim();
            if (texto.isEmpty()) {
                inputValue.setError(getString(R.string.error_empty));
                return;
            }

            double valor;
            try {
                valor = Double.parseDouble(texto.replace(',', '.'));
            } catch (NumberFormatException e) {
                inputValue.setError(getString(R.string.error_invalid));
                return;
            }

            if (valor < 0) {
                inputValue.setError(getString(R.string.error_negative));
                return;
            }

            int desde = groupFrom.getCheckedRadioButtonId();
            int hasta = groupTo.getCheckedRadioButtonId();
            double resultado = valor;

            // Uso los factores que aparecen en la tabla de la práctica.
            if (desde == R.id.radio_first) { // Kilogramos
                if (hasta == R.id.radio_second_to) {
                    resultado = valor * 2.20462262;
                } else if (hasta == R.id.radio_third_to) {
                    resultado = valor * 35.27396;
                }
            } else if (desde == R.id.radio_second) { // Libras
                if (hasta == R.id.radio_first_to) {
                    resultado = valor * 0.45359237;
                } else if (hasta == R.id.radio_third_to) {
                    resultado = valor * 16;
                }
            } else if (desde == R.id.radio_third) { // Onzas
                if (hasta == R.id.radio_first_to) {
                    resultado = valor * 0.02833;
                } else if (hasta == R.id.radio_second_to) {
                    resultado = valor * 0.0625;
                }
            }

            inputValue.setError(null);
            RadioButton unidadDesde = findViewById(desde);
            RadioButton unidadHasta = findViewById(hasta);
            String valorTexto = String.format("%.2f", valor);
            String resultadoTexto = String.format("%.2f", resultado);
            tvResult.setText(getString(R.string.result_format, valorTexto,
                    unidadDesde.getText(), resultadoTexto, unidadHasta.getText()));
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
