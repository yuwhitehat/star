package com.liuziyu.star.entity.streamtest;

import com.liuziyu.star.util.JsonUtil;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * 订单类
 *
 * @author LiuZiyu
 * @date 2023/03/18 18:18
 */
@Data
public class Order {

    private Long id;
    private Long customerId;
    private String customerName;
    private List<OrderItem> orderItemList;
    private Double totalPrice;
    private LocalDateTime placedAt;

    public static List<Order> getData() {
        List<Product> products = Product.getData();
        List<Customer> customers = Customer.getData();

        Random random = new Random();
        return LongStream.rangeClosed(1, 10).mapToObj(i ->
        {
            Order order = new Order();
            order.setId(i);
            order.setPlacedAt(LocalDateTime.now().minusHours(random.nextInt(24 * 365)));

            order.setOrderItemList(IntStream.rangeClosed(1, random.ints(1, 1, 8).findFirst().getAsInt()).mapToObj(j ->
            {
                OrderItem orderItem = new OrderItem();
                Product product = products.get(random.nextInt(products.size()));
                orderItem.setProductId(product.getId());
                orderItem.setProductName(product.getName());
                orderItem.setProductPrice(product.getPrice());
                orderItem.setProductQuantity(random.ints(1, 1, 5).findFirst().getAsInt());
                return orderItem;
            }).collect(Collectors.toList()));
            order.setTotalPrice(order.getOrderItemList().stream().mapToDouble(item -> item.getProductPrice() * item.getProductQuantity()).sum());

            Customer customer = customers.get(random.nextInt(customers.size()));
            order.setCustomerId(customer.getId());
            order.setCustomerName(customer.getName());

            return order;
        }).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(JsonUtil.toString(Order.getData()));
    }
}
