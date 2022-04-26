package com.example.demo.service;

import com.example.demo.model.MenuItem;

import java.util.List;

public class MailContentService {

    private final List<MenuItem> orderedMenuItems;
    private final double price;
    private final String specialDetails;
    private final String address;

    public MailContentService(List<MenuItem> orderedMenuItems, double price, String specialDetails, String address) {
        this.orderedMenuItems = orderedMenuItems;
        this.price = price;
        this.specialDetails = specialDetails;
        this.address = address;
    }

    public String generateReportMessage() {
        StringBuilder stringBuilder = generateCommonHtmlHead();

        for (MenuItem menuItem: orderedMenuItems) {

            stringBuilder.append("<tr>");
            stringBuilder.append("<td>").append(menuItem.getItemName()).append("</td>");
            stringBuilder.append("<td>").append(menuItem.getDescription()).append("</td>");
            stringBuilder.append("<td>").append(menuItem.getPrice()).append("</td>");
            stringBuilder.append("<td>").append(menuItem.getCategory().getName()).append("</td>");
            stringBuilder.append("</tr>");
        }
        generateCommonFooter(stringBuilder);
        return stringBuilder.toString();
    }


    private StringBuilder generateCommonHtmlHead() {
        StringBuilder stringBuilder = new StringBuilder();

        return stringBuilder.append("<head>")
                .append("<h1>Order Summary<h1>")
                .append("</head>")
                .append("<body>")
                .append("<table border=1>")
                .append("<tr>")
                .append("<th>Item name</th><th>Description</th><th>Price</th><th>Category</th>")
                .append("</tr>");
    }

    private void generateCommonFooter(StringBuilder stringBuilder) {
        stringBuilder.append("</table></body>");
        stringBuilder.append("<p> Total price: ").append(price).append("</p>");
        stringBuilder.append("<p> Address: ").append(address).append("</p>");
        stringBuilder.append("<p> Special Details: ").append(specialDetails).append("</p>");
    }

}
