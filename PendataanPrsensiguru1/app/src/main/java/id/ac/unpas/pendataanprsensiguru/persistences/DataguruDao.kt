package id.ac.unpas.pendataanprsensiguru.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.pendataanprsensiguru.model.Dataguru

@Dao
interface DataGuruDao {
   @Query("SELECT * FROM Dataguru")
   fun loadAll(): LiveData<List<DataGuruDao>>

   @Query("SELECT * FROM Dataguru WHERE id = :id")
   fun find(id: String): DataGuru?

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertAll(vararg items: DataGuru)

   @Delete
   fun delete(items: Dataguru)
}