package com.livioneiva.cursos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livioneiva.cursos.entities.OrderItem;
import com.livioneiva.cursos.entities.pk.OrderItemPK;

public interface OrderItemRepository extends  JpaRepository<OrderItem, OrderItemPK> {

}
