package service.item

import org.springframework.stereotype.Service
import repository.ItemRepository

@Service
class ItemServiceImpl(val userItemRepository: ItemRepository) : ItemService {

    override fun addItem() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteItem() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}