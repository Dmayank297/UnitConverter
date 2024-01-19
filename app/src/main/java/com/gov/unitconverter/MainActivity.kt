package com.gov.unitconverter

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gov.unitconverter.ui.theme.UnitConverterTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color
                // from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf("Meters")}
    var iExpanded by remember { mutableStateOf(false)}
    var oExpanded by remember { mutableStateOf(false)}
    val conversionFactor = remember { mutableStateOf(1.00)}
    val oConversionFactor = remember { mutableStateOf(1.00)}


    fun convertUnits(){
        // ?: - elvis operator
        val inputValueDouble =  inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.00/oConversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter",
            style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
        },
            label = { Text(text = "Enter value")})

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            // Input Box
            Box {
                //Input Button
                Button(onClick = { iExpanded = true}) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "" )
                }
                DropdownMenu(expanded = iExpanded,
                    onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                             // set to centimeters
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()}
                    )
                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {

                            // set to meters
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits() }
                    )
                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            // set to feet
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits() }
                    )
                    DropdownMenuItem(
                        text = {Text("Inches")},
                        onClick = {
                            // set to Inches
                            iExpanded = false
                            inputUnit = "Inches"
                            conversionFactor.value = 0.0254
                            convertUnits() }
                    )
                    DropdownMenuItem(
                        text = {Text("Millimeters")},
                        onClick = {
                            // set to millimeters
                            iExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor.value = 0.001
                            convertUnits() }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Output Box
            Box {
                // Output Button
                Button(onClick = { oExpanded = true }) {

                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "" )

                    DropdownMenu(expanded = oExpanded,
                        onDismissRequest = { oExpanded = false }) {
                        DropdownMenuItem(
                            text = { Text("Centimeters") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Centimeters"
                                oConversionFactor.value = 0.01
                                convertUnits()}
                        )
                        DropdownMenuItem(
                            text = { Text("Meters") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Meters"
                                oConversionFactor.value = 1.0
                                convertUnits()}
                        )
                        DropdownMenuItem(
                            text = { Text("Feet") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Feet"
                                oConversionFactor.value = 0.3048
                                convertUnits()}
                        )
                        DropdownMenuItem(
                            text = { Text("Inches") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Inches"
                                oConversionFactor.value = 0.0254
                                convertUnits()}
                        )
                        DropdownMenuItem(
                            text = { Text("Millimeters") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Millimetres"
                                oConversionFactor.value = 0.001
                                convertUnits()}
                        )
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue " +
                " $outputUnit", style = MaterialTheme.typography.headlineSmall)
    }
}




@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
    Column {
        Row {

        }
    }
}