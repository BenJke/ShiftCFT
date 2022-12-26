package ve.develop.shiftcft.viewmodels

import androidx.lifecycle.ViewModel
import ve.develop.shiftcft.Repository
import ve.develop.shiftcft.model.CardDetail
import ve.develop.shiftcft.model.RequestAttempt

class RequestViewModel() : ViewModel() {

    private val repository: Repository = Repository.get()

    var cardDetail: CardDetail? = null

    suspend fun fetchCardDetails(cardNumber: String): CardDetail =
        repository.fetchCardDetails(cardNumber)

    suspend fun saveToHistory(requestAttempt: RequestAttempt) = repository.addAttempt(requestAttempt)
}