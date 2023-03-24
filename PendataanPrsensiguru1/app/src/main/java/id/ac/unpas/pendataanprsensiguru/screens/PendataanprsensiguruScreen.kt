package id.ac.unpas.pendataanprsensiguru.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import id.ac.unpas.pendataanprsensiguru.model.Dataguru
import id.ac.unpas.pendataanprsensiguru.model.DataSepatu
import id.ac.unpas.pendataanprsensiguru.persistences.AppDatabase

@Composable
fun PendataanprsensiguruScreen() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "pendataan-guru"
    ).build()
    val dataguruDao = db.dataguruDao()

    val list: LiveData<List<Dataguru>> = dataguruDao.loadAll()
    val items: List<Dataguru> by list.observeAsState(initial = listOf())

    Column(modifier = Modifier.fillMaxWidth()) {
        FormPendataanguru(dataguruDao)

        Row(modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()) {

            Column(modifier = Modifier.weight(3f)) {
                Text(text = "nama", fontSize = 14.sp, textAlign = TextAlign.Center)
            }

            Column(modifier = Modifier.weight(3f)) {
                Text(text = "nim", fontSize = 14.sp, textAlign = TextAlign.Center)
            }

            Column(modifier = Modifier.weight(3f)) {
                Text(text = "matapelajaran", fontSize = 14.sp, textAlign = TextAlign.Center)
            }

            Column(modifier = Modifier.weight(3f)) {
                Text(text = "absen", fontSize = 14.sp, textAlign = TextAlign.Center)
            }
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()) {

                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = item.nama, fontSize = 15.sp,
                            fontWeight = FontWeight.Bold)
                    }

                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = item.nim, fontSize = 15.sp, fontWeight =
                        FontWeight.Bold)
                    }

                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = item.matapelajaran, fontSize = 15.sp, fontWeight =
                        FontWeight.Bold)
                    }

                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Rp. ${item.absen}", fontSize = 15.sp,
                            fontWeight = FontWeight.Bold)
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}