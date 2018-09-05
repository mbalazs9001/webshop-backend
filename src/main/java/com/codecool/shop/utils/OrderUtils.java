package com.codecool.shop.utils;

import com.codecool.shop.dao.AddressDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.AddressDaoPSQL;
import com.codecool.shop.dao.implementation.UserDaoPSQL;
import com.codecool.shop.model.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

public class OrderUtils {

    private static AddressDao addressDao = new AddressDaoPSQL();
    private static UserDao userDao = new UserDaoPSQL();

    public static void logOrder(Order order) {
        LocalDateTime currentDT = LocalDateTime.now();
        String filename = "./orders/order" + order.getId() + "_" + currentDT.getYear() + currentDT.getMonthValue()
                + currentDT.getDayOfMonth() + ".json";
        try {
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.println(order);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void sendMail(Order order) {
        String subject = "Information about order number " + order.getId();
        Address address = addressDao.find(order.getUserId());
        User user = userDao.find(order.getUserId());

        String orderDetails = "<h2>Dear " + user.getName() + "!</h2>" +
        "<p>Thank you for the purchase, we have received your order.</p>" +
        "<p>The items will arrive at the following address:<br>" +
        address.getZipcode() + " " + address.getCountry() + ", " + address.getStreet() + "</p>" +
        "<p>Your payment identifier: " + order.getPaymentId() + "</p>" +
        "<h3>Purchased items</h3><table><th>Name</th><th>Unit Price</th><th>Quantity</th>";
        String end = "<br><p>We hope you have a nice day!<br>Team Codeberg</p>";

        StringBuilder sb = new StringBuilder(orderDetails);
        float totalPrice = 0;
        for (ProductOrder productOrder : order.getProductOrders()) {
            Product product = productOrder.getProduct();
            sb.append("<tr>").append("<td>").append(product.getName()).append("</td>")
              .append("<td>").append(product.getDefaultPrice()).append(product.getDefaultCurrency()).append("</td>")
              .append("<td>").append(productOrder.getQuantity()).append("</td>").append("</tr>");
            totalPrice += product.getDefaultPrice() * productOrder.getQuantity();
        }
        sb.append("</table>").append("<br><strong>Total price: ").append(Math.round(totalPrice))
                .append("</strong>").append(end);

        MailSender mailSender = new MailSender(user.getEmail(), subject, sb.toString());
        mailSender.start();
    }
}
