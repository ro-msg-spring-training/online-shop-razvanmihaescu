package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.services.StockService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Stock")
public class StockController {

    private final StockService stockService;

    @GetMapping("/export/{locationId}")
    public void exportStockForGivenLocation(@PathVariable Integer locationId, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=stocks.csv");
        response.getWriter().print(stockService.exportStockForGivenLocation(locationId));
    }
}
