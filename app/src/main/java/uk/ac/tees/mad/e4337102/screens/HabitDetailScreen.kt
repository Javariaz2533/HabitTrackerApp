package uk.ac.tees.mad.e4337102.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.e4337102.data.HabitEntity
import uk.ac.tees.mad.e4337102.viewmodel.HabitViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitDetailScreen(
    habitId: Long,
    habitViewModel: HabitViewModel,
    onBack: () -> Unit
) {
    var habit by remember { mutableStateOf<HabitEntity?>(null) }
    var name by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(habitId) {
        habitViewModel.loadHabitById(habitId) { loaded ->
            habit = loaded
            name = loaded?.name.orEmpty()
            desc = loaded?.description.orEmpty()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Habit Detail") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (habit == null) {
                Text("Habit not found.")
                OutlinedButton(onClick = onBack) { Text("Back") }
                return@Column
            }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Habit name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = desc,
                onValueChange = { desc = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            if (error != null) {
                Text(text = error!!, color = MaterialTheme.colorScheme.error)
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = onBack) { Text("Back") }

                Button(onClick = {
                    if (name.trim().isEmpty()) {
                        error = "Habit name cannot be empty."
                        return@Button
                    }
                    habitViewModel.updateHabit(
                        id = habitId,
                        name = name,
                        description = desc
                    ) { onBack() }
                }) {
                    Text("Update")
                }
            }

            Spacer(Modifier.height(10.dp))

            Button(
                onClick = {
                    habit?.let { h ->
                        habitViewModel.deleteHabit(h) { onBack() }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Delete")
            }
        }
    }
}
