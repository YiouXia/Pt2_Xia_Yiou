package com.example.pt2_xia_yiou;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Temp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        EditText inputValue = findViewById(R.id.input_value);
        RadioGroup groupFrom = findViewById(R.id.group_from);
        RadioGroup groupTo = findViewById(R.id.group_to);
        TextView tvResult = findViewById(R.id.tv_result);
        Button btnConvert = findViewById(R.id.btn_convert);
        Button btnBack = findViewById(R.id.btn_back);

        btnConvert.setOnClickListener(v -> {
            // Compruebo que se haya escrito un número.
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

            int desde = groupFrom.getCheckedRadioButtonId();
            int hasta = groupTo.getCheckedRadioButtonId();
            double resultado = valor;

            // Aplico la fórmula según las unidades seleccionadas.
            if (desde == R.id.radio_first) { // Kelvin
                if (hasta == R.id.radio_second_to) {
                    resultado = valor - 273.15;
                } else if (hasta == R.id.radio_third_to) {
                    resultado = valor * 9 / 5 - 459.67;
                }
            } else if (desde == R.id.radio_second) { // Celsius
                if (hasta == R.id.radio_first_to) {
                    resultado = valor + 273.15;
                } else if (hasta == R.id.radio_third_to) {
                    resultado = valor * 9 / 5 + 32;
                }
            } else if (desde == R.id.radio_third) { // Fahrenheit
                if (hasta == R.id.radio_first_to) {
                    resultado = (valor + 459.67) * 5 / 9;
                } else if (hasta == R.id.radio_second_to) {
                    resultado = (valor - 32) * 5 / 9;
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
