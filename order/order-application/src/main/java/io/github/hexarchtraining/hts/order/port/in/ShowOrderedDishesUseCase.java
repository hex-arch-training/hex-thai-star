package io.github.hexarchtraining.hts.order.port.in;

import java.util.List;

public interface ShowOrderedDishesUseCase {
    List<OrderedDish> showOrderedDishes(ShowOrderedDishesQuery queryParams);
}
