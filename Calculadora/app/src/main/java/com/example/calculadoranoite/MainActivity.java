package com.example.calculadoranoite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    EditText edtPrimeiroNumero;
    EditText edtSegundoNumero;
    Button btnSomar;
    Button btnSubtrair;
    Button btnMultiplicar;
    Button btnDividir;
    TextView txvResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        configuracao();
        eventos();
    }

    public void configuracao()
    {
        edtPrimeiroNumero = findViewById(R.id.edtPrimeiroNumero);
        edtSegundoNumero = findViewById(R.id.edtSegundonumero);
        btnSomar = findViewById(R.id.btnSomar);
        btnSubtrair = findViewById(R.id.btnSubtrair);
        btnMultiplicar = findViewById(R.id.btnMultiplicar);
        btnDividir = findViewById(R.id.btnDividir);
        txvResultado = findViewById(R.id.txvResultado);
    }

    public void eventos()
    {
        btnSomar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Double n1 = 0.0;
                Double n2 = 0.0;
                Double resultado = 0.0;
                n1 = Double.parseDouble(edtPrimeiroNumero.getText().toString());
                n2 = Double.parseDouble(edtSegundoNumero.getText().toString());
                resultado = n1 + n2;
                txvResultado.setText(resultado.toString());
            }
        });
    }
}