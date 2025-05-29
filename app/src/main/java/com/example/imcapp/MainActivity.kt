package com.example.imcapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import com.example.imcapp.ui.theme.ImcAppTheme
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImcAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ImcCalculator()
                }
            }
        }
    }
}

@Composable
fun ImcCalculator() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var imc by remember { mutableStateOf(0.0) }
    var classificacao by remember { mutableStateOf("") }
    var mostrarResultado by remember { mutableStateOf(false) }
    var corClassificacao by remember { mutableStateOf(Color.Gray) }

    fun calcularIMC() {
        val pesoFloat = peso.replace(",", ".").toFloatOrNull()
        val alturaFloat = altura.replace(",", ".").toFloatOrNull()

        when {
            peso.isEmpty() || altura.isEmpty() -> {
                Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
            pesoFloat == null || alturaFloat == null -> {
                Toast.makeText(context, "Informe valores numéricos válidos", Toast.LENGTH_SHORT).show()
            }
            pesoFloat <= 0f || pesoFloat > 500f -> {
                Toast.makeText(context, "Peso deve estar entre 1 e 500 kg", Toast.LENGTH_SHORT).show()
            }
            alturaFloat <= 0f || alturaFloat > 3f -> {
                Toast.makeText(context, "Altura deve estar entre 0.1 e 3.0 metros", Toast.LENGTH_SHORT).show()
            }
            else -> {
                val imcCalculado = pesoFloat / (alturaFloat * alturaFloat)
                imc = (round(imcCalculado * 100) / 100.0)

                val resultado = when {
                    imc < 18.5 -> "Abaixo do peso" to Color(0xFF2196F3)
                    imc < 25.0 -> "Peso normal" to Color(0xFF4CAF50)
                    imc < 30.0 -> "Sobrepeso" to Color(0xFFFF9800)
                    imc < 35.0 -> "Obesidade Grau I" to Color(0xFFFF5722)
                    imc < 40.0 -> "Obesidade Grau II" to Color(0xFFF44336)
                    else -> "Obesidade Grau III" to Color(0xFF9C27B0)
                }

                classificacao = resultado.first
                corClassificacao = resultado.second

                mostrarResultado = true
            }
        }
    }

    fun limparCampos() {
        peso = ""
        altura = ""
        mostrarResultado = false
        imc = 0.0
        classificacao = ""
    }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(20.dp)
            .fillMaxWidth()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cabeçalho
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = " Calculadora de IMC",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Índice de Massa Corporal",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Campos de entrada
        OutlinedTextField(
            value = peso,
            onValueChange = { newValue ->
                // Permite apenas números, vírgula e ponto
                if (newValue.all { it.isDigit() || it == '.' || it == ',' }) {
                    peso = newValue
                }
            },
            label = { Text("Peso") },
            placeholder = { Text("Ex: 70.5") },
            suffix = { Text("kg") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        OutlinedTextField(
            value = altura,
            onValueChange = { newValue ->
                // Permite apenas números, vírgula e ponto
                if (newValue.all { it.isDigit() || it == '.' || it == ',' }) {
                    altura = newValue
                }
            },
            label = { Text("Altura") },
            placeholder = { Text("Ex: 1.75") },
            suffix = { Text("m") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        // Botões
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { calcularIMC() },
                modifier = Modifier.weight(1f).height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Calcular IMC",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            OutlinedButton(
                onClick = { limparCampos() },
                modifier = Modifier.weight(0.4f).height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Limpar"
                )
            }
        }

        // Resultado
        if (mostrarResultado) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = corClassificacao.copy(alpha = 0.1f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Seu IMC é",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )

                    Text(
                        text = String.format("%.2f", imc),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = corClassificacao,
                        fontSize = 48.sp
                    )

                    Text(
                        text = classificacao,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = corClassificacao,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        // Tabela de referência
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Informação",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Tabela de Referência",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                val referencias = listOf(
                    Triple("Abaixo de 18,5", "Abaixo do peso", Color(0xFF2196F3)),
                    Triple("18,5 - 24,9", "Peso normal", Color(0xFF4CAF50)),
                    Triple("25,0 - 29,9", "Sobrepeso", Color(0xFFFF9800)),
                    Triple("30,0 - 34,9", "Obesidade Grau I", Color(0xFFFF5722)),
                    Triple("35,0 - 39,9", "Obesidade Grau II", Color(0xFFF44336)),
                    Triple("Acima de 40,0", "Obesidade Grau III", Color(0xFF9C27B0))
                )

                referencias.forEach { (faixa, categoria, cor) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = faixa,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = categoria,
                            style = MaterialTheme.typography.bodyMedium,
                            color = cor,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
        }

        // Nota informativa
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Text(
                text = " O IMC é apenas uma referência. Consulte sempre um profissional de saúde para uma avaliação completa.",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(12.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImcCalculatorPreview() {
    ImcAppTheme {
        ImcCalculator()
    }
}