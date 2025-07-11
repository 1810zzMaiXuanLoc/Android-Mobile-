import com.example.maixuanloc.CartItem

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addToCart(item: CartItem) {
        val existing = cartItems.find { it.id == item.id }
        if (existing != null) {
            existing.quantity += item.quantity
        } else {
            cartItems.add(item)
        }
    }

    fun increaseQuantity(itemId: Int) {
        cartItems.find { it.id == itemId }?.apply {
            quantity++
        }
    }

    fun decreaseQuantity(itemId: Int) {
        cartItems.find { it.id == itemId }?.apply {
            if (quantity > 1) {
                quantity--
            } else {
                cartItems.remove(this)
            }
        }
    }

    fun removeItem(itemId: Int) {
        cartItems.removeAll { it.id == itemId }
    }
    fun clearCart() {
        cartItems.clear()
    }

    fun getCartItems(): List<CartItem> = cartItems

    fun getTotalPrice(): Double {
        return cartItems.sumOf { it.price * it.quantity }
    }
}
