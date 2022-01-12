package io.github.hexarchtraining.hts.order.service;

import io.github.hexarchtraining.hts.order.port.in.OrderedDish;
import io.github.hexarchtraining.hts.order.port.in.ShowOrderedDishesQuery;
import io.github.hexarchtraining.hts.order.port.in.ShowOrderedDishesUseCase;
import lombok.AllArgsConstructor;


import java.util.List;

@AllArgsConstructor
public class ShowOrderedDishes implements ShowOrderedDishesUseCase {

    @Override
    public List<OrderedDish> showOrderedDishes(ShowOrderedDishesQuery queryParams) {
        throw new UnsupportedOperationException("Implementation missing");
    }
}
