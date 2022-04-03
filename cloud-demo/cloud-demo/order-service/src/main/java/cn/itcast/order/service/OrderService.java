package cn.itcast.order.service;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);

        //2.查询用户
        /*
        老办法
        String url="http://userservice/user/"+order.getUserId();
        User user =restTemplate.getForObject(url,User.class);
        */

        //新办法
        User user=userClient.findById(order.getUserId());

        //3.封装user对象
        order.setUser(user);

        // 4.返回
        return order;
    }
}
