package com.example.demo.service;

import com.example.demo.model.MenuItem;

import java.util.List;
import java.util.logging.Logger;

public class MailContentService {

    private final List<MenuItem> orderedMenuItems;
    private final double price;
    private final String specialDetails;
    private final String address;

    private final static Logger LOGGER = Logger.getLogger(MailContentService.class.getName());

    public MailContentService(List<MenuItem> orderedMenuItems, double price, String specialDetails, String address) {
        this.orderedMenuItems = orderedMenuItems;
        this.price = price;
        this.specialDetails = specialDetails;
        this.address = address;
    }

    /**
     * Method for generating mail content
     * @return string containing a table of menu items and the footer of the mail
     */
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
        LOGGER.info("Mail Footer created");
        return stringBuilder.toString();
    }

    /**
     * Method for generating mail header
     * @return the head of the table
     */
    private StringBuilder generateCommonHtmlHead() {
        LOGGER.info("Create mail header");
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

    /**
     * Method to generate the common footer
     * @param stringBuilder extra details for delivery
     */
    private void generateCommonFooter(StringBuilder stringBuilder) {
        stringBuilder.append("</table></body>");
        stringBuilder.append("<p> Total price: ").append(price).append("</p>");
        stringBuilder.append("<p> Address: ").append(address).append("</p>");
        stringBuilder.append("<p> Special Details: ").append(specialDetails).append("</p>");
    }

}
