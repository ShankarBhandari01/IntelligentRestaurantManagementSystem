package com.shankar.intelligentrestaurantmanagementsystem.service.impl;

import com.shankar.intelligentrestaurantmanagementsystem.dto.KotItemStatus;
import com.shankar.intelligentrestaurantmanagementsystem.dto.KotStatus;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Kot;
import com.shankar.intelligentrestaurantmanagementsystem.entity.KotItem;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Station;
import com.shankar.intelligentrestaurantmanagementsystem.service.KotPrinterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KotPrinterServiceImpl implements KotPrinterService {

    @Override
    public void printKot(Kot kot) {
        List<KotItem> items = kot.getItems().stream()
                .filter(i -> i.getStatus() == KotItemStatus.NEW)
                .toList();

        printKotItems(items, kot.getStation());

        // After printing, update KOT status
        kot.setStatus(KotStatus.PRINTED);
        // save kot if needed
    }

    @Override
    public void printKotItems(List<KotItem> items, Station station) {
        String payload = buildPayload(items, station);
        sendToPrinterDevice(payload, station.getDeviceIp());
    }

    private String buildPayload(List<KotItem> items, Station station) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== KOT for ").append(station.getName()).append(" ===\n");
        for (KotItem item : items) {
            sb.append(item.getOrderItem().getMenuItem().getItemName()).append(" x").append(item.getQuantity());
            if (item.getSpecialRequests() != null) {
                sb.append(" (").append(item.getSpecialRequests()).append(")");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void sendToPrinterDevice(String payload, String deviceIp) {
        System.out.println("Sending to printer " + deviceIp + ":\n" + payload);
    }
}
