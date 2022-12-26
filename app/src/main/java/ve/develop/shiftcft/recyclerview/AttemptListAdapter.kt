package ve.develop.shiftcft.recyclerview

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ve.develop.shiftcft.databinding.ListItemRequestBinding
import ve.develop.shiftcft.model.RequestAttempt

class AttemptHolder(
    private val binding: ListItemRequestBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(attempt: RequestAttempt) {
        binding.statusTextView.text = attempt.status
        binding.timeTextView.text = SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(attempt.time)
    }
}

class AttemptListAdapter(
    private val attempts: List<RequestAttempt>,
) : RecyclerView.Adapter<AttemptHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AttemptHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemRequestBinding.inflate(inflater, parent, false)
        return AttemptHolder(binding)
    }

    override fun onBindViewHolder(holder: AttemptHolder, position: Int) {
        val requestAttempt = attempts[position]
        holder.bind(requestAttempt)
    }

    override fun getItemCount() = attempts.size
}