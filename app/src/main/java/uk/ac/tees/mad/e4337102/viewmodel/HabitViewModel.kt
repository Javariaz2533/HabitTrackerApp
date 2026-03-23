package uk.ac.tees.mad.e4337102.viewmodel

import android.app.Application
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uk.ac.tees.mad.e4337102.data.DatabaseProvider
import uk.ac.tees.mad.e4337102.data.HabitEntity
import uk.ac.tees.mad.e4337102.network.NetworkProvider
import uk.ac.tees.mad.e4337102.repository.HabitRepository

data class SyncUiState(
    val isLoading: Boolean = false,
    val message: String? = null
)

class HabitViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: HabitRepository

    val habits: StateFlow<List<HabitEntity>>

    private val _syncState = MutableStateFlow(SyncUiState())
    val syncState: StateFlow<SyncUiState> = _syncState.asStateFlow()

    init {
        val dao = DatabaseProvider.get(application).habitDao()
        val api = NetworkProvider.api()
        repo = HabitRepository(dao, api)

        habits = repo.observeHabits()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    }

    private fun isOnline(): Boolean {
        val cm = getApplication<Application>()
            .getSystemService(ConnectivityManager::class.java)
        val net = cm.activeNetwork ?: return false
        val caps = cm.getNetworkCapabilities(net) ?: return false
        return caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    fun syncHabits() {
        if (!isOnline()) {
            _syncState.value = SyncUiState(isLoading = false, message = "Offline: showing cached habits")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            _syncState.value = SyncUiState(isLoading = true, message = null)
            try {
                repo.syncFromServer()
                _syncState.value = SyncUiState(isLoading = false, message = "Synced from server ✅")
            } catch (e: Exception) {
                _syncState.value = SyncUiState(isLoading = false, message = "Sync failed: ${e.message}")
            }
        }
    }

    fun addHabit(name: String, description: String, onDone: (() -> Unit)? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addHabit(name, description)
            onDone?.let { viewModelScope.launch { it() } }
        }
    }

    fun updateHabit(id: Long, name: String, description: String, onDone: (() -> Unit)? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateHabit(id, name, description)
            onDone?.let { viewModelScope.launch { it() } }
        }
    }

    fun deleteHabit(habit: HabitEntity, onDone: (() -> Unit)? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteHabit(habit)
            onDone?.let { viewModelScope.launch { it() } }
        }
    }

    fun loadHabitById(id: Long, onResult: (HabitEntity?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val habit = repo.getHabit(id)
            viewModelScope.launch { onResult(habit) }
        }
    }
}
