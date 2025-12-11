import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.diatest.model.CartItem

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> = _cartItems

    fun addToCart(item: CartItem) {
        val existingItemIndex = _cartItems.indexOfFirst { it.productId == item.productId }

        if (existingItemIndex != -1) {
            val existingItem = _cartItems[existingItemIndex]
            _cartItems[existingItemIndex] = existingItem.copy(
                quantity = existingItem.quantity + item.quantity
            )
        } else {
            _cartItems.add(item)
        }
    }

    fun removeFromCart(productId: Int) {
        _cartItems.removeAll { it.productId == productId }
    }

    fun updateQuantity(productId: Int, newQuantity: Int) {
        val index = _cartItems.indexOfFirst { it.productId == productId }
        if (index != -1 && newQuantity > 0) {
            val item = _cartItems[index]
            _cartItems[index] = item.copy(quantity = newQuantity)
        }
    }

    fun clearCart() {
        _cartItems.clear()
    }

    fun getTotalCarbohydrates(): Double {
        return _cartItems.sumOf {
            (it.carbohydrates / 100) * it.quantity
        }
    }
}