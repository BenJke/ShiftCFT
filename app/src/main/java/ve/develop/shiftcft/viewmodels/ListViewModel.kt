package ve.develop.shiftcft.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ve.develop.shiftcft.Repository
import ve.develop.shiftcft.model.RequestAttempt

class ListViewModel : ViewModel() {

    private val repository = Repository.get()

    private val _attempts: MutableStateFlow<List<RequestAttempt>> = MutableStateFlow(emptyList())
    val attempts: StateFlow<List<RequestAttempt>>
        get() = _attempts.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAttemptList().collect() { currentList ->
                _attempts.value = currentList
            }
        }
    }
}