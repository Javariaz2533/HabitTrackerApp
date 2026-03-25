package uk.ac.tees.mad.e4337102.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.e4337102.data.HabitDao
import uk.ac.tees.mad.e4337102.data.HabitEntity
import uk.ac.tees.mad.e4337102.network.HabitApiService

class HabitRepository(
    private val dao: HabitDao,
    private val api: HabitApiService
) {
    fun observeHabits(): Flow<List<HabitEntity>> = dao.observeAllHabits()

    suspend fun getHabit(id: Long): HabitEntity? = dao.getHabitById(id)

    suspend fun addHabit(name: String, description: String): Long {
        return dao.insertHabit(HabitEntity(name = name.trim(), description = description.trim()))
    }

    suspend fun updateHabit(id: Long, name: String, description: String) {
        dao.updateHabit(HabitEntity(id = id, name = name.trim(), description = description.trim()))
    }

    suspend fun deleteHabit(habit: HabitEntity) = dao.deleteHabit(habit)


    suspend fun syncFromServer() {
        val remote = api.getHabits()


        remote.forEach { dto ->
            dao.insertHabit(
                HabitEntity(
                    id = dto.id,  // keep server id
                    name = dto.name,
                    description = dto.description
                )
            )
        }
    }
}
