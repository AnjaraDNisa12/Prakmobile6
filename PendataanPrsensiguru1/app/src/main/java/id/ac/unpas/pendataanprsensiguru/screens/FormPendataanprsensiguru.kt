package id.ac.unpas.pendataanprsensiguru.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import id.ac.unpas.pendataanprsensiguru.model.Dataguru
import id.ac.unpas.pendataanprsensiguru.persistences.DataguruDao
import id.ac.unpas.pendataanprsensiguru.ui.theme.Purple700
import id.ac.unpas.pendataanprsensiguru.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormPendataanprsensiguru(dataguruDao: DataguruDao) {
    val scope = rememberCoroutineScope()
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val nim = remember { mutableStateOf(TextFieldValue("")) }
    val matapelajaran = remember { mutableStateOf(TextFieldValue("")) }
    val absen = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            label = { Text(text = "nama") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "cth: linda nurmalinda, S.Ag.") })

        OutlinedTextField(label = { Text(text = "nim") },
            value = nim.value,
            onValueChange = {
                nim.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "cth: 193040006") })

        OutlinedTextField(label = { Text(text = "matapelajaran") },
            value = matapelajaran.value, onValueChange = {
                matapelajaran.value = it
            }, modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            placeholder = { Text(text = "cth: guru agama isalam") })

        OutlinedTextField(label = { Text(text = "absen") },
            value = absen.value, onValueChange = {
                absen.value = it
            }, modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            placeholder = { Text(text = "cth: hadir") })

        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700, contentColor = Teal200
        )

        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200, contentColor = Purple700
        )
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 0.dp, vertical = 5.dp)) {
                Button(onClick = {
                    val id = uuid4().toString()
                    val item = Dataguru(id, nama.value.text, nim.value.text,
                        matapelajaran.value.text, absen.value.text)
                    scope.launch {
                        dataguruDao.insertAll(item)
                    }
                    nama.value = TextFieldValue("")
                    nim.value = TextFieldValue("")
                    matapelajaran.value = TextFieldValue("")
                    absen.value = TextFieldValue("")
                }, colors = loginButtonColors) {
                    Text(
                        text = "Simpan", style = TextStyle(
                            color = Color.White, fontSize = 18.sp
                        ), modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Column(modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()) {
                Button(onClick = {
                    nama.value = TextFieldValue("")
                    nim.value = TextFieldValue("")
                    matapelajaran.value = TextFieldValue("")
                    absen.value = TextFieldValue("")
                }, colors = resetButtonColors) {
                    Text(
                        text = "Reset", style = TextStyle(
                            color = Color.White, fontSize = 18.sp
                        ), modifier = Modifier.padding(8.dp)
                    )
                }
            }


        }
    }
}