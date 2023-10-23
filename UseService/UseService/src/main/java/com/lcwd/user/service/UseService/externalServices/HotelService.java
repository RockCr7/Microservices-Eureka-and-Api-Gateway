package com.lcwd.user.service.UseService.externalServices;

import com.lcwd.user.service.UseService.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Hotel-Service")
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
    Hotel getHotelByHotelId(@PathVariable("hotelId") String hotelId);
}
